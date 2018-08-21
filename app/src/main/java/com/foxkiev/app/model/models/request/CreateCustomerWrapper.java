package com.foxkiev.app.model.models.request;

/**
 * Created by lipcha on 07.05.18.
 */

public class CreateCustomerWrapper {

    private Customer customer;
    private String password;

    public CreateCustomerWrapper (String email, String firstname, String lastname, String password){
        customer = new Customer(email, firstname, lastname);
        this.password = password;
    }


    private static class Customer{

        public Customer(String email, String firstname, String lastname) {
            this.email = email;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        private String email;
        private String firstname;
        private String lastname;
    }
}
