package com.foxkiev.app.utils;

import android.text.TextUtils;

import com.foxkiev.app.model.models.ProductInfo;

import java.util.Calendar;

/**
 * Created by lipcha on 05.03.18.
 */

public class ProductUtils {

    public static boolean isNewProduct(ProductInfo productInfo){
        if (TextUtils.isEmpty(productInfo.getProductIsNewFromDate()) && TextUtils.isEmpty(productInfo.getProductIsNewToDate()))
            return false;

        final Calendar isNewFromDate = TimeUtils.getCalendar(productInfo.getProductIsNewFromDate());
        final Calendar isNewToDate = TimeUtils.getCalendar(productInfo.getProductIsNewToDate());

        if (isNewFromDate == null && isNewToDate == null)
            return false;

        final Calendar currentDate = Calendar.getInstance();
        return isNewFromDate != null && isNewToDate == null && isNewFromDate.before(currentDate) || isNewFromDate == null && currentDate.before(isNewToDate) || isNewFromDate != null && isNewToDate != null && isNewFromDate.before(currentDate) && currentDate.before(isNewToDate);
    }
}
