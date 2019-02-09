package com.unionbankassociation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unionbankassociation.R;
import com.unionbankassociation.fragments.AboutUsFragment;
import com.unionbankassociation.fragments.CommonFragment;
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
            case 6:
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE", 1);
                CommonFragment fragment = new CommonFragment();
                fragment.setArguments(bundle);
                addFragmentWithBackstack(R.id.container, fragment, CommonFragment.class.getSimpleName());
                break;
            case 7:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("TYPE", 2);
                CommonFragment fragment1 = new CommonFragment();
                fragment1.setArguments(bundle1);
                addFragmentWithBackstack(R.id.container, fragment1, CommonFragment.class.getSimpleName());
                break;
            case 8:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("TYPE", 3);
                CommonFragment fragment2 = new CommonFragment();
                fragment2.setArguments(bundle2);
                addFragmentWithBackstack(R.id.container, fragment2, CommonFragment.class.getSimpleName());
                break;
            case 9:
                Bundle bundle3 = new Bundle();
                bundle3.putInt("TYPE", 4);
                CommonFragment fragment3 = new CommonFragment();
                fragment3.setArguments(bundle3);
                addFragmentWithBackstack(R.id.container, fragment3, CommonFragment.class.getSimpleName());
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
