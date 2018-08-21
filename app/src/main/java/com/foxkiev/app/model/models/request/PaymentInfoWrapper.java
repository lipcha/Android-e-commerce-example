package com.foxkiev.app.model.models.request;

public class PaymentInfoWrapper {

    private final PaymentMethod paymentMethod;
    private final ShippingMethod shippingMethod;

    public PaymentInfoWrapper(final String carrierCode, final String paymentMethod) {
        this.paymentMethod = new PaymentMethod(paymentMethod);
        this.shippingMethod = new ShippingMethod(carrierCode);
    }

    private static class PaymentMethod{
        private final String method;

        public PaymentMethod(String method) {
            this.method = method;
        }
    }

    private static class ShippingMethod{
        private final String method_code;
        private final String carrier_code;

        public ShippingMethod(final String carrierCode) {
            this.method_code = carrierCode;
            this.carrier_code = carrierCode;
        }
    }
}
