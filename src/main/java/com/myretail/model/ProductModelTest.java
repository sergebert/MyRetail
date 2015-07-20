package com.myretail.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Serge as part of the MyRetail programming case study for Target
 * July 2015
 */
public class ProductModelTest extends TestCase {

    final static Logger logger = Logger.getLogger(ProductModel.class);

    private ProductModel singletonOne = null, getSingletonTwo = null;

    public void setUp(){

        BasicConfigurator.configure();

        logger.info("creating one singleton...");
        singletonOne = ProductModel.getInstance();
        logger.info("got the first singleton: " + singletonOne);

        logger.info("creating the second singlton...");
        getSingletonTwo = ProductModel.getInstance();
        logger.info("got the second singleton: " + getSingletonTwo);

    }

    public void testGetInstance() throws Exception {
        logger.info("about to validate that the two singletons are, in fact, equal");
        Assert.assertEquals(true, singletonOne == getSingletonTwo);
    }
}