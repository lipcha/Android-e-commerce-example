package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.repository.NotificationRepository;
import com.foxkiev.app.presentation.view.NotificationDetailView;

import javax.inject.Inject;

@InjectViewState
public class NotificationDetailPresenter extends BaseListPresenter<ProductInfo, NotificationDetailView> {

    @Inject
    NotificationRepository mNotificationRepository;

    private final String mNotificationId;

    public NotificationDetailPresenter(String notificationId) {
        this.mNotificationId = notificationId;
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        getExecutor().execute(mNotificationRepository.getNotificationDetail(mNotificationId), notificationDetail -> {
            getViewState().renderNotificationHeader(notificationDetail);
            onLoadListSuccess(notificationDetail.getProducts());
        }, this::onLoadListFailed);
    }
}
