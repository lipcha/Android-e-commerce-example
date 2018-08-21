package com.foxkiev.app.base.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.model.models.query.Query;

import butterknife.ButterKnife;

/**
 * Created by lipcha on 27.02.18.
 */

public abstract class BaseViewHolder<DATA extends IdModel> extends RecyclerView.ViewHolder{

    private DATA mData;
    private View mRootView;
    private int mPosition;
    private BaseAdapter.OnItemClickListener<DATA> mOnClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mRootView = itemView;
        mRootView.setOnClickListener(v -> {
            if (mData != null && mOnClickListener != null)
                mOnClickListener.onItemClick(mData, mPosition);
        });
        ButterKnife.bind(this, itemView);
    }

    public void setOnClickListener(BaseAdapter.OnItemClickListener<DATA> onClickListener){
        mOnClickListener = onClickListener;
    }

    public BaseAdapter.OnItemClickListener<DATA> getOnClickListener() {
        return mOnClickListener;
    }

    public void init(DATA data, int position, Query<DATA> query){
        mData = data;
        mPosition = position;
        setupHolder(data, position, query);
    }

    public DATA getData() {
        return mData;
    }

    public Context getContext(){
        return mRootView.getContext();
    }

    protected abstract void setupHolder(DATA data, int position, Query<DATA> query);
}

