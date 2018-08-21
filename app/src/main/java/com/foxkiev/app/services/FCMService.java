package com.foxkiev.app.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.model.models.Push;
import com.foxkiev.app.ui.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "posts_notification";
    private static final String CHANNEL_NAME = "Push_chanel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        final RemoteMessage.Notification notification = remoteMessage.getNotification();
//        if (notification != null && notification.getBody() != null){
//            displayNotification(notification.getTitle(), notification.getBody());
//        }
        final Push push = Push.fromRemoteMessage(remoteMessage);
        if (push.isValid())
            displayNotification(push);
    }

    private void displayNotification(Push push) {

        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.KEY_PUSH, push);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 1251, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder
                .setContentTitle(push.getTitle())
                .setContentText(push.getMessage())
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(push.getTitle()).bigText(push.getMessage()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(push.getMessage());
            mChannel.enableVibration(true);
            final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager == null)
                return;
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(1251, builder.build());
        } else {
            final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
            notificationManager.notify(1251, builder.build());
        }
    }
}
