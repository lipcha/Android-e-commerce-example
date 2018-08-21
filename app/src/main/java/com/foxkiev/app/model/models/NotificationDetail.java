package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationDetail extends Notification{

    private List<ProductInfo> products;

    @SerializedName("post_content")
    private String content;

    public List<ProductInfo> getProducts() {
        return products;
    }

    public String getContent() {
        return content;
    }
}
