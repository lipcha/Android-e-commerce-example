package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.models.ShippingMethod;

import java.util.List;

public interface ShippingMethodsView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void beforeLoadShippingMethods();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onLoadShippingMethodsSuccess(List<ShippingMethod> shippingMethods);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onLoadShippingMethodsFailed(final Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void showSelectShippingMethodError();

    @StateStrategyType(SkipStrategy.class)
    void beforeUploadShippingInfo();

    @StateStrategyType(SkipStrategy.class)
    void onUploadShippingInfoSuccess(String carrierCode, PaymentMethodsTotal paymentMethodsTotal);

    @StateStrategyType(SkipStrategy.class)
    void onUploadShippingInfoFailed(Throwable throwable);
}
