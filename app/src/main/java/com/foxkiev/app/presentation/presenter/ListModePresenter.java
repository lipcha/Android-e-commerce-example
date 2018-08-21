package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.Constants;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.presentation.view.ListModeView;

import javax.inject.Inject;

/**
 * Created by lipcha on 01.03.18.
 */

@InjectViewState
public class ListModePresenter extends BasePresenter<ListModeView> {

    @Inject
    PreferenceStorage mStorage;

    public ListModePresenter (){
        App.getRepositoryComponent().inject(this);
    }

    @Override
    protected void onAttachView() {

    }

    public int getMode() {
        return mStorage.getListMode();
    }

    public void onClickChangeListMode(int currentScrollPosition) {
        final int mode = mStorage.getListMode() == Constants.MODE_LIST ? Constants.MODE_GRID : Constants.MODE_LIST;
        getExecutor().execute(mStorage.storeListMode(mode),b -> getViewState().changeListMode(mode, currentScrollPosition));
    }
}
