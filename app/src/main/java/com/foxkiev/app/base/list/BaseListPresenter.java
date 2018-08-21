package com.foxkiev.app.base.list;


import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.model.models.query.Query;

import java.util.List;

/**
 * Created by lipcha on 28.02.18.
 */

public abstract class BaseListPresenter<MODEL extends IdModel, V extends MvpListView> extends BasePresenter<V> {

    private final DataProvider<MODEL> mDataProvider = new DataProvider<>();

    public abstract void refreshList();

    @Override
    protected void onAttachView() {
        refreshList();
    }

    public DataProvider<MODEL> getDataProvider(){
        return mDataProvider;
    }

    protected void onLoadListSuccess(List<MODEL> data){
        getViewState().hideLoadingProgress();
        getDataProvider().setData(data);
        getViewState().notifyDataSetChange();
    }

    protected void onLoadListFailed(final Throwable throwable){
        getViewState().hideLoadingProgress();
        getDataProvider().clearData();
        getViewState().showLoadingError(throwable);
    }

    public void setQuery(Query<MODEL> query){
        mDataProvider.setQuery(query);
        getViewState().notifyDataSetChange();
    }

    public void removeItem(MODEL data){
        final int position = getDataProvider().removeItemById(data.getId());
        if (position != -1)
            getViewState().notifyDataRemoved(position);
    }

    protected MODEL getDataById(String id){
        return getDataProvider().getItemById(id);
    }

    protected void updateItem(MODEL item){
        final int position = getDataProvider().updateItem(item);
        if (position != -1)
            getViewState().notifyItemChanged(position);
    }

}
