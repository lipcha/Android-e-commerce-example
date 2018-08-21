package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lipcha on 23.02.18.
 */

public class Category implements IdModel, Serializable{

    private String id;

    @SerializedName("parent_id")
    private int parentId;

    private String name;

    @SerializedName("is_active")
    private boolean isActive;

    @SerializedName("include_in_menu")
    private boolean includeInMenu;

    private int position;

    private int level;

    @SerializedName("product_count")
    private int productCount;

    @SerializedName("children_data")
    private List<Category> childrenCategory;

    @SerializedName("img_url")
    private String imageUrl;

    @Override
    public String getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getPosition() {
        return position;
    }

    public int getLevel() {
        return level;
    }

    public int getProductCount() {
        return productCount;
    }

    public boolean hasChildrenCategory(){
        return childrenCategory != null && !childrenCategory.isEmpty();
    }

    public List<Category> getChildrenCategory() {
        return childrenCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isIncludeInMenu() {
        return includeInMenu;
    }
}
