package com.unionbankassociation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unionbankassociation.R;

public class ServiceConditionActivity extends BaseActivity {

    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_service_condition);
        getIntentData();
        setFragment();
    }

    private void setFragment() {
    }

    private void getIntentData() {
        type = getIntent().getIntExtra("Type", 0);
    }
}
