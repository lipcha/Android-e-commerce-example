package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.model.models.ShippingMethod;
import com.foxkiev.app.presentation.presenter.ShippingMethodsPresenter;
import com.foxkiev.app.presentation.view.ShippingMethodsView;
import com.foxkiev.app.ui.custom_views.ErrorView;
import com.foxkiev.app.ui.custom_views.ShippingMethodsRadioGroup;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShippingMethodsFragment extends BaseFragment implements ShippingMethodsView {

    private static final String KEY_ADDRESS = "address";

    @BindView(R.id.rgShippingMethods)
    ShippingMethodsRadioGroup rgShippingMethods;

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.errorView)
    ErrorView errorView;

    @BindView(R.id.llShippingMethodsContainer)
    View llShippingMethodsContainer;

    @InjectPresenter
    ShippingMethodsPresenter mShippingMethodsPresenter;

    @ProvidePresenter
    ShippingMethodsPresenter provideShippingMethodsPresenter(){
        return new ShippingMethodsPresenter((Address) getArguments().getSerializable(KEY_ADDRESS));
    }

    public static BaseFragment newInstance(final Address address){
        final BaseFragment fragment = new ShippingMethodsFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_ADDRESS, address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shipping_methods;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        errorView.setOnReloadClickListener(v -> mShippingMethodsPresenter.refreshShippingMethods());
    }

    @OnClick(R.id.btnNext)
    void onNextClick(View v){
        mShippingMethodsPresenter.onNext(rgShippingMethods.getSelectedShippingMethod());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_shipping_methods);
    }

    @Override
    public void beforeLoadShippingMethods() {
        errorView.setVisibility(View.GONE);
        llShippingMethodsContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadShippingMethodsSuccess(List<ShippingMethod> shippingMethods) {
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        llShippingMethodsContainer.setVisibility(View.VISIBLE);
        rgShippingMethods.setShippingMethods(shippingMethods);
    }

    @Override
    public void onLoadShippingMethodsFailed(Throwable throwable) {
        llShippingMethodsContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        errorView.show(throwable);
    }

    @Override
    public void showSelectShippingMethodError() {
        ErrorHandler.onError(getContext(), new Throwable("Choose shipping method!"));
    }

    @Override
    public void beforeUploadShippingInfo() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onUploadShippingInfoSuccess(String carrierCode, PaymentMethodsTotal paymentMethodsTotal) {
        ProgressDialogFragment.hide(getFragmentManager());
        getRouter().addFragmentToBackStack(PaymentMethodsFragment.newInstance(carrierCode, paymentMethodsTotal));
    }

    @Override
    public void onUploadShippingInfoFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }
}
