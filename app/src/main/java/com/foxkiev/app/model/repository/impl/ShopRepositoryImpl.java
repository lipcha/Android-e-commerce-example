package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.api.ShopApi;
import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.models.SearchProductsWrapper;
import com.foxkiev.app.model.repository.ShopRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by lipcha on 23.02.18.
 */

public class ShopRepositoryImpl implements ShopRepository{

    private final ShopApi mShopApi;

    public ShopRepositoryImpl(ShopApi shopApi) {
        this.mShopApi = shopApi;
    }

    @Override
    public Observable<Category> getCategories() {
        return mShopApi.getCategories();
    }

    @Override
    public Observable<List<ProductInfo>> getCategoryProducts(String categoryId) {
        return mShopApi.getCategoryProducts(categoryId);

    }

    @Override
    public Observable<List<ProductInfo>> getProductsByQuery(String searchQuery) {
        return mShopApi.getProductsByQuery(createQueryFromCategoryProducts(searchQuery))
                .map(SearchProductsWrapper::getItems);
    }

    @Override
    public Observable<List<ProductInfo>> getProductsBySkuList(List<String> skuList) {

        final Map<String, String> searchQuery = new HashMap<>();
        for (int i = 0; i < skuList.size(); i++){
            // TODO: 15.08.18 remove if statement below
            if (!skuList.get(i).contains(" ")) {
                searchQuery.put("searchCriteria[filter_groups][0][filters][" + i + "][field]", "sku");
                searchQuery.put("searchCriteria[filter_groups][0][filters][" + i + "][value]", skuList.get(i));
            }
        }

        return mShopApi.getProductsByQuery(searchQuery)
                .map(SearchProductsWrapper::getItems);
    }

    @Override
    public Observable<Product> getProductBySku(String sku) {
        return mShopApi.getProductBySku(sku);
    }

    private Map<String, String> createQueryFromCategoryProducts(String queryString){
        final Map<String, String> query = new HashMap<>();

        for (int i = 0; i < Constants.SEARCH_PRODUCTS_FIELDS.length; i++){
            query.put("searchCriteria[filter_groups][0][filters][" + i + "][field]", Constants.SEARCH_PRODUCTS_FIELDS[i]);
            query.put("searchCriteria[filter_groups][0][filters][" + i + "][value]", "%25" + queryString + "%25");
            query.put("searchCriteria[filter_groups][0][filters][" + i + "][condition_type]", "like");
        }
        return query;
    }
}
