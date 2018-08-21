package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.Constants;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.PaymentMethod;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.repository.CheckoutRepository;
import com.foxkiev.app.presentation.view.PaymentMethodsView;

import java.util.HashMap;

import javax.inject.Inject;

import ua.privatbank.paylibliqpay.ErrorCode;
import ua.privatbank.paylibliqpay.LiqPay;
import ua.privatbank.paylibliqpay.LiqPayCallBack;

@InjectViewState
public class PaymentMethodsPresenter extends BasePresenter<PaymentMethodsView>{

    @Inject
    CheckoutRepository mCheckoutRepository;

    private final PaymentMethodsTotal mPaymentMethodsTotal;
    private final String mCarrierCode;

    public PaymentMethodsPresenter(String carrierCode, PaymentMethodsTotal paymentMethodsTotal) {
        this.mPaymentMethodsTotal = paymentMethodsTotal;
        this.mCarrierCode = carrierCode;
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        getViewState().renderPaymentMethods(mPaymentMethodsTotal.getPaymentMethods());
    }

    public void placeOrder(PaymentMethod selectedPaymentMethod) {
        if (selectedPaymentMethod == null)
            return;
        switch (selectedPaymentMethod.getCode()){
            case Constants.PAYMENT_METHOD_CASH:
                getViewState().beforePlaceOrder();
                getExecutor().execute(mCheckoutRepository.setPaymentInfo(selectedPaymentMethod.getCode(), mCarrierCode),
                        orderNumber -> getViewState().onPlaceOrderSuccess(orderNumber),
                        getViewState()::onPlaceOrderFailed
                );
                break;
            case Constants.PAYMENT_METHOD_BANK:
                getViewState().placeOrderWithLiqPay(selectedPaymentMethod);
                break;
        }
    }
}
