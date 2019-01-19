package assignment.com.smsapplication.sms.view;

import java.util.List;

import assignment.com.smsapplication.base.BaseViewContract;
import assignment.com.smsapplication.sms.model.Sms;

public interface SmsMvpView extends BaseViewContract {
    void onGetInboxMessagesResponse(int result, List<Sms> smsList,String message);
}
