package com.myretail.model;

import java.io.Serializable;
import java.util.HashMap;

import com.myretail.domain.Product;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.log4j.Logger;


/**
 * Created by Serge as part of the MyRetail programming case study for Target
 * July 2015
 */
public final class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    final static Logger logger = Logger.getLogger(ProductModel.class);

    // Store a collection of Product objects in a hash map
    HashMap productMap = null;

    // private instance variable
    private static ProductModel INSTANCE = null;

    //**************************
    // Singleton related methods
    //**************************

    // private “shouting” constructor
    private ProductModel() {
        if (INSTANCE != null) {
            // SHOUT
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static ProductModel getInstance() {

        return ProductModelLoader.INSTANCE;
    }

    // Curse you serialization!!!
    @SuppressWarnings("unused")
    private ProductModel readResolve() {
        return ProductModelLoader.INSTANCE;
    }

    // Wrapped in a inner static class so that loaded only when required
    private static class ProductModelLoader {

        // And no more fear of threads
        private static final ProductModel INSTANCE = new ProductModel();

    }

    //************************
    // Product related methods
    //************************

    // Create a set of products for the purposes of this exercise
    //
    // In this case, we're going to load up our product hash map
    // with a number of fake products that we may later retrieve
    //
    // Note: a couple of key attributes of the products will be
    // purposely left null, because we will later load those
    // values from different sources

    public void createProducts(){

        logger.info("About to create some products.");

        if (productMap == null)
        {
            productMap = new HashMap();
        }

        // clear the hash map
        productMap.clear();

        // Create a few products, in this case, 7 of them, then add each one to the hash map
        // Note: the product ID of each product will serve as the key to that object in the map

        Product product01 = new Product();
        product01.setId(14423830);
        product01.setCurrencyCode("USD");
        product01.setExternalLookUpURL("https://api.target.com/products/v3/14423830?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product01.getId(), product01);

        Product product02 = new Product();
        product02.setId(14423840);
        product02.setCurrencyCode("USD");
        product02.setExternalLookUpURL("https://api.target.com/products/v3/14423840?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product02.getId(), product02);

        Product product03 = new Product();
        product03.setId(17293894);
        product03.setCurrencyCode("USD");
        product03.setExternalLookUpURL("https://api.target.com/products/v3/17293894?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product03.getId(), product03);

        Product product04 = new Product();
        product04.setId(17293888);
        product04.setCurrencyCode("USD");
        product04.setExternalLookUpURL("https://api.target.com/products/v3/17293888?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product04.getId(), product04);

        Product product05 = new Product();
        product05.setId(14000294);
        product05.setCurrencyCode("USD");
        product05.setExternalLookUpURL("https://api.target.com/products/v3/14000294?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product05.getId(), product05);

        Product product06 = new Product();
        product06.setId(14000289);
        product06.setCurrencyCode("USD");
        product06.setExternalLookUpURL("https://api.target.com/products/v3/14000289?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product06.getId(), product06);

        Product product07 = new Product();
        product07.setId(14213715);
        product07.setCurrencyCode("USD");
        product07.setExternalLookUpURL("https://api.target.com/products/v3/14213715?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
        productMap.put(product07.getId(), product07);
    }

    public Product getProduct(int id) {

        logger.info("About to get a product.");

        // This method returns a Product object, provided the caller provides a valid id
        // If no corresponding Product is found for the id, this method returns null
        // If a corresponding Product is found, then we check to see if the productName
        // has been set for the Product.  If it's there, and effectively, this means it's cached,
        // then we return the Product as is.
        // Otherwise, look up the productName by performing an HTTP lookup to get
        // product info from api.target.com, then parse the JSON and then use that to set the productName
        // for the given product before returning it.
        Product product = null;

        // Attempt to find the product in our Product map
        product = (Product)productMap.get(id);

        if (product != null){

            // Check to see if the productName has already been set
            if (product.getName() == null) {

                logger.info("About to parse the JSON for product id = " + id);

                // We have a valid Product, but its name has not yet been set, so let's look it up
                String jsonFeedForProduct = this.readJSONFeed(product.getExternalLookUpURL());

                try {

                    // Create a new JSON object based on the retrieved JSON info for the given product
                    JSONObject jsonForProduct = new JSONObject(jsonFeedForProduct);

                    // Create a new JSON object based on the JSON under the product composite response section
                    JSONObject jsonComposite = jsonForProduct.getJSONObject("product_composite_response");

                    // Create a new JSON array based on the items JSON under the product composite section
                    JSONArray jsonArray = jsonComposite.getJSONArray("items");

                    // Create a new JSON object based on the first item in the array
                    JSONObject jsonFinal = (JSONObject) jsonArray.get(0);

                    // Create a string based on extracting the general description section from the latest
                    // JSON object
                    String prodName = jsonFinal.getString("general_description");

                    // Based on all that mess, set the product name
                    product.setName(prodName);

                } catch (JSONException e) {

                    logger.error(e);
                }
            }

        }

        return product;
    }

    private String readJSONFeed(String URL) {

        logger.info("About to make HTTP request to get JSON for a product.");

        StringBuilder stringBuilder = new StringBuilder();
        //HttpClient httpClient = new DefaultHttpClient();
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(URL);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();

            } else {
                logger.error("Failed in HTTP request for a JSON file for a product.");
                throw new RuntimeException("Failed to download JSON file for a product.");
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return stringBuilder.toString();
    }
}