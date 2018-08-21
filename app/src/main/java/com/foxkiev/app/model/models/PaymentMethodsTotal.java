package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaymentMethodsTotal implements Serializable{

    @SerializedName("payment_methods")
    private List<PaymentMethod> paymentMethods;

    private CartTotal totals;

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public CartTotal getTotals() {
        return totals;
    }
}
