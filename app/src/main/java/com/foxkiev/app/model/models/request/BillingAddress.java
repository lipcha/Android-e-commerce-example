package com.foxkiev.app.model.models.request;

import java.util.ArrayList;
import java.util.List;

class BillingAddress {

    private final String email;
    private final String firstname;
    private final String lastname;
    private final String telephone;
    private final String city;
    private final List<String> street;
    private final String company = "";
    private final String postcode = "";
    private final String region = "";

    private final String countryId =  "UA";
    private final int saveInAddressBook = 1;
    private final int sameAsBilling = 1;

    public BillingAddress(String email, String firstname, String lastname, String telephone, String city, String warehouse) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.city = city;
        this.street = new ArrayList<>();
        street.add(warehouse);
    }
}