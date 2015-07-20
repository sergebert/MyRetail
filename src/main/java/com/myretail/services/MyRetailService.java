package com.myretail.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.myretail.domain.Product;
import com.myretail.model.ProductModel;
import com.myretail.encoder.JSONProductEncoder;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Serge as part of the MyRetail programming case study for Target
 * July 2015
 */
@Path("/products")
public class MyRetailService {

    final static Logger logger = Logger.getLogger(MyRetailService.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hi from MyRetail!";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProduct(@PathParam("id") int id) {

        BasicConfigurator.configure();

        // log incoming call
        logger.info("Incoming call to getProduct for product Id = " + id);

        // Return a product from our model
        //
        // Instantiate our singleton model
        ProductModel productModel = ProductModel.getInstance();

        // Create some products within our Product Model
        productModel.createProducts();

        // Attempt to retrieve the product specified by the incoming "id" parameter
        // from our model
        final Product product = productModel.getProduct(id);

        if (product == null) {
            logger.info("Incoming request had an invalid Product id");

            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        // Encode the product to the requested return type
        // For the purposes of this exercise, it's going to be JSON, but it could
        // be XML, HTML, or something else
        //
        logger.info("Incoming request had a valid Product id");

        // Create the Product to JSON encoder
        JSONProductEncoder productEncoder = new JSONProductEncoder();

        // return the JSON (in this case, a string), from the encoder for this product
        return (productEncoder.getJSONForProduct(product));
    }
}