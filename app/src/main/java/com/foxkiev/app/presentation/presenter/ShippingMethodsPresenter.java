package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.ShippingMethod;
import com.foxkiev.app.model.repository.CheckoutRepository;
import com.foxkiev.app.presentation.view.ShippingMethodsView;

import javax.inject.Inject;

@InjectViewState
public class ShippingMethodsPresenter extends BasePresenter<ShippingMethodsView>{

    private final Address mAddress;

    @Inject
    CheckoutRepository mСheckoutRepository;

    public ShippingMethodsPresenter(Address address) {
        this.mAddress = address;
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        refreshShippingMethods();
    }

    public void refreshShippingMethods() {
        getViewState().beforeLoadShippingMethods();
        getExecutor().execute(mСheckoutRepository.getShippingMethods(), getViewState()::onLoadShippingMethodsSuccess, getViewState()::onLoadShippingMethodsFailed);
    }

    public void onNext(ShippingMethod shippingMethod) {
        if (shippingMethod == null){
            getViewState().showSelectShippingMethodError();
        }
        getViewState().beforeUploadShippingInfo();
        getExecutor().execute(mСheckoutRepository.setShippingInfo(
                mAddress.getEmail(),
                mAddress.getFirstName(),
                mAddress.getLastName(),
                mAddress.getTelephone(),
                mAddress.getCity(),
                mAddress.getStreet().get(0),
                shippingMethod.getCarrierCode()),
                paymentMethodsTotal -> getViewState().onUploadShippingInfoSuccess(shippingMethod.getCarrierCode(), paymentMethodsTotal),
                t -> getViewState().onUploadShippingInfoFailed(t)
        );
    }
}
