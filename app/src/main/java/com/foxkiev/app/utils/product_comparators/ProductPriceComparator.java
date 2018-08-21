package com.foxkiev.app.utils.product_comparators;

import com.foxkiev.app.model.models.ProductInfo;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<ProductInfo>{

    @Override
    public int compare(ProductInfo o1, ProductInfo o2) {
        return Float.compare(o1.getPrice(), o2.getPrice());
    }
}
