package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.PaymentMethod;

import java.util.List;

public interface PaymentMethodsView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderPaymentMethods(List<PaymentMethod> paymentMethods);

    @StateStrategyType(SkipStrategy.class)
    void placeOrderWithLiqPay(PaymentMethod paymentMethod);

    @StateStrategyType(SkipStrategy.class)
    void beforePlaceOrder();

    @StateStrategyType(SkipStrategy.class)
    void onPlaceOrderSuccess(String orderNumber);

    @StateStrategyType(SkipStrategy.class)
    void onPlaceOrderFailed(Throwable throwable);
}
