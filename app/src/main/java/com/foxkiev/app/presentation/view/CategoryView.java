package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.base.list.MvpListView;
import com.foxkiev.app.model.models.Category;

import java.util.List;

/**
 * Created by lipcha on 23.02.18.
 */

public interface CategoryView extends MvpListView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderCategoryGroups(List<Category> categories);
}
