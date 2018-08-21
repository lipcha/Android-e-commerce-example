package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 01.03.18.
 */

public class Price {

    @SerializedName("customer_group_id")
    private int customerGroupId;

    private String qty;

    private float value;


}
