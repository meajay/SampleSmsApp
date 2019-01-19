package assignment.com.smsapplication.sms.presenter;

import android.content.Context;

import assignment.com.smsapplication.base.BasePresenter;
import assignment.com.smsapplication.sms.view.SmsMvpView;
import assignment.com.smsapplication.utils.SmsAPI;

public class SmsPresenter extends BasePresenter<SmsMvpView> implements SmsMvpPresenter {
    private SmsAPI smsAPI;

    public SmsPresenter(SmsAPI smsAPI) {
        this.smsAPI = smsAPI;
    }

    @Override
    public void getAllInBoxMessages() {

    }
}
