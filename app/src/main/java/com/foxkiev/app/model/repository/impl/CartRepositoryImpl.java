package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.CartApi;
import com.foxkiev.app.model.models.Cart;
import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.models.CartTotal;
import com.foxkiev.app.model.models.request.CartItemWrapper;
import com.foxkiev.app.model.models.request.TransferGuestCartWrapper;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.utils.Optional;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * Created by lipcha on 03.04.18.
 */

public class CartRepositoryImpl implements CartRepository {

    private final CartApi mCartApi;
    private final PreferenceStorage mPreferenceStorage;

    public CartRepositoryImpl(CartApi cartApi, PreferenceStorage preferenceStorage) {
        this.mCartApi = cartApi;
        this.mPreferenceStorage = preferenceStorage;
    }

    @Override
    public Observable<CartItem> addItemToCart(String productSky, int qty) {
        return getCartId()
                .flatMap(cartId -> {
                    final CartItemWrapper cartItemWrapper = new CartItemWrapper(cartId, productSky, qty);
                    return isAuthorized()
                            .flatMap(authorized -> authorized ?
                                    mCartApi.addProductToCustomerCart(cartItemWrapper) :
                                    mCartApi.addProductToGuestCart(cartId, cartItemWrapper)
                            );
                });
    }

    @Override
    public Observable<CartItem> editCartItem(String itemId, String productSky, int qty) {

        return getCartId()
                .flatMap(cartId -> {
                    final CartItemWrapper itemWrapper = new CartItemWrapper(cartId, productSky, qty);
                    return isAuthorized()
                            .flatMap(authorized -> authorized ?
                                    mCartApi.editCustomerCartProduct(itemId, itemWrapper) :
                                    mCartApi.editGuestCartProduct(cartId, itemId, itemWrapper)
                            );
                });

    }

    @Override
    public Observable<Boolean> removeCartItem(String itemId) {
        return getCartId()
                .flatMap(cartId -> isAuthorized()
                        .flatMap(authorized -> authorized ?
                                mCartApi.removeProductFromCustomerCart(itemId) :
                                mCartApi.removeProductFromGuestCart(cartId, itemId)
                        )
                );
    }

    @Override
    public Observable<CartTotal> getCartTotal() {
        return getCartId()
                .flatMap(cartId -> isAuthorized()
                        .flatMap(authorized -> authorized ?
                                mCartApi.getCustomerCartTotal() :
                                mCartApi.getGuestCartTotal(cartId)
                        )
                );

    }

    @Override
    public Observable<List<String>> transferGuestCart() {
        return getGuestCartId()
                .flatMap(mCartApi::getGuestCart)
                .flatMap(guestCard -> getCustomerCartId()
                        .flatMap(customerCartId -> mPreferenceStorage.getCustomer()
                                .map(Optional::get)
                                .flatMap(customer -> mCartApi.transferGuestCart(new TransferGuestCartWrapper(guestCard.getId(), customer.getId())))
                                .map(strings -> {
                                    mPreferenceStorage.clearGuestCart();
                                    return strings;
                                })
                        )
                );
    }

    private Observable<Boolean> isAuthorized() {
        return mPreferenceStorage.getAccessToken()
                .map(Optional::isPresent);
    }

    private Observable<String> getCartId() {
        return isAuthorized()
                .flatMap(authorized -> authorized ? getCustomerCartId() : getGuestCartId());
    }

    private Observable<String> getCustomerCartId(){
        return mCartApi.getCustomerCartId()
                .onErrorResumeNext(throwable -> {
                    return mCartApi.createCustomerCart()
                            .flatMap(s -> mCartApi.getCustomerCartId());
                })
                .map(cart -> String.valueOf(cart.getId()));
//        return mPreferenceStorage.getCustomerCartId()
//                .flatMap(optionalCartId -> {
//                    if (!optionalCartId.isPresent())
//                        return mCartApi.createCustomerCart()
//                                .map(id -> {
//                                    mPreferenceStorage.storeCustomerCartId(id);
//                                    return id;
//                                });
//                    else return Observable.just(optionalCartId.get());
//                });
    }

    private Observable<String> getGuestCartId(){
        return mPreferenceStorage.getGuestCartId()
                .flatMap(optionalCartId -> {
                    if (!optionalCartId.isPresent())
                        return mCartApi.createGuestCart()
                                .map(id -> {
                                    mPreferenceStorage.storeGustCartId(id);
                                    return id;
                                });
                    else return Observable.just(optionalCartId.get());
                });
    }

}
