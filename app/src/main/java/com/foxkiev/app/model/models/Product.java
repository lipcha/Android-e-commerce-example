package com.foxkiev.app.model.models;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.foxkiev.app.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lipcha on 26.02.18.
 */

public class Product extends ProductInfo implements Serializable, IdModel {

    private String id;

    @SerializedName("attribute_set_id")
    private int attributeSetId;

    private int visibility;

    private float weight;

    @SerializedName("media_gallery_entries")
    private List<MediaGallery> mediaGalleries;

    @SerializedName("custom_attributes")
    private Attribute customAttibutes;

    @SerializedName("product_links")
    private List<ProductLink> productLinks;

    @SerializedName("extension_attributes")
    private ExtensionAttributes extensionAttributes;

    @Override
    public String getId() {
        return id;
    }

    public int getAttributeSetId() {
        return attributeSetId;
    }


    public int getVisibility() {
        return visibility;
    }

    public float getWeight() {
        return weight;
    }

    public List<MediaGallery> getMediaGalleries() {
        return mediaGalleries;
    }

    @Override
    public boolean hasSpecialPrice() {
        return customAttibutes != null && customAttibutes.containsKey("special_price") && !TextUtils.isEmpty(customAttibutes.get("special_price"));
    }

    @Override
    public float getSpecialPrice() {
        return Float.parseFloat(customAttibutes.get("special_price"));
    }

    @Nullable
    public StockItem getStockItem(){
        return extensionAttributes == null ? null : extensionAttributes.getStockItem();
    }

    public String getCustomAttribute(String attrKey){
        if (customAttibutes != null && customAttibutes.containsKey(attrKey))
            return customAttibutes.get(attrKey);
        return null;
    }

    public List<ProductLink> getProductLinks() {
        return productLinks;
    }

    public String getProductImageUrl(){
        if (mediaGalleries == null || mediaGalleries.isEmpty())
            return "";
        return Constants.API.BASE_MEDIA_URL + mediaGalleries.get(0).getFile();
    }
}
