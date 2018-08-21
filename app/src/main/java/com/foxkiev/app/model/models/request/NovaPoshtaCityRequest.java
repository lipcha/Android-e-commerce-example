package com.foxkiev.app.model.models.request;

import com.foxkiev.app.Constants;

/**
 * Created by lipcha on 08.05.18.
 */

public class NovaPoshtaCityRequest {

    private final String apiKey = Constants.API.NOVA_POSHTA_API_KEY;
    private final String modelName;
    private final String calledMethod;
    private final Property methodProperties;

    public static NovaPoshtaCityRequest forCityName(final String cityName){
        return new NovaPoshtaCityRequest("Address", "searchSettlements", cityName);
    }

    private NovaPoshtaCityRequest(String modelName, String calledMethod, final String cityName) {
        methodProperties = new Property(cityName);
        this.modelName = modelName;
        this.calledMethod = calledMethod;
    }

    private static class Property{
        private String CityName;
        private int Limit;
        private final String Language = "ru";

        public Property(String cityName) {
            CityName = cityName;
            Limit = 40;
        }
    }
}
