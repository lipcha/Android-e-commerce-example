package com.foxkiev.app.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;

import java.text.Normalizer;

/**
 * Created by lipcha on 26.04.18.
 */

public class SearchHelper {

    public static CharSequence highlight(String search, String originalText) {
        if (TextUtils.isEmpty(search))
            return originalText;
        String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        int start = normalizedText.indexOf(search);
        if (start <= 0) {
            return originalText;
        } else {
            Spannable highlighted = new SpannableString(originalText);
            while (start > 0) {
                int spanStart = Math.min(start, originalText.length());
                int spanEnd = Math.min(start + search.length(), originalText.length());
                highlighted.setSpan(new BackgroundColorSpan(Color.YELLOW), spanStart, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = normalizedText.indexOf(search, spanEnd);
            }
            return highlighted;
        }
    }
}
