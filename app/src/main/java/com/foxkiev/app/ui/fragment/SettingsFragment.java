package com.foxkiev.app.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.DefaultCurrency;
import com.foxkiev.app.presentation.presenter.CurrencyPresenter;
import com.foxkiev.app.presentation.presenter.SettingsPresenter;
import com.foxkiev.app.presentation.view.CurrencyView;
import com.foxkiev.app.presentation.view.SettingsView;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.utils.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 04.05.18.
 */

public class SettingsFragment extends BaseFragment implements SettingsView, CurrencyView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.btnLogout)
    View btnLogOut;

    @BindView(R.id.rbEnglishLanguage)
    RadioButton rbEnglishLanguage;

    @BindView(R.id.rbDefaultLanguage)
    RadioButton rbDefaultLanguage;

    @BindView(R.id.rgLanguage)
    RadioGroup rgLanguage;

    @BindView(R.id.rbCurrencyEUR)
    RadioButton rbCurrencyEUR;

    @BindView(R.id.rbCurrencyUAH)
    RadioButton rbCurrencyUAH;

    @BindView(R.id.rgCurrency)
    RadioGroup rgCurrency;

    @InjectPresenter
    SettingsPresenter mSettingsPresenter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    CurrencyPresenter mCurrencyPresenter;

    @BindView(R.id.llAccountSettings)
    View accountSettingsContainer;

    public static BaseFragment newInstance(){
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rgLanguage.setOnCheckedChangeListener(this);
        rgCurrency.setOnCheckedChangeListener(this);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_settings);
    }

    @OnClick(R.id.btnLogout)
    void onLogOutClick(View v){
        mSettingsPresenter.logOut();
    }

    @OnClick(R.id.btnEditAccount)
    void onEditAccountClick(View v){
        getRouter().addFragmentToBackStack(EditAccountFragment.newInstance());
    }

    @OnClick(R.id.btnChangePassword)
    void onChangePasswordClick(View v){
        getRouter().addFragmentToBackStack(ChangePasswordFragment.newInstance());
    }

    @Override
    public void onLogoutSuccess() {
        final BaseActivity baseActivity = getBaseActivity();
        getRouter().popBackStack();
        if (baseActivity != null && baseActivity instanceof MainActivity){
            ((MainActivity) baseActivity).onLogOut();
        }
    }

    @Override
    public void setVisibleAccountSettings(boolean visible) {
        accountSettingsContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLanguageChange(String language) {
        final Activity activity = getActivity();
        if (activity != null)
            activity.recreate();
    }

    @Override
    public void setCurrentLanguage(String language) {
        switch (language){
            case Constants.LANGUAGE_DEFAULT:
                rbDefaultLanguage.setChecked(true);
                break;
            case Constants.LANGUAGE_EN:
                rbEnglishLanguage.setChecked(true);
                break;
        }
    }

    @Override
    public void setCurrentCurrency(String currencyCode) {
        switch (currencyCode){
            case Constants.CURRENCY_CODE_EUR:
                rbCurrencyEUR.setChecked(true);
                break;
            case Constants.CURRENCY_CODE_UAH:
                rbCurrencyUAH.setChecked(true);
                break;
        }
    }

    @Override
    public void onCurrencyChange() {
        mCurrencyPresenter.updateCurrency();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbDefaultLanguage:
                mSettingsPresenter.changeLanguage(Constants.LANGUAGE_DEFAULT);
                break;
            case R.id.rbEnglishLanguage:
                mSettingsPresenter.changeLanguage(Constants.LANGUAGE_EN);
                break;
            case R.id.rbCurrencyEUR:
                mSettingsPresenter.changeCurrency(Constants.CURRENCY_CODE_EUR);
                break;
            case R.id.rbCurrencyUAH:
                mSettingsPresenter.changeCurrency(Constants.CURRENCY_CODE_UAH);
                break;
        }
    }

    @Override
    public void setCurrency(DefaultCurrency currency) {

    }
}
