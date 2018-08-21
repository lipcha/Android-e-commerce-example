package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.DefaultCurrency;
import com.foxkiev.app.model.repository.CurrencyRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.presentation.view.CurrencyView;

import javax.inject.Inject;


@InjectViewState
public class CurrencyPresenter extends BasePresenter<CurrencyView>{

    @Inject
    CurrencyRepository mCurrencyRepository;

    @Inject
    PreferenceStorage mStorage;

    private DefaultCurrency mCurrency;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void updateCurrency(){
        getExecutor().execute(mCurrencyRepository.getCurrency()
                        .flatMap(currency -> mStorage.getCurrentCurrency()
                                .map(currencyCode -> new DefaultCurrency(currencyCode, currency))
                        ),
                defaultCurrency -> {
                    mCurrency = defaultCurrency;
                    getViewState().setCurrency(defaultCurrency);
                }
        );
    }

    public void provideCurrency(){
        if (mCurrency == null){
           updateCurrency();
        }else getViewState().setCurrency(mCurrency);
    }



}
