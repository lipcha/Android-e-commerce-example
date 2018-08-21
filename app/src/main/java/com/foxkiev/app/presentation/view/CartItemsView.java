package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.base.list.MvpListView;
import com.foxkiev.app.model.models.CartTotal;

/**
 * Created by lipcha on 01.05.18.
 */

public interface CartItemsView extends MvpListView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderCartTotal(CartTotal cartTotal);

    @StateStrategyType(SkipStrategy.class)
    void onEditCartItemError(Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void navigateToCheckout();

    @StateStrategyType(SkipStrategy.class)
    void navigateToAuthenticate();

}
