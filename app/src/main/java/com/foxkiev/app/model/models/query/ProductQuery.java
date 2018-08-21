package com.foxkiev.app.model.models.query;

import android.text.TextUtils;

import com.foxkiev.app.model.models.ProductInfo;

/**
 * Created by lipcha on 26.04.18.
 */

public class ProductQuery implements Query<ProductInfo> {

    private final String text;

    public ProductQuery(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean contains(ProductInfo model) {
        if (TextUtils.isEmpty(text))
            return true;
        return model.getName().toLowerCase().contains(text.toLowerCase());
    }
}
