package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOrderItem implements IdModel {

    private String customer_group_id;

    private String base_discount_tax_compensation_amount;

    private String remote_ip;

    private String base_subtotal_incl_tax;

    private String base_discount_amount;

    private String billing_address_id;

    private String base_shipping_tax_amount;

    private String quote_id;

    @SerializedName("grand_total")
    private float grandTotal;

    private String store_name;

    private String store_currency_code;

    private String base_shipping_incl_tax;

    private String status;

    private String customer_email;

    private String store_id;

    private String customer_note_notify;

    private String subtotal;

    private String base_tax_amount;

    private String order_currency_code;

    private String discount_amount;

    private String base_subtotal;

    private String is_virtual;

    private String updated_at;

    private String protect_code;

    private String shipping_tax_amount;

    private List<CartItem> items;

    private String shipping_incl_tax;

    @SerializedName("base_grand_total")
    private float baseGrandTotal;

    private String customer_is_guest;

    private String store_to_order_rate;

    @SerializedName("billing_address")
    private Address billingAddress;

    @SerializedName("increment_id")
    private String incrementId;

    private String total_item_count;

    private String base_total_due;

    private String weight;

    private String base_shipping_discount_amount;

    private String base_shipping_amount;

    private String total_qty_ordered;

    private String discount_tax_compensation_amount;

    private String shipping_amount;

    private String base_currency_code;

    private String shipping_discount_amount;

    private String state;

    private String entity_id;

    private String base_shipping_discount_tax_compensation_amnt;

    @SerializedName("subtotal_incl_tax")
    private String subtotalInclTax;

    private String store_to_base_rate;

    private String applied_rule_ids;

    private String tax_amount;

    @SerializedName("created_at")
    private String createdAt;

    private String base_to_order_rate;

    private String global_currency_code;

//    private String payment;


    private String shipping_discount_tax_compensation_amount;

    private String base_to_global_rate;

    private String shipping_description;

    private String total_due;

    private String email_sent;

    @Override
    public String getId() {
        return incrementId;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public float getBaseGrandTotal() {
        return baseGrandTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }
}
