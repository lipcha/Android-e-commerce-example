package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 08.05.18.
 */

public class CityListWrapper {

    private List<CityPaginationWrapper> data;

    public List<City> getCities() {
        if (data == null || data.isEmpty())
            return new ArrayList<>();

        return data.get(0).getCities();
    }

    private class CityPaginationWrapper{
        @SerializedName("Addresses")
        private List<City> cities;

        @SerializedName("TotalCount")
        private int totalCount;

        public List<City> getCities() {
            return cities == null || cities.isEmpty() ? new ArrayList<>() : cities;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }
}
