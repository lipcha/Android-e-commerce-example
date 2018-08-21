package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.models.Warehouse;

/**
 * Created by lipcha on 11.05.18.
 */

public interface ShippingAddressView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void beforeLoadingAddress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderShippingAddress(Address address);

    @StateStrategyType(SkipStrategy.class)
    void onLoadAddressFailed(Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void fillCustomerInformation(Customer customer);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderCity(City city);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderWarehouse(Warehouse warehouse);

    @StateStrategyType(SkipStrategy.class)
    void showAuthentication();

    @StateStrategyType(SkipStrategy.class)
    void showNotValidFieldError(FieldId field);

    @StateStrategyType(SkipStrategy.class)
    void selectWarehouse(final City city);

    @StateStrategyType(SkipStrategy.class)
    void beforeApplyAddress();

    @StateStrategyType(SkipStrategy.class)
    void onApplyAddressSuccessSuccess(Address address);

    @StateStrategyType(SkipStrategy.class)
    void onApplyAddressFailed(Throwable throwable);
}
