package com.foxkiev.app.di.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.foxkiev.app.Constants;
import com.foxkiev.app.model.api.Oauth1SigningInterceptor;
import com.foxkiev.app.model.models.Attribute;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.utils.gson.AttributeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lipcha on 20.02.18.
 */

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Converter.Factory converterFactory, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constants.API.BASE_SERVER_URL + Constants.API.API_VERSION)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(Interceptor interceptor){
        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(Constants.API.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.API.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.API.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor());
//        return SecureUtils.getUnsafeOkHttpClient(httpClientBuilder).build();
        return httpClientBuilder.build();
    }


    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(final Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Attribute.class, new AttributeAdapter().nullSafe());
        return builder.create();
    }

    @Provides
    @Singleton
    public Interceptor provideOauthInterceptor(PreferenceStorage preferenceStorage){
        return new Oauth1SigningInterceptor.Builder()
                .consumerKey(Constants.API.CONSUMER_KEY)
                .consumerSecret(Constants.API.CONSUMER_SECRET)
                .accessToken(Constants.API.ACCESS_TOKEN)
                .accessSecret(Constants.API.ACCESS_SECRET_TOKEN)
                .storage(preferenceStorage)
                .build();
    }
}
