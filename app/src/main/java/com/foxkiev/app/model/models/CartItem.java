package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 03.04.18.
 */

public class CartItem implements IdModel {

    @SerializedName("quote_id")
    private String quoteId;

    private String sku;

    private int qty;

    @SerializedName("product_type")
    private String productType;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("item_id")
    private String itemId;

    private String name;

    private float price;

    @SerializedName("base_discount_amount")
    private float baseDiscountAmount;

    @SerializedName("base_price")
    private float basePrice;

    @SerializedName("base_price_incl_tax")
    private float basePriceInclTax;

    @SerializedName("base_row_total")
    private float baseRowTotal;

    @SerializedName("base_row_total_incl_tax")
    private float baseRowTotalInclTax;

    @SerializedName("base_tax_amount")
    private float baseTaxAmount;

    @SerializedName("discount_amount")
    private float discountAmount;

    @SerializedName("discount_percent")
    private float discountPercent;

    @SerializedName("price_incl_tax")
    private float priceInclTax;

    @SerializedName("row_total")
    private float rowTotal;

    @SerializedName("row_total_incl_tax")
    private float rowTotalInclTax;

    @SerializedName("row_total_with_discount")
    private float rowTotalWithDiscount;

    @SerializedName("tax_amount")
    private float taxAmount;

    @SerializedName("tax_percent")
    private float taxPercent;

    @SerializedName("weee_tax_applied")
    private String weeeTaxApplied;

    @SerializedName("weee_tax_applied_amount")
    private String weeeTaxAppliedAmount;

    private boolean updateCartItemInProgress;

    private boolean removeCArtItemInProgress;

    public String getQuoteId() {
        return quoteId;
    }

    public String getSku() {
        return sku;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getProductType() {
        return productType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String getId() {
        return itemId;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public float getPriceInclTax() {
        return priceInclTax;
    }

    public float getRowTotal() {
        return rowTotal;
    }

    public float getRowTotalInclTax() {
        return rowTotalInclTax;
    }

    public float getRowTotalWithDiscount() {
        return rowTotalWithDiscount;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public float getTaxPercent() {
        return taxPercent;
    }

    public String getWeeeTaxApplied() {
        return weeeTaxApplied;
    }

    public String getWeeeTaxAppliedAmount() {
        return weeeTaxAppliedAmount;
    }

    public boolean isUpdateCartItemInProgress() {
        return updateCartItemInProgress;
    }

    public void setUpdateCartItemInProgress(boolean updateCartItemInProgress) {
        this.updateCartItemInProgress = updateCartItemInProgress;
    }

    public boolean isRemoveCArtItemInProgress() {
        return removeCArtItemInProgress;
    }

    public void setRemoveCArtItemInProgress(boolean removeCArtItemInProgress) {
        this.removeCArtItemInProgress = removeCArtItemInProgress;
    }
}
