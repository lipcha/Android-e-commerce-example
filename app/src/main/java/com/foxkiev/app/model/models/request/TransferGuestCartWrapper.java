package com.foxkiev.app.model.models.request;

import com.google.gson.annotations.SerializedName;

public class TransferGuestCartWrapper {

    private final Quote quote;

    public TransferGuestCartWrapper(int guestCartId, int customerId) {
        quote = new Quote(guestCartId, customerId);
    }

    private static class Quote {

        @SerializedName("id")
        private int guestCartId;

        private Customer customer;

        public Quote(int guestCartId, int customerId) {
            this.guestCartId = guestCartId;
            this.customer = new Customer(customerId);
        }
    }

    private static class Customer {
        private int id;

        public Customer(int id) {
            this.id = id;
        }
    }
}
