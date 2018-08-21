package com.foxkiev.app.base.list;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lipcha on 26.04.18.
 */

public interface ListConfiguration {

    @DrawableRes
    int getEmptyStateIcon();

    @StringRes
    int getEmptyStateMessage();

    @DrawableRes
    int getSearchEmptyStateIcon();

    @StringRes
    int getSearchEmptyStateMessage();

    RecyclerView.LayoutManager getLayoutManager();
}
