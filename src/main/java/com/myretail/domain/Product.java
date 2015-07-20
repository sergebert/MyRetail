package com.myretail.domain;

/**
 * Created by Serge as part of the MyRetail programming case study for Target
 * July 2015
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private String currencyCode;
    private String externalLookUpURL;

    // Id getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Name getters & setters
    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    // Price getters & setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double productPrice) {
        this.price = productPrice;
    }

    // Currency Code getters & setters
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    // External Lookup URL getters & setters
    public String getExternalLookUpURL() {
        return externalLookUpURL;
    }

    public void setExternalLookUpURL(String externalLookUpURL) {
        this.externalLookUpURL = externalLookUpURL;
    }
}
