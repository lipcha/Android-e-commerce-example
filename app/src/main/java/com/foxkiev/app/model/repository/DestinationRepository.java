package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.Warehouse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lipcha on 08.05.18.
 */

public interface DestinationRepository {

    Observable<List<City>> findCity(String cityName);

    Observable<List<Warehouse>> findWarehouse(String cityRef);
}
