package com.foxkiev.app.model.models.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 17.04.18.
 */

public class Credentials {

    @SerializedName("username")
    private final String userName;

    private final String password;

    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
