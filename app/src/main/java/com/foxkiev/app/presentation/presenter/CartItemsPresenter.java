package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.Constants;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.presentation.view.CartItemsView;

import javax.inject.Inject;

/**
 * Created by lipcha on 06.04.18.
 */

@InjectViewState
public class CartItemsPresenter extends BaseListPresenter<CartItem, CartItemsView> {

    @Inject
    CartRepository mCartRepository;

    @Inject
    PreferenceStorage mPreferenceStorage;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        updateCart(true);
    }

    public void checkout(){
        getExecutor().execute(mPreferenceStorage.getCustomer(), optionalCustomer -> {
            if (optionalCustomer.isPresent())
                getViewState().navigateToCheckout();
            else getViewState().navigateToAuthenticate();
        });
    }

    public void updateCart(boolean refreshList){
        getExecutor().execute(mCartRepository.getCartTotal(), cart -> {
                    if (refreshList)
                        onLoadListSuccess(cart.getItems());
                    getViewState().renderCartTotal(cart);
                }, this::onLoadListFailed
        );
    }

    public void removeCartItem(CartItem cartItem) {
        cartItem.setRemoveCArtItemInProgress(true);
        updateItem(cartItem);
        getExecutor().execute(
                mCartRepository.removeCartItem(cartItem.getItemId()),
                removed -> {
                    updateCart(false);
                    if (removed)
                        removeItem(cartItem);
                },
                throwable -> {
                    cartItem.setRemoveCArtItemInProgress(false);
                    updateItem(cartItem);
                    getViewState().onEditCartItemError(throwable);
                }
        );
    }

    public void clearCart(){
        for (CartItem cartItem : getDataProvider().getData()){
            removeCartItem(cartItem);
        }
    }

    public void minusItemCount(String itemId, int qty) {
        final CartItem item = getDataById(itemId);
        if (qty == 1 || item == null)
            return;
        editCartItem(item, --qty);
    }

    public void plusItemCount(String itemId, int qty) {
        final CartItem item = getDataById(itemId);
        if(qty >= Constants.MAX_CART_PRODUCT || item == null)
            return;
        editCartItem(item, ++qty);
    }

    public void editCartItem(CartItem cartItem, int qty) {
        cartItem.setUpdateCartItemInProgress(true);
        updateItem(cartItem);
        getExecutor().execute(
                mCartRepository.editCartItem(cartItem.getItemId(), cartItem.getSku(), qty),
                editedItem ->{
                    updateItem(editedItem);
                    updateCart(false);
                },
                throwable -> {
                    cartItem.setUpdateCartItemInProgress(false);
                    updateItem(cartItem);
                    getViewState().onEditCartItemError(throwable);
                }
        );

    }
}
