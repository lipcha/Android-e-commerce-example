package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 10.04.18.
 */

public class Address implements Serializable {

    private String id;

    private String city;

    @SerializedName("country_id")
    private String countryId;

    private String email;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("postcode")
    private String postCode;

    @SerializedName("region_code")
    private String regionCode;

    private String telephone;

    @SerializedName("region_id")
    private String regionId;

    @SerializedName("same_as_billing")
    private int sameAsBilling;

    @SerializedName("save_in_address_book")
    private int saveInAddressBook;

    private List<String> street;

    public Address() {
    }

    public Address(String id, String email, String firstname, String lastname, String telephone, String city, String warehouse) {
        this.id = id;
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
        this.telephone = telephone;
        this.city = city;
        this.street = new ArrayList<>();
        street.add(warehouse);
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getRegionId() {
        return regionId;
    }

    public int getSameAsBilling() {
        return sameAsBilling;
    }

    public int getSaveInAddressBook() {
        return saveInAddressBook;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<String> getStreet() {
        return street;
    }


}
