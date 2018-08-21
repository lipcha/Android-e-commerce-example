package com.foxkiev.app.di.modules;

import com.foxkiev.app.model.api.CartApi;
import com.foxkiev.app.model.api.CheckoutApi;
import com.foxkiev.app.model.api.CurrencyApi;
import com.foxkiev.app.model.api.NotificationApi;
import com.foxkiev.app.model.api.NovaPoshtaApi;
import com.foxkiev.app.model.api.OrdersApi;
import com.foxkiev.app.model.api.ShopApi;
import com.foxkiev.app.model.api.UserApi;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.model.repository.CheckoutRepository;
import com.foxkiev.app.model.repository.CurrencyRepository;
import com.foxkiev.app.model.repository.DestinationRepository;
import com.foxkiev.app.model.repository.NotificationRepository;
import com.foxkiev.app.model.repository.OrdersRepository;
import com.foxkiev.app.model.repository.ShopRepository;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.model.repository.impl.CartRepositoryImpl;
import com.foxkiev.app.model.repository.impl.CheckoutRepositoryImpl;
import com.foxkiev.app.model.repository.impl.CurrencyRepositoryImpl;
import com.foxkiev.app.model.repository.impl.NotificationRepositoryImpl;
import com.foxkiev.app.model.repository.impl.OrdersRepositoryImpl;
import com.foxkiev.app.model.repository.impl.ProductDestinationRepositoryImpl;
import com.foxkiev.app.model.repository.impl.ShopRepositoryImpl;
import com.foxkiev.app.model.repository.impl.UserRepositoryImpl;
import com.foxkiev.app.model.storage.PreferenceStorage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lipcha on 22.02.18.
 */

@Module
public class RepositoryModule {

    @Provides
    public UserRepository provideUserRepository(UserApi userApi, PreferenceStorage preferenceStorage){
        return new UserRepositoryImpl(userApi, preferenceStorage);
    }

    @Provides
    public ShopRepository provideShopRepository(ShopApi shopApi){
        return new ShopRepositoryImpl(shopApi);
    }

    @Provides
    public CartRepository provideCartRepository(CartApi cartApi, PreferenceStorage preferenceStorage){
        return new CartRepositoryImpl(cartApi, preferenceStorage);
    }

    @Provides
    public DestinationRepository provideProductDestinationRepository(NovaPoshtaApi novaPoshtaApi){
        return new ProductDestinationRepositoryImpl(novaPoshtaApi);
    }

    @Provides
    public CheckoutRepository provideCheckoutRepository(CheckoutApi checkoutApi, PreferenceStorage preferenceStorage, OrdersApi ordersApi){
        return new CheckoutRepositoryImpl(checkoutApi, preferenceStorage, ordersApi);
    }

    @Provides
    public OrdersRepository provideOrdersRepository(OrdersApi ordersApi, PreferenceStorage preferenceStorage){
        return new OrdersRepositoryImpl(ordersApi, preferenceStorage);
    }

    @Provides
    public NotificationRepository provideNotificationRepository(NotificationApi notificationApi){
        return new NotificationRepositoryImpl(notificationApi);
    }

    @Provides
    public CurrencyRepository provideCurrencyRepository(CurrencyApi currencyApi){
        return new CurrencyRepositoryImpl(currencyApi);
    }
}
