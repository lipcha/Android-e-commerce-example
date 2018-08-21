package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 20.04.18.
 */

public class StockItem {

    private int backorders;

    @SerializedName("enable_qty_increments")
    private boolean enableQtyIncrements;

    @SerializedName("is_decimal_divided")
    private boolean isDecimalDivided;

    @SerializedName("is_in_stock")
    private boolean isInStock;

    @SerializedName("is_qty_decimal")
    private boolean isQtyDecimal;

    @SerializedName("item_id")
    private int itemId;

    @SerializedName("low_stock_date")
    private String lowStockDate;

    @SerializedName("manage_stock")
    private boolean manageStock;

    @SerializedName("max_sale_qty")
    private int maxSaleQty;

    @SerializedName("min_qty")
    private int minQty;

    @SerializedName("min_sale_qty")
    private int minSaleQty;

    @SerializedName("notify_stock_qty")
    private int notifyStockQty;

    @SerializedName("product_id")
    private int productId;

    private int qty;

    @SerializedName("qty_increments")
    private int qtyIncrements;

    @SerializedName("show_default_notification_message")
    private boolean showDefaultNotificationMessage;

    @SerializedName("stock_id")
    private int stockId;

    @SerializedName("stock_status_changed_auto")
    private int stockStatusChangedAuto;

    @SerializedName("use_config_backorders")
    private boolean useConfigBackorders;

    @SerializedName("use_config_enable_qty_inc")
    private boolean useConfigEnableQtyInc;

    @SerializedName("use_config_manage_stock")
    private boolean useConfigManageStock;

    @SerializedName("use_config_max_sale_qty")
    private boolean useConfigMaxSaleQty;

    @SerializedName("use_config_min_qty")
    private boolean useConfigMinqty;

    @SerializedName("use_config_min_sale_qty")
    private int useConfigMinSaleQty;

    @SerializedName("use_config_notify_stock_qty")
    private boolean useConfigNotifyStockQty;

    @SerializedName("use_config_qty_increments")
    private boolean useConfigQtyIncrements;

    public int getBackorders() {
        return backorders;
    }

    public boolean isEnableQtyIncrements() {
        return enableQtyIncrements;
    }

    public boolean isDecimalDivided() {
        return isDecimalDivided;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public boolean isQtyDecimal() {
        return isQtyDecimal;
    }

    public int getItemId() {
        return itemId;
    }

    public String getLowStockDate() {
        return lowStockDate;
    }

    public boolean isManageStock() {
        return manageStock;
    }

    public int getMaxSaleQty() {
        return maxSaleQty;
    }

    public int getMinQty() {
        return minQty;
    }

    public int getMinSaleQty() {
        return minSaleQty;
    }

    public int getNotifyStockQty() {
        return notifyStockQty;
    }

    public int getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public int getQtyIncrements() {
        return qtyIncrements;
    }

    public boolean isShowDefaultNotificationMessage() {
        return showDefaultNotificationMessage;
    }

    public int getStockId() {
        return stockId;
    }

    public int getStockStatusChangedAuto() {
        return stockStatusChangedAuto;
    }

    public boolean isUseConfigBackorders() {
        return useConfigBackorders;
    }

    public boolean isUseConfigEnableQtyInc() {
        return useConfigEnableQtyInc;
    }

    public boolean isUseConfigManageStock() {
        return useConfigManageStock;
    }

    public boolean isUseConfigMaxSaleQty() {
        return useConfigMaxSaleQty;
    }

    public boolean isUseConfigMinqty() {
        return useConfigMinqty;
    }

    public int isUseConfigMinSaleQty() {
        return useConfigMinSaleQty;
    }

    public boolean isUseConfigNotifyStockQty() {
        return useConfigNotifyStockQty;
    }

    public boolean isUseConfigQtyIncrements() {
        return useConfigQtyIncrements;
    }
}
