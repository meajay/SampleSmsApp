package assignment.com.smsapplication.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import assignment.com.smsapplication.sms.model.Sms;
import assignment.com.smsapplication.sms.model.SmsResponse;
import io.reactivex.Observable;

public class SmsAPI {
    private Context context;

    public SmsAPI(Context context) {
        this.context = context;
    }

    public Observable<SmsResponse> fetchAllInboxSms() {
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, null, null,
                null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);
        int totalSMS = 0;
        List<Sms> smsList = new ArrayList<>();
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    Sms sms = new Sms();
                    sms.setDate(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Inbox.DATE)));
                    sms.setSender(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Inbox.ADDRESS)));
                    sms.setMessage(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Inbox.BODY)));
                    sms.setRead(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Inbox.READ)));
                    c.moveToNext();
                    smsList.add(sms);
                }
            }
            c.close();
        } else {
            Toast.makeText(context, "No message to show!", Toast.LENGTH_SHORT).show();
        }
        return getSmsResponse(smsList);
    }

    private Observable<SmsResponse> getSmsResponse(List<Sms> smsList) {
        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setSmsList(smsList);
        return Observable.just(smsResponse);
    }

}
