package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.base.list.MvpListView;
import com.foxkiev.app.model.models.FilterModel;

/**
 * Created by lipcha on 28.02.18.
 */

public interface ProductListView extends MvpListView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderFilter(FilterModel filterModel);
}
