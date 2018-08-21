package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.presentation.view.CartView;

import javax.inject.Inject;

/**
 * Created by lipcha on 03.04.18.
 */

@InjectViewState
public class CartPresenter extends BasePresenter<CartView> {

    @Inject
    CartRepository mCartRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void addProductToCart(final Product product, final int productCount){
        getViewState().onAddToCartInProgress(product.getSku());

        getExecutor().execute(
                mCartRepository.addItemToCart(product.getSku(), productCount),
                cartItem -> getViewState().onAddToCartSuccess(),
                throwable -> getViewState().onAddToCartError(product.getSku(), throwable)
        );
    }
}
