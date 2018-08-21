package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lipcha on 08.05.18.
 */

public class City implements IdModel, Serializable {

    @SerializedName("Present")
    private String present;

    @SerializedName("Warehouses")
    private String warehouses;

    @SerializedName("MainDescription")
    private String mainDescription;

    @SerializedName("Areaa")
    private String areaa;

    @SerializedName("Region")
    private String region;

    @SerializedName("SettlementTypeCode")
    private String settlementTypeCode;

    @SerializedName("Ref")
    private String ref;

    @SerializedName("DeliveryCity")
    private String deliveryCity;

    @SerializedName("StreetsAvailability")
    private String streetsAvailability;

    @SerializedName("ParentRegionTypes")
    private String parentRegionTypes;

    @SerializedName("ParentRegionCode")
    private String parentRegionCode;

    @SerializedName("RegionTypes")
    private String regionTypes;

    @SerializedName("RegionTypesCode")
    private String RegionTypesCode;

    public City(String present) {
        this.present = present;
    }

    @Override
    public String getId() {
        return ref;
    }

    public String getPresent() {
        return present;
    }

    public String getRef() {
        return ref;
    }
}
