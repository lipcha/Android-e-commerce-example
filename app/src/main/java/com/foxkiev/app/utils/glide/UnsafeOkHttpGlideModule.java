package com.foxkiev.app.utils.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.foxkiev.app.utils.SecureUtils;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by lipcha on 28.02.18.
 */

@GlideModule
public class UnsafeOkHttpGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient client = SecureUtils.getUnsafeOkHttpClient(new OkHttpClient.Builder()).build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}