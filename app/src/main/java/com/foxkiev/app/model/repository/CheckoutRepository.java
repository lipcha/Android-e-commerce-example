package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.models.ShippingMethod;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by lipcha on 11.05.18.
 */

public interface CheckoutRepository {

    Observable<Address> getShippingAddress();

    Observable<List<Address>> getBillingAddress();

    Observable<List<ShippingMethod>> getShippingMethods();

    Observable<Address> setAddress(String email, String firstname, String lastname, String telephone, String city, String warehouse);

    Observable<PaymentMethodsTotal> setShippingInfo(String email, String firstname, String lastname, String telephone, String city, String warehouse, String carrierCode);

    Observable<String> setPaymentInfo(final String paymentMethod, String carrierCode);
}
