package com.unionbankassociation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unionbankassociation.R;
import com.unionbankassociation.utils.AppSharedPreference;


public class SplashActivity extends BaseActivity {
    private int SPLASH_TIME_OUT = 2000;
    String noticeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getIntentData();
        // Start home activity
        showSplashScreen();
    }

    private void getIntentData() {
        if (getIntent() != null &&  getIntent().getExtras()!=null && getIntent().getExtras().containsKey("notice_id")) {
            noticeId = getIntent().getExtras().getString("notice_id");
        }
    }


    public void showSplashScreen() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (AppSharedPreference.getInstance().getString(SplashActivity.this, AppSharedPreference.ACCESS_TOKEN) != null) {

                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                    if (noticeId.length() > 0)
                        intent.putExtra("notice_id", noticeId);
                } else {
                    intent = new Intent(SplashActivity.this, LogInActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}
