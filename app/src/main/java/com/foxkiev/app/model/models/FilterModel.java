package com.foxkiev.app.model.models;

public class FilterModel {

    public static final int SORT_BY_POSITION = 0;
    public static final int SORT_BY_PRODUCT_NAME = 1;
    public static final int SORT_BY_SKU = 2;
    public static final int SORT_BY_PRICE = 3;
    public static final int SORT_BY_SIZE = 4;
    public static final int SORT_BY_CUSTOM_STOCK_STATUS = 5;

    private int mSortBy;
    private boolean mSortReverse;

    public int getSortBy() {
        return mSortBy;
    }

    public void setSortBy(int sortBy) {
        this.mSortBy = sortBy;
    }

    public boolean isSortReverse() {
        return mSortReverse;
    }

    public void toggleSortReverse() {
        this.mSortReverse = !mSortReverse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterModel that = (FilterModel) o;
        return mSortBy == that.mSortBy &&
                mSortReverse == that.mSortReverse;
    }
}
