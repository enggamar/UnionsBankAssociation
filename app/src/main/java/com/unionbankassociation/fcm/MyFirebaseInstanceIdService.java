package com.unionbankassociation.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.unionbankassociation.utils.AppSharedPreference;

/**
 * Created by nitin on 5/9/16.
 */


//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    public String refreshedToken;

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("token", refreshedToken);
        //Displaying token on logcat
        AppSharedPreference.getInstance().putString(getApplicationContext(), AppSharedPreference.DEVICE_TOKEN, refreshedToken);
    }
}