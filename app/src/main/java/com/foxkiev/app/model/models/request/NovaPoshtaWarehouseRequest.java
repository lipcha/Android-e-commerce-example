package com.foxkiev.app.model.models.request;

import com.foxkiev.app.Constants;

/**
 * Created by lipcha on 09.05.18.
 */

public class NovaPoshtaWarehouseRequest {

    private final String apiKey = Constants.API.NOVA_POSHTA_API_KEY;
    private final String modelName;
    private final String calledMethod;
    private final NovaPoshtaWarehouseRequest.Property methodProperties;

    public static NovaPoshtaWarehouseRequest fromCity(final String cityRef){
        return new NovaPoshtaWarehouseRequest("AddressGeneral", "getWarehouses", cityRef);
    }

    private NovaPoshtaWarehouseRequest(String modelName, String calledMethod, final String cityName) {
        methodProperties = new NovaPoshtaWarehouseRequest.Property(cityName);
        this.modelName = modelName;
        this.calledMethod = calledMethod;
    }

    private static class Property{
        private String SettlementRef;
//        private int Limit;
        private final String Language = "ru";

        public Property(String cityRef) {
            SettlementRef = cityRef;
//            Limit = 40;
        }
    }
}
