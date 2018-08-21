package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 03.04.18.
 */

public interface CartView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void onAddToCartInProgress(String productSku);

    @StateStrategyType(SkipStrategy.class)
    void onAddToCartSuccess();

    @StateStrategyType(SkipStrategy.class)
    void onAddToCartError(String productSky, Throwable throwable);
}
