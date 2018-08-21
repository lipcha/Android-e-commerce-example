package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 20.04.18.
 */

public class ExtensionAttributes {

    @SerializedName("stock_item")
    private StockItem stockItem;

    public StockItem getStockItem() {
        return stockItem;
    }
}
