package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.presentation.view.CartTotalView;

import javax.inject.Inject;

/**
 * Created by lipcha on 23.04.18.
 */

@InjectViewState
public class CartTotalPresenter extends BasePresenter<CartTotalView> {

    @Inject
    CartRepository mCartRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        updateCartItemsCount();
    }

    public void updateCartItemsCount(){
        getExecutor().execute(mCartRepository.getCartTotal(), cartTotal -> getViewState().setCartItemsCount(cartTotal.getItemsQty()));
    }
}
