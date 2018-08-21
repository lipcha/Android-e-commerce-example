package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 03.04.18.
 */

public class Cart {

    private int id;

    public int getId() {
        return id;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isCustomerIsGuest() {
        return customerIsGuest;
    }

    public boolean isCustomerNoteNotify() {
        return customerNoteNotify;
    }

    public int getCustomerTaxClassId() {
        return customerTaxClassId;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public int getItemsQty() {
        return itemsQty;
    }

    public int getOrigOrderId() {
        return origOrderId;
    }

    public int getStoreId() {
        return storeId;
    }

    @SerializedName("billing_address")
    private Address billingAddress;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    private Currency currency;

    private Customer customer;

    @SerializedName("customer_is_guest")
    private boolean customerIsGuest;

    @SerializedName("customer_note_notify")
    private boolean customerNoteNotify;

    @SerializedName("customer_tax_class_id")
    private int customerTaxClassId;

    @SerializedName("is_active")
    private boolean isActive;

    @SerializedName("is_virtual")
    private boolean isVirtual;

    private List<CartItem> items;

    @SerializedName("items_count")
    private int itemsCount;

    @SerializedName("items_qty")
    private int itemsQty;

    @SerializedName("orig_order_id")
    private int origOrderId;

    @SerializedName("store_id")
    private int storeId;
}
