package com.foxkiev.app.base.list;

import android.text.TextUtils;

import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.model.models.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 28.02.18.
 */

public class DataProvider<MODEL extends IdModel> {

    private final List<MODEL> mData;
    private final List<MODEL> mFilterData;
    private Query<MODEL> query;

    public DataProvider() {
        mData = new ArrayList<>();
        mFilterData = new ArrayList<>();
    }

    public DataProvider(final List<MODEL> data) {
        mData = data;
        mFilterData = data;
    }

    public List<MODEL> getData(){
        return mFilterData;
    }

    public Query<MODEL> getQuery() {
        return query;
    }

    void setQuery(Query<MODEL> query) {
        this.query = query;
        updateFilterQuery();
    }

    void setData(List<MODEL> data) {
        this.mData.clear();
        mData.addAll(data);
        updateFilterQuery();
    }

    void clearData(){
        mData.clear();
        mFilterData.clear();
    }

    int removeItemById(String itemId){
        if (TextUtils.isEmpty(itemId))
            return -1;

        for (int i = 0; i < mData.size(); i++){
            final MODEL data = mData.get(i);
            if (!TextUtils.isEmpty(data.getId()) && data.getId().equals(itemId)){
                mData.remove(i);
            }
        }

        for (int i = 0; i < mFilterData.size(); i++){
            final MODEL data = mFilterData.get(i);
            if (!TextUtils.isEmpty(data.getId()) && data.getId().equals(itemId)){
                mFilterData.remove(i);
                return i;
            }
        }
        return -1;
    }

    MODEL getItemById(String itemId){
        if (TextUtils.isEmpty(itemId))
            return null;
        for (int i = 0; i < mData.size(); i++){
            final MODEL data = mData.get(i);
            if (!TextUtils.isEmpty(data.getId()) && data.getId().equals(itemId)){
                return data;
            }
        }
        return null;
    }

    int updateItem(MODEL model){
        if (model == null)
            return -1;
        for (int i = 0; i < mData.size(); i++){
            final MODEL data = mData.get(i);
            if (!TextUtils.isEmpty(data.getId()) && data.getId().equals(model.getId())){
                mData.set(i, model);
            }
        }

        for (int i = 0; i < mFilterData.size(); i++){
            final MODEL data = mFilterData.get(i);
            if (!TextUtils.isEmpty(data.getId()) && data.getId().equals(model.getId())){
                mFilterData.set(i, model);
                return i;
            }
        }
        return -1;
    }

    private void updateFilterQuery(){
        mFilterData.clear();
        for (MODEL data : mData){
            if (query == null || query.contains(data))
                mFilterData.add(data);
        }
    }
}
