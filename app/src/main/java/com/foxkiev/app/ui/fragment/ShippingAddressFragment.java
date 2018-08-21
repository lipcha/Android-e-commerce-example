package com.foxkiev.app.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.presentation.presenter.ShippingAddressPresenter;
import com.foxkiev.app.presentation.view.ShippingAddressView;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.ui.custom_views.ErrorView;
import com.foxkiev.app.ui.custom_views.TopBanner;
import com.foxkiev.app.ui.custom_views.dialogs.ConfirmDialog;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lipcha on 02.05.18.
 */

public class ShippingAddressFragment extends BaseFragment implements ShippingAddressView {

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.errorView)
    ErrorView errorView;

    @BindView(R.id.llAddressContainer)
    View llAddressContainer;

    @BindView(R.id.etCustomerEmail)
    EditText etEmail;

    @BindView(R.id.etCustomerFirstName)
    EditText etFirstName;

    @BindView(R.id.etCustomerLastName)
    EditText etLastName;

    @BindView(R.id.etCustomerPhoneNumber)
    EditText etPhoneNumber;

    @BindView(R.id.tvCity)
    TextView tvCity;

    @BindView(R.id.tvWarehouse)
    TextView tvWarehouse;

    @BindView(R.id.tilEmailWrapper)
    TextInputLayout tilEmailWrapper;

    @BindView(R.id.tilFirstNameWrapper)
    TextInputLayout tilFirstNameWrapper;

    @BindView(R.id.tilLastNameWrapper)
    TextInputLayout tilLastNameWrapper;

    @BindView(R.id.tilPhoneNumberWrapper)
    TextInputLayout tilPhoneNumberWrapper;

    @InjectPresenter
    ShippingAddressPresenter mShippingAddressPresenter;

    public static BaseFragment newInstance(){
        return new ShippingAddressFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shipping_address;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        errorView.setOnReloadClickListener(v -> mShippingAddressPresenter.refresh());

        RxUtils.interceptTextChange(etFirstName, s -> tilFirstNameWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etLastName, s -> tilLastNameWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etEmail, s -> tilEmailWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etPhoneNumber, s -> tilPhoneNumberWrapper.setErrorEnabled(false));
    }

    @OnClick(R.id.tvCity)
    void onCityClick(View v){
        getRouter().addFragmentToBackStack(CityListFragment.newInstance(this));
    }

    @OnClick(R.id.tvWarehouse)
    void onWarehouseClick(View v){
        mShippingAddressPresenter.onWarehouseClick();
    }

    @OnClick(R.id.btnNext)
    void onNextClick(View v){
        mShippingAddressPresenter.onNext(etEmail.getText().toString(), etFirstName.getText().toString(), etLastName.getText().toString(), etPhoneNumber.getText().toString());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_shipping_address);
    }

    @Override
    public void beforeLoadingAddress() {
        progressBar.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        llAddressContainer.setVisibility(View.GONE);
    }

    @Override
    public void renderShippingAddress(Address address) {
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        llAddressContainer.setVisibility(View.VISIBLE);
        if (address == null)
            return;
        etFirstName.setText(address.getFirstName());
        etLastName.setText(address.getLastName());
//        etEmail.setText(address.getEmail());
        etPhoneNumber.setText(address.getTelephone());
    }

    @Override
    public void onLoadAddressFailed(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        llAddressContainer.setVisibility(View.GONE);
        errorView.show(throwable);
    }

    @Override
    public void fillCustomerInformation(Customer customer) {
        etEmail.setText(customer.getEmail());
        etFirstName.setText(customer.getFirstName());
        etLastName.setText(customer.getLastName());
    }

    @Override
    public void renderCity(City city) {
        tvCity.setText(city.getPresent());
        tvWarehouse.setText("");
    }

    @Override
    public void renderWarehouse(Warehouse warehouse) {
        tvWarehouse.setText(warehouse.getDescription());
    }

    @Override
    public void showAuthentication() {
        ConfirmDialog
                .newBuilder(getBaseActivity())
                .setMessage(R.string.message_only_registered_users_can_bye)
                .setVisibleCancelButton(true)
                .setHeaderBackgroundColor(Color.GREEN)
                .setIcon(R.drawable.ic_warning)
                .setTextPositiveButton(R.string.btn_text_sign_in)
                .setConfirmCallback(() -> {
                    getRouter().popBackStack();
                    final Activity activity = getActivity();
                    if (activity != null && activity instanceof MainActivity){
                        ((MainActivity) activity).changeTab(3);
                        ((MainActivity) activity).selectTabItem(3);
                        getRouter().showRoot();
                    }
                }).show();

    }

    @Override
    public void showNotValidFieldError(FieldId field) {
        switch (field){
            case WAREHOUSE:
                tvWarehouse.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                break;
            case CITY:
                tvCity.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                break;
            case FIRST_NAME:
                tilFirstNameWrapper.setError(getString(R.string.error_required_field));
                break;
            case LAST_NAME:
                tilLastNameWrapper.setError(getString(R.string.error_required_field));
                break;
            case EMAIL:
                tilEmailWrapper.setError(getString(R.string.error_enter_valid_email));
                break;
            case EMAIL_EMPTY:
                tilEmailWrapper.setError(getString(R.string.error_required_field));
                break;
            case PHONE_NUMBER:
                tilPhoneNumberWrapper.setError(getString(R.string.error_required_field));
                break;

        }
    }

    @Override
    public void selectWarehouse(City city) {
        getRouter().addFragmentToBackStack(WarehouseListFragment.newInstance(this, city.getRef()));
    }

    @Override
    public void beforeApplyAddress() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onApplyAddressSuccessSuccess(Address address) {
        ProgressDialogFragment.hide(getFragmentManager());
        getRouter().addFragmentToBackStack(ShippingMethodsFragment.newInstance(address));
    }

    @Override
    public void onApplyAddressFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != BaseActivity.RESULT_OK)
            return;
        switch (requestCode){
            case Constants.REQUEST_CODE_GET_CITY:
                final City city = (City) data.getSerializableExtra(Constants.KEY_CITY);
                mShippingAddressPresenter.setCity(city);
                break;
            case Constants.REQUEST_CODE_GET_WAREHOUSE:
                final Warehouse warehouse = (Warehouse) data.getSerializableExtra(Constants.KEY_WAREHOUSE);
                mShippingAddressPresenter.setWarehouse(warehouse);
                break;
        }
    }
}
