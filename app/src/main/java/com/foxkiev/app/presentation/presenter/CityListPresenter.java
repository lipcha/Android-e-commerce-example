package com.foxkiev.app.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.City;
import com.foxkiev.app.model.repository.DestinationRepository;
import com.foxkiev.app.presentation.view.CityListView;

import javax.inject.Inject;

/**
 * Created by lipcha on 08.05.18.
 */

@InjectViewState
public class CityListPresenter extends BaseListPresenter<City, CityListView> {

    @Inject
    DestinationRepository mDestinationRepository;

    private String mCityName;

    public void setCityName(String name){
        mCityName = name;
        refreshList();
    }

    @Override
    public void refreshList() {
        findCity();
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    public void findCity(){
        if (mDestinationRepository == null)
            return;
        getExecutor().execute(mDestinationRepository.findCity(mCityName), this::onLoadListSuccess, this::onLoadListFailed);
    }
}
