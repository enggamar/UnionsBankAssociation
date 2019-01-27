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
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.unionbankassociation.utils.AppConstant;

import org.json.JSONObject;

/**
 * Created.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebasePushService";
    private int uniqueId = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String messageJson = remoteMessage.getData().get("message");

    }

}