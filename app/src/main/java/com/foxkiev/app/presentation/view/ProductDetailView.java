package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductInfo;

import java.util.List;

/**
 * Created by lipcha on 28.02.18.
 */

public interface ProductDetailView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void beforeLoadProduct();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadProductError(Throwable throwable);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderProduct(final Product product);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderRelatedProducts(List<ProductInfo> relatedProducts);
}
