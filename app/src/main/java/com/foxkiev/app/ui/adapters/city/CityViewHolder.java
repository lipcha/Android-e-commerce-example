package com.foxkiev.app.ui.adapters.city;

import android.view.View;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.query.Query;

import butterknife.BindView;

/**
 * Created by lipcha on 08.05.18.
 */

public class CityViewHolder extends BaseViewHolder<City> {

    @BindView(R.id.tvCityName)
    TextView tvCityName;

    public CityViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setupHolder(City city, int position, Query<City> query) {
        tvCityName.setText(city.getPresent());
    }
}
