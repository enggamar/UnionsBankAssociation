package com.unionbankassociation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unionbankassociation.R;
import com.unionbankassociation.utils.AppSharedPreference;


public class SplashActivity extends BaseActivity {
    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Start home activity
        showSplashScreen();
    }

    public void showSplashScreen() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (AppSharedPreference.getInstance().getString(SplashActivity.this, AppSharedPreference.ACCESS_TOKEN) != null) {
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LogInActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
