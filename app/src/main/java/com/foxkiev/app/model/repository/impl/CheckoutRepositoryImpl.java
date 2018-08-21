package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.CheckoutApi;
import com.foxkiev.app.model.api.OrdersApi;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.OrderInformation;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.models.ShippingMethod;
import com.foxkiev.app.model.models.request.BillingAddressWrapper;
import com.foxkiev.app.model.models.request.PaymentInfoWrapper;
import com.foxkiev.app.model.models.request.ShippingInfoWrapper;
import com.foxkiev.app.model.repository.CheckoutRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lipcha on 11.05.18.
 */

public class CheckoutRepositoryImpl implements CheckoutRepository {

    private final CheckoutApi mCheckoutApi;

    private final OrdersApi mOrderApi;

    private final PreferenceStorage mPreferenceStorage;

    public CheckoutRepositoryImpl(CheckoutApi checkoutApi, PreferenceStorage preferenceStorage, OrdersApi ordersApi) {
        this.mCheckoutApi = checkoutApi;
        this.mOrderApi = ordersApi;
        this.mPreferenceStorage = preferenceStorage;
    }

    @Override
    public Observable<Address> getShippingAddress() {
        return mCheckoutApi.getShippingAddress();
    }

    @Override
    public Observable<List<Address>> getBillingAddress() {
        return mCheckoutApi.getBillingAddress();
    }

    @Override
    public Observable<List<ShippingMethod>> getShippingMethods(){
        return mCheckoutApi.getShippingMethods();
    }

    @Override
    public Observable<Address> setAddress(String email, String firstname, String lastname, String telephone, String city, String warehouse) {
        return mCheckoutApi.editBillingAddress(new BillingAddressWrapper(email, firstname, lastname, telephone, city, warehouse))
                .map(addressId -> new Address(addressId, email, firstname, lastname, telephone, city, warehouse));
    }

    @Override
    public Observable<PaymentMethodsTotal> setShippingInfo(String email, String firstname, String lastname, String telephone, String city, String warehouse, String carrierCode) {
        return mCheckoutApi.setShippingInfo(new ShippingInfoWrapper(email, firstname, lastname, telephone, city, warehouse, carrierCode));
    }

    @Override
    public Observable<String> setPaymentInfo(String paymentMethod, String carrierCode) {
        return mCheckoutApi.setPaymentInfo(new PaymentInfoWrapper(carrierCode, paymentMethod))
                .doOnNext(id -> {
                    mPreferenceStorage.storeCustomerCartId("");
                    mPreferenceStorage.storeGustCartId("");
                })
                .flatMap(mOrderApi::getOrderInformation)
                .map(OrderInformation::getOrderNumber);
    }
}
