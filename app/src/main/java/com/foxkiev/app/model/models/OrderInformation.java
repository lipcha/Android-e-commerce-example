package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

public class OrderInformation {

    @SerializedName("increment_id")
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }
}
