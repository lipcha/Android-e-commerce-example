package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 05.03.18.
 */

public class ProductInfo implements IdModel {

    private String sku;

    @SerializedName("category_id")
    private String categoryId;

    private String name;

    private int status;

    @SerializedName("is_in_stock")
    private boolean isInStock;

    @SerializedName("type_id")
    private String typeId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("short_description")
    private String shortDescription;

    private float price;

    @SerializedName("tier_prices")
    private List<Price> tierPrices;

    @SerializedName("final_price")
    private float finalPrice;

    @SerializedName("special_price")
    private float specialPrice;

    @SerializedName("special_price_from_date")
    private String specialPriceFromDate;

    @SerializedName("special_price_to_date")
    private String specialPriceToDate;

    @SerializedName("product_is_new_from_date")
    private String productIsNewFromDate;

    @SerializedName("product_is_new_to_date")
    private String productIsNewToDate;

    @SerializedName("is_new")
    private boolean isNew;

    public String getSku() {
        return sku;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public float getPrice() {
        return price;
    }

    public List<Price> getTierPrices() {
        return tierPrices;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public float getSpecialPrice() {
        return specialPrice;
    }

    public String getSpecialPriceFromDate() {
        return specialPriceFromDate;
    }

    public String getSpecialPriceToDate() {
        return specialPriceToDate;
    }

    public String getProductIsNewFromDate() {
        return productIsNewFromDate;
    }

    public String getProductIsNewToDate() {
        return productIsNewToDate;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean hasSpecialPrice(){
        return specialPrice != 0 && specialPrice == finalPrice;
    }

    @Override
    public String getId() {
        return sku;
    }
}
