package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lipcha on 23.02.18.
 */

public interface ShopRepository {

    Observable<Category> getCategories();

    Observable<List<ProductInfo>> getCategoryProducts(final String categoryId);

    Observable<List<ProductInfo>> getProductsByQuery(String searchQuery);

    Observable<List<ProductInfo>> getProductsBySkuList(List<String> skuList);

    Observable<Product> getProductBySku(final String sku);
}
