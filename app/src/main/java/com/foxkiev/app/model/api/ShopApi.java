package com.foxkiev.app.model.api;

import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.models.SearchProductsWrapper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by lipcha on 23.02.18.
 */

public interface ShopApi {

    @GET("categories")
    Observable<Category> getCategories();

    @GET("categories/{categoryId}/products")
    Observable<List<ProductInfo>> getCategoryProducts(@Path("categoryId") String categoryID);

    @GET("products")
    Observable<SearchProductsWrapper> getProductsByQuery(@QueryMap(encoded = true) Map<String, String> query);


    @GET("products/{productSku}")
    Observable<Product> getProductBySku(@Path("productSku") String productSku);


}
