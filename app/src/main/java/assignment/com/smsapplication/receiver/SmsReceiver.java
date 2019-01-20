package assignment.com.smsapplication.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import assignment.com.smsapplication.constants.AppConstants;
import assignment.com.smsapplication.sms.view.SmsActivity;
import assignment.com.smsapplication.utils.NotificationUtils;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = BroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus != null) {
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                if (isVersionM) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                String address = msgs[i].getOriginatingAddress();
                String messsage = msgs[i].getMessageBody();
                String time = ""+msgs[i].getTimestampMillis();
                Log.d(TAG, "onReceive: " + messsage);

                showNotificationMessage(context, address, messsage,time);
            }
        }
    }

    private void showNotificationMessage(Context context, String title, String message,String time) {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.showNotificationMessages(title, message,time);
    }
}
