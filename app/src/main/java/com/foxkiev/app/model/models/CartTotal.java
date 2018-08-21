package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 23.04.18.
 */

public class CartTotal {

    @SerializedName("items_qty")
    private int itemsQty;

    @SerializedName("base_currency_code")
    private String base_currency_code;// "EUR"\
    private float base_discount_amount;
    private float base_grand_total;
    private float base_shipping_amount;
    private float base_shipping_discount_amount;
    private float base_shipping_incl_tax;
    private float base_shipping_tax_amount;
    private float base_subtotal;
    private float base_subtotal_with_discount;
    private float base_tax_amount;

    @SerializedName("discount_amount")
    private float discountAmount;

    @SerializedName("grand_total")
    private float grandTotal;

    @SerializedName("quote_currency_code")
    private String quoteCurrencyCode;

    @SerializedName("shipping_amount")
    private float shippingAmount;

    @SerializedName("shipping_discount_amount")
    private float shippingDiscountAmount;

    @SerializedName("shipping_incl_tax")
    private float shippingInclTax;

    @SerializedName("shipping_tax_amount")
    private float shippingTaxAmount;

    private float subtotal;

    @SerializedName("subtotal_incl_tax")
    private float subtotalInclTax;

    @SerializedName("subtotal_with_discount")
    private float subtotalWithDiscount;

    @SerializedName("tax_amount")
    private float taxAmount;

    @SerializedName("total_segments")
    private List<CartSegment> totalSegments;

    @SerializedName("weee_tax_applied_amount")
    private String weeeTaxAppliedAmount;

    private List<CartItem> items;

    public int getItemsQty() {
        return itemsQty;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public String getQuoteCurrencyCode() {
        return quoteCurrencyCode;
    }

    public float getShippingAmount() {
        return shippingAmount;
    }

    public float getShippingDiscountAmount() {
        return shippingDiscountAmount;
    }

    public float getShippingInclTax() {
        return shippingInclTax;
    }

    public float getShippingTaxAmount() {
        return shippingTaxAmount;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public float getSubtotalInclTax() {
        return subtotalInclTax;
    }

    public float getSubtotalWithDiscount() {
        return subtotalWithDiscount;
    }

    public float getTaxAmount() {
        return taxAmount;
    }

    public String getWeeeTaxAppliedAmount() {
        return weeeTaxAppliedAmount;
    }

    public List<CartSegment> getTotalSegments() {
        return totalSegments;
    }
}
