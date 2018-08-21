package com.foxkiev.app.base.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxkiev.app.model.models.IdModel;

/**
 * Created by lipcha on 27.02.18.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private final DataProvider<? extends IdModel> mDataProvider;
    private OnItemClickListener<? extends IdModel> mOnClickListener;

    public BaseAdapter(DataProvider<? extends IdModel> dataProvider) {
        this.mDataProvider = dataProvider;
    }

    protected abstract int getLayoutResId(int itemType);

    protected abstract BaseViewHolder<? extends IdModel> getViewHolder(View itemView, int itemType);

    @Override
    @NonNull
    public BaseViewHolder<? extends IdModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(viewType), parent, false);
        return getViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setOnClickListener(mOnClickListener);
        holder.init(mDataProvider.getData().get(position), position, mDataProvider.getQuery());
    }

    public void setOnClickListener(OnItemClickListener<? extends IdModel> onClickListener){
        mOnClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return mDataProvider.getData().size();
    }

    public interface OnItemClickListener<DATA>{
        void onItemClick(DATA data, int position);
    }
}
