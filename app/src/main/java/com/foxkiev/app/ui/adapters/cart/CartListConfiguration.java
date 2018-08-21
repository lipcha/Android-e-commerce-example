package com.foxkiev.app.ui.adapters.cart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.ListConfiguration;

/**
 * Created by lipcha on 26.04.18.
 */

public class CartListConfiguration implements ListConfiguration{

    private final Context mContext;

    public CartListConfiguration(Context context) {
        this.mContext = context;
    }

    @Override
    public int getEmptyStateIcon() {
        return R.drawable.ic_cart_empty;
    }

    @Override
    public int getEmptyStateMessage() {
        return R.string.empty_state_cart;
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
