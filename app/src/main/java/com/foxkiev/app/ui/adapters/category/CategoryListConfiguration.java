package com.foxkiev.app.ui.adapters.category;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foxkiev.app.base.list.ListConfiguration;

/**
 * Created by lipcha on 26.04.18.
 */

public class CategoryListConfiguration implements ListConfiguration {

    private final Context mContext;

    public CategoryListConfiguration(Context context) {
        this.mContext = context;
    }

    @Override
    public int getEmptyStateIcon() {
        return 0;
    }

    @Override
    public int getEmptyStateMessage() {
        return 0;
    }

    @Override
    public int getSearchEmptyStateIcon() {
        return 0;
    }

    @Override
    public int getSearchEmptyStateMessage() {
        return 0;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext, 3);
    }
}
