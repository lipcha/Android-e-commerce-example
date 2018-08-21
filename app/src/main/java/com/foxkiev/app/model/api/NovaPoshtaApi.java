package com.foxkiev.app.model.api;

import com.foxkiev.app.model.models.CityListWrapper;
import com.foxkiev.app.model.models.WarehouseListWrapper;
import com.foxkiev.app.model.models.request.NovaPoshtaCityRequest;
import com.foxkiev.app.model.models.request.NovaPoshtaWarehouseRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by lipcha on 08.05.18.
 */

public interface NovaPoshtaApi {

    @POST
    Observable<CityListWrapper> findCity(@Url String url, @Body NovaPoshtaCityRequest request);

    @POST
    Observable<WarehouseListWrapper> findWarehouse(@Url String url, @Body NovaPoshtaWarehouseRequest request);
}
