package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 10.04.18.
 */

public class Customer {

    private String email;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    private String confirmation;
    private String created_at;
    private String updated_at;

    private String created_in;
    private int disable_auto_group_change;
    private int group_id;
    private int id;
    private int store_id;
    private int website_id;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }
}
