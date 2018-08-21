package com.foxkiev.app.ui.adapters.city;

import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.City;

/**
 * Created by lipcha on 08.05.18.
 */

public class CityListAdapter extends BaseAdapter {

    public CityListAdapter(DataProvider<City> dataProvider) {
        super(dataProvider);
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_city;
    }

    @Override
    protected CityViewHolder getViewHolder(View itemView, int itemType) {
        return new CityViewHolder(itemView);
    }
}
