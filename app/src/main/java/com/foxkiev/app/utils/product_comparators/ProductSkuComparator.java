package com.foxkiev.app.utils.product_comparators;

import com.foxkiev.app.model.models.ProductInfo;

import java.util.Comparator;

public class ProductSkuComparator implements Comparator<ProductInfo>{

    @Override
    public int compare(ProductInfo o1, ProductInfo o2) {
        return o1.getSku().compareToIgnoreCase(o2.getSku());
    }
}
