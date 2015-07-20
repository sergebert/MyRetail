package com.myretail.encoder;

import com.myretail.domain.Product;

import org.json.JSONException;
import org.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * Created by Serge as part of the MyRetail programming case study for Target
 * July 2015
 */
public class JSONProductEncoder {

    final static Logger logger = Logger.getLogger(JSONProductEncoder.class);

    public String getJSONForProduct(Product product) {

        logger.info("About to create a JSON representation for a product.");

        JSONObject json = null;

        try {

            //instantiate new Json Object
            json = new JSONObject();

            // put value pairs into the JSON object from the Product object
            json.put("id", product.getId());
            json.put("name", product.getName());
            json.put("current_price", product.getPrice());
            json.put("currency_code", product.getCurrencyCode());
            json.put("externalLookUpURL", product.getExternalLookUpURL());

        } catch (JSONException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("Failed to convert Product object values into a JSON Object.", e);
        }

        // finally return the json as a string
        return (json.toString());
    }
}
