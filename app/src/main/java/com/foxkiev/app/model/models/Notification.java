package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 20.04.18.
 */

public class Notification implements IdModel {

    private String id;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("product_ids")
    private List<String > productIds;

    @SerializedName("publish_date")
    private String publishDate; //"2018-05-24 15:28:25"

    @SerializedName("short_description")
    private String shortDescription;

    private String title;

    private String url;

    @SerializedName("url_key")
    private String urlKey;


    @Override
    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlKey() {
        return urlKey;
    }
}
