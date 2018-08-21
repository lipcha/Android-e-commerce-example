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
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.model.models.query.WarehouseQuery;
import com.foxkiev.app.presentation.presenter.WarehouseListPresenter;
import com.foxkiev.app.presentation.view.WarehoseView;
import com.foxkiev.app.ui.adapters.warehouse.WarehouseListAdapter;
import com.foxkiev.app.ui.adapters.warehouse.WarehouseListConfiguration;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by lipcha on 09.05.18.
 */

public class WarehouseListFragment extends BaseListFragment<Warehouse, WarehouseListAdapter> implements WarehoseView {

    private static final String KEY_CITY_REF = "city_ref";

    @InjectPresenter
    WarehouseListPresenter mWarehouseListPresenter;

    @BindView(R.id.etWarehouse)
    EditText etWarehouse;

    @ProvidePresenter
    WarehouseListPresenter provideWarehousesPresenter(){
        return new WarehouseListPresenter(getArguments().getString(KEY_CITY_REF));
    }

    public static BaseFragment newInstance(final BaseFragment targetFragment, final String cityRef){
        final BaseFragment fragment = new WarehouseListFragment();
        final Bundle args = new Bundle();
        args.putString(KEY_CITY_REF, cityRef);
        fragment.setArguments(args);
        fragment.setTargetFragment(targetFragment, Constants.REQUEST_CODE_GET_WAREHOUSE);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_warehouse;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxTextView.textChangeEvents(etWarehouse)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .skip(1)
                .subscribe(event -> getListPresenter().setQuery(new WarehouseQuery(etWarehouse.getText().toString())));
    }

    @Override
    protected String getTitle() {
        return "Select warehouse";
    }

    @Override
    protected BaseListPresenter<Warehouse, ?> getListPresenter() {
        return mWarehouseListPresenter;
    }

    @Override
    protected WarehouseListAdapter createListAdapter(DataProvider<Warehouse> dataProvider) {
        return new WarehouseListAdapter(dataProvider);
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new WarehouseListConfiguration(getContext());
    }

    @Override
    public void onItemClick(Warehouse model, int position) {
        final Fragment fragment = getTargetFragment();
        if (fragment == null)
            return;
        final Intent intent = new Intent(getContext(), WarehouseListFragment.class);
        intent.putExtra(Constants.KEY_WAREHOUSE, model);
        fragment.onActivityResult(getTargetRequestCode(), BaseActivity.RESULT_OK, intent);
        getRouter().popBackStack();
    }
}
