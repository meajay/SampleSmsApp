package assignment.com.smsapplication.sms.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import assignment.com.smsapplication.base.BasePresenter;
import assignment.com.smsapplication.constants.AppConstants;
import assignment.com.smsapplication.sms.model.Sms;
import assignment.com.smsapplication.sms.view.SmsMvpView;
import assignment.com.smsapplication.utils.SmsAPI;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SmsPresenter extends BasePresenter<SmsMvpView> implements SmsMvpPresenter {
    private SmsAPI smsAPI;

    public SmsPresenter(SmsAPI smsAPI) {
        this.smsAPI = smsAPI;
    }

    @Override
    public void getAllInBoxMessages() {
        Disposable d = smsAPI.fetchAllInboxSms().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(smsResponse -> {
                    getView().onGetInboxMessagesResponse(AppConstants.SUCCESS,
                            smsResponse.getSmsList(), "success");
                }, throwable -> {
                    getView().onGetInboxMessagesResponse(AppConstants.ERROR, null,
                            throwable.getMessage());
                });
    }
}
