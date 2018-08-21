package com.foxkiev.app.ui.adapters.warehouse;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foxkiev.app.base.list.ListConfiguration;

/**
 * Created by lipcha on 09.05.18.
 */

public class WarehouseListConfiguration implements ListConfiguration {
    private final Context mContext;

    public WarehouseListConfiguration(Context context) {
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
        return new LinearLayoutManager(mContext);
    }
}
