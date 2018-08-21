package com.foxkiev.app.model.models;

import com.google.gson.Gson;

import retrofit2.HttpException;

/**
 * Created by lipcha on 25.04.18.
 */

public class ErrorResponse {

    private String message;

    public static synchronized ErrorResponse fromHttpExeption(final HttpException httpException){
        try {
            return new Gson().fromJson(httpException.response().errorBody().string(), ErrorResponse.class);
        }catch (Exception e){
            return null;
        }


    }

    public String getMessage() {
        return message;
    }
}
