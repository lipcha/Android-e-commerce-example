package com.foxkiev.app.ui.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.foxkiev.app.model.models.Currency;
import com.foxkiev.app.model.models.DefaultCurrency;
import com.foxkiev.app.presentation.presenter.CurrencyPresenter;
import com.foxkiev.app.presentation.view.CurrencyView;

import java.text.DecimalFormat;

public class PriceTextView extends AppCompatTextView implements CurrencyView{

    private MvpDelegate mParentDelegate;
    private MvpDelegate<PriceTextView> mMvpDelegate;

    @InjectPresenter(type = PresenterType.GLOBAL)
    CurrencyPresenter mCurrencyPresenter;

    private DefaultCurrency mCurrency;

    private float mPrice;

    public PriceTextView(Context context) {
        super(context);
    }

    public PriceTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(MvpDelegate parentDelegate) {
        mParentDelegate = parentDelegate;

        getMvpDelegate().onCreate();
        getMvpDelegate().onAttach();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        getMvpDelegate().onSaveInstanceState();
        getMvpDelegate().onDetach();
    }

    public MvpDelegate<PriceTextView> getMvpDelegate() {
        if (mMvpDelegate != null) {
            return mMvpDelegate;
        }

        mMvpDelegate = new MvpDelegate<>(this);
        mMvpDelegate.setParentDelegate(mParentDelegate, String.valueOf(getId()));
        return mMvpDelegate;
    }

    public void setPrice(float price){
        mPrice = price;
        if (mCurrency != null)
            renderProductPrice();
        else mCurrencyPresenter.provideCurrency();
    }

    @Override
    public void setCurrency(DefaultCurrency currency) {
        mCurrency = currency;
        renderProductPrice();
    }

    @SuppressLint("SetTextI18n")
    private void renderProductPrice(){
        setText(String.valueOf(new DecimalFormat("#.##").format(mPrice * mCurrency.getRate())) + " " + mCurrency.getCurrencyName());
    }
}
