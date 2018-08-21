package com.foxkiev.app.utils;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.foxkiev.app.Constants;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by lipcha on 26.04.18.
 */

public class RxUtils {

    public static Disposable setupClick(final View view, final Consumer<Object> onNext){
        return RxView.clicks(view)
                .throttleFirst(Constants.FIRST_CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(onNext);
    }

    public static Disposable queryTextChangeEvents(final SearchView searchView, final Consumer<String> onNext){
        return RxSearchView.queryTextChangeEvents(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(searchViewQueryTextEvent -> searchViewQueryTextEvent.queryText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(1)
                .subscribe(onNext);
    }

    public static Disposable interceptTextChange(final TextView textView, final Consumer<TextViewTextChangeEvent> onNext){
        return RxTextView.textChangeEvents(textView).subscribe(onNext);
    }
}
