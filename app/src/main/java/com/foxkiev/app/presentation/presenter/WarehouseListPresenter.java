package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.Warehouse;
import com.foxkiev.app.model.repository.DestinationRepository;
import com.foxkiev.app.presentation.view.WarehoseView;

import javax.inject.Inject;

/**
 * Created by lipcha on 09.05.18.
 */

@InjectViewState
public class WarehouseListPresenter extends BaseListPresenter<Warehouse, WarehoseView> {

    private final String mCityRef;

    @Inject
    DestinationRepository mDestinationRepository;

    public WarehouseListPresenter(final String cityRef){
        mCityRef = cityRef;
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        getExecutor().execute(mDestinationRepository.findWarehouse(mCityRef), this::onLoadListSuccess, this::onLoadListFailed);
    }


}
