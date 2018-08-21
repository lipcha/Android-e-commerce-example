package com.foxkiev.app.presentation.presenter;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductLink;
import com.foxkiev.app.model.repository.ShopRepository;
import com.foxkiev.app.presentation.view.ProductDetailView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lipcha on 28.02.18.
 */

@InjectViewState
public class ProductDetailPresenter extends BasePresenter<ProductDetailView> {

    @Inject
    ShopRepository mShopRepository;

    private final String mProductSku;

    public ProductDetailPresenter(String productSku) {
        App.getRepositoryComponent().inject(this);
        this.mProductSku = productSku;
    }

    @Override
    protected void onAttachView() {
        refreshProduct();
    }

    public void refreshProduct(){
        getViewState().beforeLoadProduct();
        getExecutor().execute(mShopRepository.getProductBySku(mProductSku), this::onLoadProductSuccess, this::onLoadProductFailed);
    }

    private void onLoadProductSuccess(final Product product){
        getViewState().renderProduct(product);
        loadRelatedProducts(product.getProductLinks());
    }

    private void onLoadProductFailed(final Throwable throwable){
        getViewState().showLoadProductError(throwable);
    }

    private void loadRelatedProducts(final List<ProductLink> productLinks){
        if (productLinks == null || productLinks.isEmpty())
            return;
        getExecutor().execute(
                Observable.fromIterable(productLinks)
                        .map(ProductLink::getLinkedProductSku)
                        .toList().toObservable()
                        .flatMap(skuList -> mShopRepository.getProductsBySkuList(skuList)),
                relatedProducts -> getViewState().renderRelatedProducts(relatedProducts),
                throwable -> {
                    Log.d("tag", throwable.toString());
                }
        );
    }
}
