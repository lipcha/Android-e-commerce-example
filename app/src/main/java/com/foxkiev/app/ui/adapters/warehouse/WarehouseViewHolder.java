package com.foxkiev.app.ui.adapters.warehouse;

import android.view.View;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.model.models.query.Query;

import butterknife.BindView;

/**
 * Created by lipcha on 09.05.18.
 */

public class WarehouseViewHolder extends BaseViewHolder<Warehouse> {

    @BindView(R.id.tvWarehouseName)
    TextView tvWarehouseName;

    public WarehouseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setupHolder(Warehouse warehouse, int position, Query<Warehouse> query) {
        tvWarehouseName.setText(warehouse.getDescription());
    }
}
