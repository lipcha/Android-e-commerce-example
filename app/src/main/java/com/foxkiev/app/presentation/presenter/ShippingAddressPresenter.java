package com.foxkiev.app.presentation.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.model.repository.CheckoutRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.presentation.view.ShippingAddressView;
import com.foxkiev.app.utils.validation.Validator;

import javax.inject.Inject;

import retrofit2.HttpException;

/**
 * Created by lipcha on 11.05.18.
 */

@InjectViewState
public class ShippingAddressPresenter extends BasePresenter<ShippingAddressView> {

    @Inject
    CheckoutRepository mCheckoutRepository;

    @Inject
    PreferenceStorage mPreferenceStorage;

    private City mCity;

    private Warehouse mWarehouse;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        refresh();
    }

    public void refresh(){
        getAddress();
        getCustomer();
    }

    public void getAddress(){
        getExecutor().execute(mCheckoutRepository.getShippingAddress(), this::onLoadAddressSuccess, throwable -> {
            if (throwable instanceof HttpException && ((HttpException) throwable).code() == 401){
                getViewState().showAuthentication();
            }else {
                getViewState().onLoadAddressFailed(throwable);
            }

        });
    }

    private void onLoadAddressSuccess(Address address){
        getViewState().renderShippingAddress(address);
        if (!TextUtils.isEmpty(address.getCity())) {
            setCity(new City(address.getCity()));
        }

        if (address.getStreet() != null && !address.getStreet().isEmpty()){
            setWarehouse(new Warehouse(address.getStreet().get(0)));
        }
    }

    private void getCustomer(){
        getExecutor().execute(mPreferenceStorage.getCustomer(), optionalCustomer -> {
            if (optionalCustomer.isPresent())
                getViewState().fillCustomerInformation(optionalCustomer.get());
        });
    }

    public void setCity(City city){
        mCity = city;
        mWarehouse = null;
        getViewState().renderCity(city);
    }

    public void setWarehouse(Warehouse warehouse){
        mWarehouse = warehouse;
        getViewState().renderWarehouse(warehouse);
    }

    public void onWarehouseClick(){
        if (mCity == null || TextUtils.isEmpty(mCity.getRef())){
            getViewState().showNotValidFieldError(FieldId.CITY);
            return;
        }
        getViewState().selectWarehouse(mCity);
    }

    public void onNext(String email, String firstName, String lastName, String phoneNumber) {
        Validator.create()
                .addNotEmptyField(email, FieldId.EMAIL_EMPTY)
                .addEmailField(email, FieldId.EMAIL)
                .addNotEmptyField(firstName, FieldId.FIRST_NAME)
                .addNotEmptyField(lastName, FieldId.LAST_NAME)
                .addPhoneNumberField(phoneNumber, FieldId.PHONE_NUMBER)
                .addNotNullObject(mCity, FieldId.CITY)
                .addNotNullObject(mWarehouse, FieldId.WAREHOUSE)
                .validate(() -> applyShippingAddress(email, firstName, lastName, phoneNumber), fieldId -> getViewState().showNotValidFieldError(fieldId));
    }

    private void applyShippingAddress(String email, String firstName, String lastName, String phoneNumber){
        getViewState().beforeApplyAddress();
        getExecutor().execute(
                mCheckoutRepository.setAddress(email, firstName, lastName, phoneNumber, mCity.getPresent(), mWarehouse.getDescription()),
                getViewState()::onApplyAddressSuccessSuccess,
                throwable -> getViewState().onApplyAddressFailed(throwable)
        );
    }
}
