package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.PaymentMethod;
import com.foxkiev.app.model.models.PaymentMethodsTotal;
import com.foxkiev.app.presentation.presenter.PaymentMethodsPresenter;
import com.foxkiev.app.presentation.view.PaymentMethodsView;
import com.foxkiev.app.ui.custom_views.PaymentMethodsRadioGroup;
import com.foxkiev.app.ui.custom_views.dialogs.OrderSuccessDialog;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import ua.privatbank.paylibliqpay.CheckoutActivity;
import ua.privatbank.paylibliqpay.ErrorCode;
import ua.privatbank.paylibliqpay.LiqPay;
import ua.privatbank.paylibliqpay.LiqPayCallBack;

public class PaymentMethodsFragment extends BaseFragment implements PaymentMethodsView{

    public static final String KEY_PAYMENT_METHODS = "payment_methods";
    public static final String KEY_CARRIER_CODE = "carrier_code";

    @BindView(R.id.rgPaymentMethods)
    PaymentMethodsRadioGroup rgPaymentMethods;

    @InjectPresenter
    PaymentMethodsPresenter mPaymentPresenter;

    @ProvidePresenter
    PaymentMethodsPresenter providePaymentPresenter(){
        return new PaymentMethodsPresenter(getArguments().getString(KEY_CARRIER_CODE), (PaymentMethodsTotal) getArguments().getSerializable(KEY_PAYMENT_METHODS));
    }

    public static BaseFragment newInstance(String carrierCode, PaymentMethodsTotal paymentMethodsTotal){
        final BaseFragment fragment = new PaymentMethodsFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_CARRIER_CODE, carrierCode);
        args.putSerializable(KEY_PAYMENT_METHODS, paymentMethodsTotal);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_payment_methods;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_payment_methods);
    }

    @Override
    public void renderPaymentMethods(List<PaymentMethod> paymentMethods) {
        rgPaymentMethods.setPaymentMethods(paymentMethods);
    }

    @Override
    public void placeOrderWithLiqPay(PaymentMethod paymentMethod) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("version", "3");
        map.put("public_key", "your public_key");
        map.put("action", "auth");
        map.put("amount", "payment amount");
        map.put("currency", "payment currency");
        map.put("description", "account top-up");
        map.put("order_id", "123456");
        map.put("language", "ru");
        map.put("server_url", "https://www.wargaming.net");
        map.put("card", "5457095645459696");
        map.put("card_exp_month", "11");
        map.put("card_exp_year", "17");
        map.put("card_exp_cvv", "171");
        LiqPay.api(getContext(), "auth", map, "your_privat_key", new LiqPayCallBack() {
            @Override
            public void onResponseSuccess(String s) {

            }

            @Override
            public void onResponceError(ErrorCode errorCode) {

            }
        });

    }

    @Override
    public void beforePlaceOrder() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onPlaceOrderSuccess(String orderNumber) {
        ProgressDialogFragment.hide(getFragmentManager());
        OrderSuccessDialog.show(getBaseActivity(), orderNumber);
    }

    @Override
    public void onPlaceOrderFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @OnClick(R.id.btnPlaceOrder)
    void onPlaceOrderClick(View v){
        mPaymentPresenter.placeOrder(rgPaymentMethods.getSelectedPaymentMethod());
    }
}
