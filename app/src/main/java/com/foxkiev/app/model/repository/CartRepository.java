package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.models.CartTotal;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lipcha on 03.04.18.
 */

public interface CartRepository {

    Observable<CartItem> addItemToCart(String productSky, int qty);

    Observable<CartItem> editCartItem(String itemId, String productSky, int qty);

    Observable<Boolean> removeCartItem(String itemId);

    Observable<CartTotal> getCartTotal();

    Observable<List<String>> transferGuestCart();

}
