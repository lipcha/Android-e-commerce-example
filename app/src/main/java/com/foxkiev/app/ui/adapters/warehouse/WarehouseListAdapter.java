package com.foxkiev.app.ui.adapters.warehouse;

import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.Warehouse;

/**
 * Created by lipcha on 09.05.18.
 */

public class WarehouseListAdapter extends BaseAdapter{

    public WarehouseListAdapter(DataProvider<Warehouse> dataProvider) {
        super(dataProvider);
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_warehouse;
    }

    @Override
    protected BaseViewHolder<Warehouse> getViewHolder(View itemView, int itemType) {
        return new WarehouseViewHolder(itemView);
    }
}
