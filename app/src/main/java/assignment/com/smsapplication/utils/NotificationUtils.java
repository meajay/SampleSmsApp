package assignment.com.smsapplication.utils;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import assignment.com.smsapplication.R;
import assignment.com.smsapplication.constants.AppConstants;
import assignment.com.smsapplication.sms.view.SmsActivity;

public class NotificationUtils {
    private static String TAG = NotificationUtils.class.getSimpleName();

    private Context context;

    private NotificationManager notificationManager;

    public NotificationUtils(Context context) {
        this.context = context;
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }


    public void showNotificationMessages(final String title, final String message,final String time){
        final int icon = R.drawable.ic_textsms;
        Intent resultIntent = new Intent(context, SmsActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        resultIntent.putExtra(AppConstants.ADDRESS, title);
        resultIntent.putExtra(AppConstants.MESSAGE, message);
        resultIntent.putExtra(AppConstants.TIMESTAMP,time);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        showSmallNotification(mBuilder, icon, title, message, pendingIntent);
        playNotificationSound();
    }


    private void showSmallNotification(NotificationCompat.Builder mBuilder, int icon, String title,
                                       String message, PendingIntent resultPendingIntent) {
        String CHANNEL_ID = "SMS_APP";
        CharSequence name = "New-Sms";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
        }

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);
        Notification notification;
        notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setStyle(inboxStyle)
                .setChannelId(CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon))
                .setContentText(message)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(AppConstants.NOTIFICATION_ID_SMALL, notification);
    }

    private void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + context.getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(context, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
