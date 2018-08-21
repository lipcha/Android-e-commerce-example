package com.foxkiev.app.ui.adapters.my_orders;

import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.MyOrderItem;

public class MyOrdersListAdapter extends BaseAdapter{

    private final MvpDelegate mvpDelegate;

    public MyOrdersListAdapter(DataProvider<MyOrderItem> dataProvider, MvpDelegate mvpDelegate) {
        super(dataProvider);
        this.mvpDelegate = mvpDelegate;
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_my_order;
    }

    @Override
    protected BaseViewHolder<MyOrderItem> getViewHolder(View itemView, int itemType) {
        return new MyOrderViewHolder(itemView, mvpDelegate);
    }
}
