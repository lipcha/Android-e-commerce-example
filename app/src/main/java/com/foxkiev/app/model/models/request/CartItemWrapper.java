package com.foxkiev.app.model.models.request;


/**
 * Created by lipcha on 03.04.18.
 */

public class CartItemWrapper {

    private CartItem cartItem;

    public CartItemWrapper(String quoteId, String sku, int qty) {
        cartItem = new CartItem(quoteId, sku, qty);
    }

    private static class CartItem {

        private String quoteId;

        private String sku;

        private int qty;

        public CartItem(String quoteId, String sku, int qty) {
            this.quoteId = quoteId;
            this.sku = sku;
            this.qty = qty;
        }
    }
}
