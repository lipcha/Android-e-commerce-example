package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.foxkiev.app.R;
import com.foxkiev.app.model.models.FilterModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FilterView extends FrameLayout{

    @BindView(R.id.sSortChooser)
    Spinner sSortByChooser;

    @BindView(R.id.btnSortBy)
    ImageButton btnSortBy;

    private FilterModel mFilterModel;

    private OnFilterChangeListener mOnFilterChangeListener;

    public FilterView(@NonNull Context context) {
        super(context);
        init();
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        init();
        super.onFinishInflate();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_filter, this, true);
        ButterKnife.bind(this, this);
    }

    public void renderFilter(FilterModel filterModel){
        mFilterModel = filterModel;
        setupSortBy(filterModel);
        btnSortBy.setRotation(!filterModel.isSortReverse() ? 0 : 180);
    }

    @OnClick(R.id.btnSortBy)
    void onSortByClick(View view){
        if (mFilterModel == null)
            return;
        mFilterModel.toggleSortReverse();
        btnSortBy.setRotation(!mFilterModel.isSortReverse() ? 0 : 180);
        updateFilter(mFilterModel);
    }

    public void setOnFilterChangeListener(OnFilterChangeListener listener) {
        this.mOnFilterChangeListener = listener;
    }

    private void setupSortBy(FilterModel filterModel){
        final ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(), R.array.filter_sort_by_items, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSortByChooser.setAdapter(adapter);
        sSortByChooser.setSelection(filterModel.getSortBy());
        sSortByChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterModel.setSortBy(position);
                updateFilter(filterModel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateFilter(FilterModel filterModel){
        if (mOnFilterChangeListener != null)
            mOnFilterChangeListener.onFilterChange(filterModel);
    }

    public interface OnFilterChangeListener {
        void onFilterChange(FilterModel filterModel);
    }
}
