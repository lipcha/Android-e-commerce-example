package com.foxkiev.app.di.modules;

import com.foxkiev.app.model.api.CartApi;
import com.foxkiev.app.model.api.CheckoutApi;
import com.foxkiev.app.model.api.CurrencyApi;
import com.foxkiev.app.model.api.NotificationApi;
import com.foxkiev.app.model.api.NovaPoshtaApi;
import com.foxkiev.app.model.api.OrdersApi;
import com.foxkiev.app.model.api.ShopApi;
import com.foxkiev.app.model.api.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by lipcha on 20.02.18.
 */

@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public UserApi provideUserApi(Retrofit retrofit){
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Singleton
    public ShopApi provideShopApi(Retrofit retrofit){
        return retrofit.create(ShopApi.class);
    }

    @Provides
    @Singleton
    public CartApi provideCartApi(Retrofit retrofit){
        return retrofit.create(CartApi.class);
    }

    @Provides
    @Singleton
    public NovaPoshtaApi provideNovaPoshtaApi(Retrofit retrofit){
        return retrofit.create(NovaPoshtaApi.class);
    }

    @Provides
    @Singleton
    public CheckoutApi provideCheckoutApi(Retrofit retrofit){
        return retrofit.create(CheckoutApi.class);
    }

    @Provides
    @Singleton
    public OrdersApi provideOrdersApi(Retrofit retrofit){
        return retrofit.create(OrdersApi.class);
    }

    @Provides
    @Singleton
    public NotificationApi provideNotificationApi(Retrofit retrofit){
        return retrofit.create(NotificationApi.class);
    }

    @Provides
    @Singleton
    CurrencyApi provideCurrencyApi(Retrofit retrofit){
        return retrofit.create(CurrencyApi.class);
    }
}
