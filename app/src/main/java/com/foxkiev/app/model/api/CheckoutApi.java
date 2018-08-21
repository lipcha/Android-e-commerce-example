package com.foxkiev.app.model.api;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.models.ShippingMethod;
import com.foxkiev.app.model.models.request.BillingAddressWrapper;
import com.foxkiev.app.model.models.request.EstimateShippingMethods;
import com.foxkiev.app.model.models.request.PaymentInfoWrapper;
import com.foxkiev.app.model.models.request.ShippingInfoWrapper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lipcha on 11.05.18.
 */

public interface CheckoutApi {

    @GET(Constants.API.REQUESTS.SHIPPING_ADDRESS)
    Observable<Address> getShippingAddress();

    @GET(Constants.API.REQUESTS.BILLING_ADDRESS)
    Observable<List<Address>> getBillingAddress();

    @POST(Constants.API.REQUESTS.EDIT_BILLING_ADDRESS)
    Observable<String> editBillingAddress(@Body BillingAddressWrapper shippingAddressWrapper);

    @POST(Constants.API.REQUESTS.ESTIMATE_SHIPPING_METHODS)
    Observable<String> estimateShippingMethodsByAddressId(@Body EstimateShippingMethods estimateShippingMethods);

    @GET(Constants.API.REQUESTS.GET_SHIPPING_METHODS)
    Observable<List<ShippingMethod>> getShippingMethods();

    @POST(Constants.API.REQUESTS.SET_SHIPPING_INFO)
    Observable<PaymentMethodsTotal> setShippingInfo(@Body ShippingInfoWrapper shippingInfoWrapper);

    @POST(Constants.API.REQUESTS.SET_PAYMENT_INFO)
    Observable<String> setPaymentInfo(@Body PaymentInfoWrapper paymentInfoWrapper);
}
