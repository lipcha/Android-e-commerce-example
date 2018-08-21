package com.foxkiev.app.model.models.query;

import android.text.TextUtils;

import com.foxkiev.app.model.models.Warehouse;

/**
 * Created by lipcha on 09.05.18.
 */

public class WarehouseQuery implements Query<Warehouse> {

    private final String text;

    public WarehouseQuery(String text) {
        this.text = text;
    }

    @Override
    public boolean contains(Warehouse model) {
        if (TextUtils.isEmpty(text))
            return true;
        return model.getDescription().toLowerCase().contains(text.toLowerCase());
    }
}
