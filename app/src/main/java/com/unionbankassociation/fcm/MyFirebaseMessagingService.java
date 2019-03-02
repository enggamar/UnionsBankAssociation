package com.unionbankassociation.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.SplashActivity;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    /**
     * This variable is used to have a unique id of the every notification
     */
    private int mUniqueId;
    /**
     * This variable is used to set the title of the notification
     */
    private String mTitle;
    private String mNoticeId;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //handling notification
        sendNotification(remoteMessage.getData());
    }

    //method to get notificationManager
    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    //This method is only generating push notification
    private void sendNotification(Map<String, String> data) {
        mUniqueId = 0;
        try {
            generateUniqueId();
            Intent intent = new Intent(this, SplashActivity.class);
            if (data.get("title") != null)
                mTitle = data.get("title");
            if (data.get("notice_id") != null) {
                mNoticeId = data.get("notice_id");
           }
            intent.putExtra("notice_id", mNoticeId);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String id = "channel" + mUniqueId;
            NotificationManager notificationManager = getNotificationManager();
            if (notificationManager != null) {
                String channelId = "channel" + mUniqueId;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel mChannel = new NotificationChannel(channelId, getString(R.string.app_name), importance);
                    // Configure the notification channel.
                    mChannel.setDescription(getString(R.string.app_name));
                    mChannel.setShowBadge(true);
                    mChannel.enableLights(true);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    notificationManager.createNotificationChannel(mChannel);

                }
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(this, id)
                                .setSmallIcon(getNotificationIcon())
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo))
                                .setSound(defaultSoundUri)
                                .setContentText(mTitle)
                                .setContentTitle(getString(R.string.app_name))
                                .setAutoCancel(true).setChannelId(channelId)
                                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                .setContentIntent(pendingIntent);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    notificationBuilder.setSmallIcon(R.drawable.ic_logo);
                }
                notificationManager.notify(mUniqueId, notificationBuilder.build());

                Notification notification = notificationBuilder.build();

                notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                notification.flags |=
                        Notification.FLAG_AUTO_CANCEL; //Do not clear the notification
                notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
                notification.defaults |= Notification.DEFAULT_VIBRATE;//Vibration

                notificationManager.notify(mUniqueId, notification);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to generate unique id for the notifications
     */
    private void generateUniqueId() {
        mUniqueId = (int) System.currentTimeMillis();
    }

    /**
     * This method is used to set the notification icon on the push notifications for marshmallow
     * or oreo devices
     *
     * @return drawable on the basis of the type of device
     */
    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_logo : R.drawable.ic_logo;
    }
}
