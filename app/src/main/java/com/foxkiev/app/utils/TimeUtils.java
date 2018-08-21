package com.foxkiev.app.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lipcha on 05.03.18.
 */

public class TimeUtils {

    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat outTimeFormat = new SimpleDateFormat("dd MMM yyyy ', ' E HH:mm aa", Locale.getDefault());

    public static Calendar getCalendar(final String timeString){
        if (TextUtils.isEmpty(timeString))
            return null;
        Date gmtTime;

        try {
            gmtTime = formatter.parse(timeString);// catch exception

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        final Calendar cal = Calendar.getInstance();

        cal.setTime(gmtTime);
        return cal;
    }

    public static String getFormattedDate(String dateStr){
        Date date = fromUTC(dateStr);
        if (date != null) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
                return outTimeFormat.format(date);
        }
        return "";
    }

    private static Date fromUTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", new Locale("en"));
        df.setTimeZone(tz);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
