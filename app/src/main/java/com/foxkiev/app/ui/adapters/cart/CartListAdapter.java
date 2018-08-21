package com.foxkiev.app.ui.adapters.cart;

import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.CartItem;

/**
 * Created by lipcha on 06.04.18.
 */

public class CartListAdapter extends BaseAdapter {

    private final CartItemViewCallback mViewCallback;
    private final MvpDelegate mvpDelegate;

    public CartListAdapter(DataProvider<CartItem> dataProvider, CartItemViewCallback callback, MvpDelegate mvpDelegate) {
        super(dataProvider);
        mViewCallback = callback;
        this.mvpDelegate = mvpDelegate;
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_cart;
    }

    @Override
    protected CartItemViewHolder getViewHolder(View itemView, int itemType) {
        return new CartItemViewHolder(itemView, mViewCallback, mvpDelegate);
    }
}
