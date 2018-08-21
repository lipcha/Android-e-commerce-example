package com.foxkiev.app.base.list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 28.02.18.
 */

public interface MvpListView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void notifyDataSetChange();

    @StateStrategyType(SkipStrategy.class)
    void notifyDataRemoved(int position);

    @StateStrategyType(SkipStrategy.class)
    void notifyItemChanged(int position);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideLoadingProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoadingError(final Throwable throwable);
}
