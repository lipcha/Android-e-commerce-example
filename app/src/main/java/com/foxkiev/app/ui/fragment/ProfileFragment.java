package com.foxkiev.app.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.presentation.presenter.ProfilePresenter;
import com.foxkiev.app.presentation.view.ProfileView;
import com.foxkiev.app.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lipcha on 11.05.18.
 */

public class ProfileFragment extends BaseFragment implements ProfileView{

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvUserEmail)
    TextView tvUserEmail;

    public static BaseFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_profile);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProfilePresenter.updateCustomer();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void renderCustomer(Customer customer) {
        if (customer == null)
            return;
        tvUserName.setText(customer.getFirstName() + " " + customer.getLastName());
        tvUserEmail.setText(customer.getEmail());
    }

    @Override
    public void onGetCustomerFailed() {
        final BaseActivity baseActivity = getBaseActivity();
        getRouter().popBackStack();
        if (baseActivity != null && baseActivity instanceof MainActivity){
            ((MainActivity) baseActivity).setTab(3);
        }
    }

    @OnClick(R.id.btnMyOrders)
    void onMyOrdersClick(View v){
        getRouter().addFragmentToBackStack(MyOrdersFragment.newInstance());
    }

    @OnClick(R.id.btnSettings)
    void onSettingsClick(View v){
        getRouter().addFragmentToBackStack(SettingsFragment.newInstance());
    }
}
