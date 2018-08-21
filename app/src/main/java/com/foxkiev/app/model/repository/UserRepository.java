package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.utils.Optional;

import io.reactivex.Observable;

/**
 * Created by lipcha on 22.02.18.
 */

public interface UserRepository {

    Observable<Customer> signIn(String email, String password);

    Observable<Customer> createCustomer(String email, String firstName, String lastName, String password);

    Observable<String> changePassword(String password, String newPassword);

    Observable<Boolean> isAuthorized();

    Observable<Optional<Customer>> getCustomerMy();

    Observable<Boolean> logout();

    Observable<String> resetPassword(String email);
}
