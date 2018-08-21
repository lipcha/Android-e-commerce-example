package com.foxkiev.app.model.api;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.request.ChangePasswordWrapper;
import com.foxkiev.app.model.models.request.CreateCustomerWrapper;
import com.foxkiev.app.model.models.request.Credentials;
import com.foxkiev.app.model.models.request.EmailWrapper;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HTTP;

/**
 * Created by lipcha on 22.02.18.
 */

public interface UserApi {

    @HTTP(method = "POST", path = "integration/customer/token", hasBody = true)
    Observable<String> signIn(@Body Credentials credentials);

    @HTTP(method = "POST", path = "customers", hasBody = true)
    Observable<Customer> signUp(@Body CreateCustomerWrapper wrapper);

    @HTTP(method = "GET", path = Constants.API.REQUESTS.GET_CUSTOMER)
    Observable<Customer> getCustomer();

    @HTTP(method = "PUT", path = "customers/password", hasBody = true)
    Observable<String> resetPassword(@Body EmailWrapper wrapper);

    @HTTP(method = "PUT", path = Constants.API.REQUESTS.CHANGE_PASSWORD, hasBody = true)
    Observable<String> changePassword(@Body ChangePasswordWrapper wrapper);

}
