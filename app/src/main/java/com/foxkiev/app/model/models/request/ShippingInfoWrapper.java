package com.foxkiev.app.model.models.request;

/**
 * Created by lipcha on 14.05.18.
 */

public class ShippingInfoWrapper {

    private AddressInfo addressInformation;

    public ShippingInfoWrapper(String email, String firstname, String lastname, String telephone, String city, String warehouse, String carrierCode) {
        addressInformation = new AddressInfo(email, firstname, lastname, telephone, city, warehouse, carrierCode);
    }

    private static final class AddressInfo{
        private final BillingAddress shippingAddress;
        private final BillingAddress billingAddress;

        private final String shipping_method_code;
        private final String shipping_carrier_code;

        public AddressInfo(String email, String firstname, String lastname, String telephone, String city, String warehouse, String carrierCode) {
            shippingAddress = new BillingAddress(email, firstname, lastname, telephone, city, warehouse);
            billingAddress = new BillingAddress(email, firstname, lastname, telephone, city, warehouse);
            shipping_carrier_code = carrierCode;
            shipping_method_code = carrierCode;
        }
    }

}
