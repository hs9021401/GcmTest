package com.foxlinkimage.alex.gcmserver;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by Alex on 2015/7/9.
 */
public class GcmReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "GCMTEST";
    private NotificationManager mNotificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.i(TAG, "GcmReceiver");

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);
        Log.i(TAG, "GcmReceiver messageType:" + messageType);

        Bundle extras = intent.getExtras();
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {

            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

            }
            String msg = extras.getString("message");
            Log.i(TAG, "GcmReceiver Received: " + msg);
            MainActivity.getInstace().updateTheTextView(msg);

        }
    }
}
