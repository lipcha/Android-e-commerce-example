package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

public class ShippingMethod {

    @SerializedName("carrier_code")
    private String carrierCode;

    @SerializedName("method_code")
    private String methodCode;

    @SerializedName("carrier_title")
    private String carrierTitle;

    @SerializedName("method_title")
    private String methodTitle;

    private String amount;

    @SerializedName("base_amount")
    private String baseamount;

    private boolean available;

    @SerializedName("error_message")
    private String errorMessage;

    @SerializedName("price_excl_tax")
    private int priceExclTax;

    @SerializedName("price_incl_tax")
    private  int priceInclTax;

    public String getCarrierCode() {
        return carrierCode;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public String getCarrierTitle() {
        return carrierTitle;
    }

    public String getMethodTitle() {
        return methodTitle;
    }

    public String getAmount() {
        return amount;
    }

    public String getBaseamount() {
        return baseamount;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getPriceExclTax() {
        return priceExclTax;
    }

    public int getPriceInclTax() {
        return priceInclTax;
    }
}
