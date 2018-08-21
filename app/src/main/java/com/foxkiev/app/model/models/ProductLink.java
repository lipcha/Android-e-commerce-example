package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

public class ProductLink {

    @SerializedName("link_type")
    private String linkType;

    @SerializedName("linked_product_sku")
    private String linkedProductSku;

    @SerializedName("linked_product_type")
    private String linkedProductType;

    private int position;

    private String sku;

    public String getLinkType() {
        return linkType;
    }

    public String getLinkedProductSku() {
        return linkedProductSku;
    }

    public String getLinkedProductType() {
        return linkedProductType;
    }

    public int getPosition() {
        return position;
    }

    public String getSku() {
        return sku;
    }
}
