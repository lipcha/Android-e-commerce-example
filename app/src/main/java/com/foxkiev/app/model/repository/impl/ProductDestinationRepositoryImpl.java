package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.api.NovaPoshtaApi;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.CityListWrapper;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.model.models.WarehouseListWrapper;
import com.foxkiev.app.model.models.request.NovaPoshtaCityRequest;
import com.foxkiev.app.model.models.request.NovaPoshtaWarehouseRequest;
import com.foxkiev.app.model.repository.DestinationRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lipcha on 08.05.18.
 */

public class ProductDestinationRepositoryImpl implements DestinationRepository {

    private final NovaPoshtaApi mNovaPoshtaApi;

    public ProductDestinationRepositoryImpl(NovaPoshtaApi novaPoshtaApi) {
        mNovaPoshtaApi = novaPoshtaApi;
    }

    @Override
    public Observable<List<City>> findCity(String cityName) {
        return mNovaPoshtaApi.findCity(Constants.API.NOVA_POSHTA_BASE_URL, NovaPoshtaCityRequest.forCityName(cityName))
                .map(CityListWrapper::getCities);
    }

    @Override
    public Observable<List<Warehouse>> findWarehouse(String cityRef) {
        return mNovaPoshtaApi.findWarehouse(Constants.API.NOVA_POSHTA_BASE_URL, NovaPoshtaWarehouseRequest.fromCity(cityRef))
                .map(WarehouseListWrapper::getWarehouses);
    }
}
