package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lipcha on 08.05.18.
 */

public class Warehouse implements IdModel, Serializable{

    @SerializedName("Ref")
    private String ref;

    @SerializedName("Description")
    private String description;

    public Warehouse(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return ref;
    }

    public String getDescription() {
        return description;
    }
}
