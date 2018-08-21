package com.foxkiev.app.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.presentation.presenter.CityListPresenter;
import com.foxkiev.app.presentation.view.CityListView;
import com.foxkiev.app.ui.adapters.city.CityListAdapter;
import com.foxkiev.app.ui.adapters.city.CityListConfiguration;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by lipcha on 08.05.18.
 */

public class CityListFragment extends BaseListFragment<City, CityListAdapter> implements CityListView {

    @BindView(R.id.etCity)
    EditText etCity;

    public static BaseFragment newInstance(BaseFragment targetFragment){
        final BaseFragment cityFragment = new CityListFragment();
        cityFragment.setTargetFragment(targetFragment, Constants.REQUEST_CODE_GET_CITY);
        return cityFragment;
    }

    @InjectPresenter
    CityListPresenter mCityPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_city;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxTextView.textChangeEvents(etCity)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .skip(1)
                .subscribe(event -> mCityPresenter.setCityName(event.text().toString()));
    }

    @Override
    protected String getTitle() {
        return "Select city";
    }

    @Override
    protected BaseListPresenter<City, ?> getListPresenter() {
        return mCityPresenter;
    }

    @Override
    protected CityListAdapter createListAdapter(DataProvider<City> dataProvider) {
        return new CityListAdapter(dataProvider);
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new CityListConfiguration(getContext());
    }

    @Override
    public void onItemClick(City model, int position) {
        final Fragment fragment = getTargetFragment();
        if (fragment == null)
            return;
        final Intent intent = new Intent(getContext(), CityListFragment.class);
        intent.putExtra(Constants.KEY_CITY, model);
        fragment.onActivityResult(getTargetRequestCode(), BaseActivity.RESULT_OK, intent);
        getRouter().popBackStack();
    }
}
