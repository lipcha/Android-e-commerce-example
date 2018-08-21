package com.foxkiev.app.ui.adapters.cart;

import com.foxkiev.app.model.models.CartItem;

/**
 * Created by lipcha on 17.04.18.
 */

public interface CartItemViewCallback {

    void onClickRemoveCartItem(CartItem cartItem);
    void onClickPlusItemCount(CartItem cartItem);
    void onClickMinusItemCount(CartItem cartItem);
    void onClickEditItemCount(CartItem cartItem);
}
