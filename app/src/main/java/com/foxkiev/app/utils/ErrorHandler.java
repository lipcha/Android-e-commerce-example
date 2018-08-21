package com.foxkiev.app.utils;

import android.content.Context;
import android.text.TextUtils;

import com.foxkiev.app.model.models.ErrorResponse;
import com.foxkiev.app.ui.custom_views.TopBanner;

import retrofit2.HttpException;

/**
 * Created by lipcha on 25.04.18.
 */

public class ErrorHandler {

    public static void onError(Context context, final Throwable throwable){
        if (throwable == null)
            return;

        if (throwable instanceof HttpException){
            final ErrorResponse errorResponse = ErrorResponse.fromHttpExeption((HttpException)throwable);
            if (errorResponse != null && !TextUtils.isEmpty(errorResponse.getMessage())){
                TopBanner.newInstance(context, errorResponse.getMessage()).show();
                return;
            }
        }

        TopBanner.newInstance(context, throwable.getMessage()).show();
    }

    public interface ErrorCallback {
        void onError(final Throwable throwable);
    }
}
