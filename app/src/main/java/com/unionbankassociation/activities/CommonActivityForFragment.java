package com.unionbankassociation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unionbankassociation.R;
import com.unionbankassociation.fragments.AboutUsFragment;
import com.unionbankassociation.fragments.DisciplinaryActionFragment;
import com.unionbankassociation.fragments.NonSunOrdinatorFragment;
import com.unionbankassociation.fragments.PensionFragment;
import com.unionbankassociation.fragments.ServiceConditionbpsFragment;

public class CommonActivityForFragment extends BaseActivity {
    int fragmentType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_service_condition);
        getIntentData();
        switch (fragmentType) {
            case 1:
                addFragmentWithBackstack(R.id.container, new AboutUsFragment(), AboutUsFragment.class.getSimpleName());
                break;
            case 2:
                addFragmentWithBackstack(R.id.container, new ServiceConditionbpsFragment(), ServiceConditionbpsFragment.class.getSimpleName());
                break;
            case 3:
                addFragmentWithBackstack(R.id.container, new NonSunOrdinatorFragment(), ServiceConditionbpsFragment.class.getSimpleName());
                break;
            case 4:
                addFragmentWithBackstack(R.id.container, new PensionFragment(), PensionFragment.class.getSimpleName());
                break;
            case 5:
                addFragmentWithBackstack(R.id.container, new DisciplinaryActionFragment(), DisciplinaryActionFragment.class.getSimpleName());
                break;

        }
    }

    private void getIntentData() {
        fragmentType = getIntent().getExtras().getInt("FRAGMENT");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
