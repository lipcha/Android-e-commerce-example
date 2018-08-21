package com.foxkiev.app.di.components;

import com.foxkiev.app.di.modules.RepositoryModule;
import com.foxkiev.app.di.scope.RepositoryScope;
import com.foxkiev.app.presentation.presenter.CartItemsPresenter;
import com.foxkiev.app.presentation.presenter.CartPresenter;
import com.foxkiev.app.presentation.presenter.CartTotalPresenter;
import com.foxkiev.app.presentation.presenter.CategoryPresenter;
import com.foxkiev.app.presentation.presenter.ChangePasswordPresenter;
import com.foxkiev.app.presentation.presenter.CityListPresenter;
import com.foxkiev.app.presentation.presenter.CurrencyPresenter;
import com.foxkiev.app.presentation.presenter.ListModePresenter;
import com.foxkiev.app.presentation.presenter.MainActivityPresenter;
import com.foxkiev.app.presentation.presenter.MyOrdersPresenter;
import com.foxkiev.app.presentation.presenter.NotificationDetailPresenter;
import com.foxkiev.app.presentation.presenter.NotificationListPresenter;
import com.foxkiev.app.presentation.presenter.PaymentMethodsPresenter;
import com.foxkiev.app.presentation.presenter.ProductDetailPresenter;
import com.foxkiev.app.presentation.presenter.ProductListPresenter;
import com.foxkiev.app.presentation.presenter.ProfilePresenter;
import com.foxkiev.app.presentation.presenter.ResetPasswordPresenter;
import com.foxkiev.app.presentation.presenter.SettingsPresenter;
import com.foxkiev.app.presentation.presenter.ShippingAddressPresenter;
import com.foxkiev.app.presentation.presenter.ShippingMethodsPresenter;
import com.foxkiev.app.presentation.presenter.SignInPresenter;
import com.foxkiev.app.presentation.presenter.SignUpPresenter;
import com.foxkiev.app.presentation.presenter.WarehouseListPresenter;

import dagger.Component;

/**
 * Created by lipcha on 22.02.18.
 */

@RepositoryScope
@Component(dependencies = AppComponent.class, modules = {RepositoryModule.class})
public interface RepositoryComponent {

    void inject(SignInPresenter signInPresenter);

    void inject(CategoryPresenter categoryPresenter);

    void inject(ProductListPresenter productListPresenter);

    void inject(ProductDetailPresenter productDetailPresenter);

    void inject(CartPresenter cartPresenter);

    void inject(CartItemsPresenter cartItemsPresenter);

    void inject(CartTotalPresenter cartCountPresenter);

    void inject(ResetPasswordPresenter resetPasswordPresenter);

    void inject(SignUpPresenter signUpPresenter);

    void inject(CityListPresenter cityListPresenter);

    void inject(WarehouseListPresenter warehouseListPresenter);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(SettingsPresenter settingsPresenter);

    void inject(ProfilePresenter profilePresenter);

    void inject(ShippingAddressPresenter addressPresenter);

    void inject(ChangePasswordPresenter changePasswordPresenter);

    void inject(ShippingMethodsPresenter shippingMethodsPresenter);

    void inject(PaymentMethodsPresenter paymentMethodsPresenter);

    void inject(MyOrdersPresenter myOrdersPresenter);

    void inject(NotificationListPresenter notificationListPresenter);

    void inject(NotificationDetailPresenter notificationDetailPresenter);

    void inject(CurrencyPresenter currencyPresenter);

    void inject(ListModePresenter listModePresenter);
}
