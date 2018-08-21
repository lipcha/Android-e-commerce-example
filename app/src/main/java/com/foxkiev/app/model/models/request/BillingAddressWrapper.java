package com.foxkiev.app.model.models.request;

public class BillingAddressWrapper {

    private final BillingAddress address;
    private final boolean useForShipping = true;

    public BillingAddressWrapper(String email, String firstname, String lastname, String telephone, String city, String warehouse) {
        address = new BillingAddress(email, firstname, lastname, telephone, city, warehouse);
    }
}
