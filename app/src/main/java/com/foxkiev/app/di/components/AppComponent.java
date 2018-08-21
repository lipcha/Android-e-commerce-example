package com.foxkiev.app.di.components;

import android.content.Context;

import com.foxkiev.app.base.executors.RxExecutor;
import com.foxkiev.app.di.modules.ApiModule;
import com.foxkiev.app.di.modules.ContextModule;
import com.foxkiev.app.di.modules.RxExecutorModule;
import com.foxkiev.app.di.modules.StorageModule;
import com.foxkiev.app.model.api.CartApi;
import com.foxkiev.app.model.api.CheckoutApi;
import com.foxkiev.app.model.api.CurrencyApi;
import com.foxkiev.app.model.api.NotificationApi;
import com.foxkiev.app.model.api.NovaPoshtaApi;
import com.foxkiev.app.model.api.OrdersApi;
import com.foxkiev.app.model.api.ShopApi;
import com.foxkiev.app.model.api.UserApi;
import com.foxkiev.app.model.storage.PreferenceStorage;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lipcha on 20.02.18.
 */

@Component(modules = {ContextModule.class, ApiModule.class, RxExecutorModule.class, StorageModule.class})
@Singleton
public interface AppComponent {

    Context getContext();

    UserApi getUserApi();

    ShopApi getShopApi();

    CartApi getCartApi();

    CurrencyApi getCurrencyApi();

    NotificationApi getNotificationApi();

    NovaPoshtaApi getNovaPoshtaApi();

    CheckoutApi getCheckoutApi();

    OrdersApi getOrdersApi();

    RxExecutor getRxExecutor();

    PreferenceStorage getPreferenceStorage();
}
