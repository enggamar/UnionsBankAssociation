package com.unionbankassociation.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.unionbankassociation.R;
import com.unionbankassociation.fragments.AboutUsFragment;
import com.unionbankassociation.fragments.CommonFragment;
import com.unionbankassociation.fragments.CommonServiceConditionFragment;
import com.unionbankassociation.fragments.ContactUsFrgament;
import com.unionbankassociation.fragments.DisciplinaryActionFragment;
import com.unionbankassociation.fragments.NewMedicalScheme;
import com.unionbankassociation.fragments.NonSunOrdinatorFragment;
import com.unionbankassociation.fragments.PartTimeEmployeeFragment;
import com.unionbankassociation.fragments.PensionFragment;
import com.unionbankassociation.fragments.PhotoGalleryFragment;
import com.unionbankassociation.fragments.SalaryComponentFragment;
import com.unionbankassociation.fragments.ServiceConditionFragment;
import com.unionbankassociation.fragments.ServiceConditionbpsFragment;

public class CommonActivityForFragment extends BaseActivity {
    int fragmentType;
    private String title = "";
    private String description = "";
    private String headerTitle = "";

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
            case 10:
                addFragmentWithBackstack(R.id.container, new NewMedicalScheme(), NewMedicalScheme.class.getSimpleName());
                break;
            case 11:
                addFragmentWithBackstack(R.id.container, new ServiceConditionFragment(), ServiceConditionFragment.class.getSimpleName());
                break;
            case 12:
                addFragmentWithBackstack(R.id.container, new PartTimeEmployeeFragment(), PartTimeEmployeeFragment.class.getSimpleName());
                break;
            case 13:
                addFragmentWithBackstack(R.id.container, new PhotoGalleryFragment(), PhotoGalleryFragment.class.getSimpleName());
                break;
            case 14:
                addFragmentWithBackstack(R.id.container, new ContactUsFrgament(), ContactUsFrgament.class.getSimpleName());
                break;

            case 15:
//                Bundle bundle4 = new Bundle();
//                bundle4.putString("TITLE", title);
//                bundle4.putString("DESCRIPTION", description);
//                bundle4.putString("HEADER_TTILE", getString(R.string.basic_pay));
//                CommonFragment fragment4 = new CommonFragment();
//                fragment4.setArguments(bundle4);
//                getIntentData();
                addFragmentWithBackstack(R.id.container, new SalaryComponentFragment(), SalaryComponentFragment.class.getSimpleName());
                break;
            case 16:
                getIntentData();
                Bundle bundle4 = new Bundle();
                bundle4.putString("TITLE", title);
                bundle4.putString("DESCRIPTION", description);
                bundle4.putString("HEADER_TTILE", headerTitle);
                CommonServiceConditionFragment fragment4 = new CommonServiceConditionFragment();
                fragment4.setArguments(bundle4);
                addFragmentWithBackstack(R.id.container, fragment4, CommonServiceConditionFragment.class.getSimpleName());
                break;

        }
    }

    private void getIntentData() {
        fragmentType = getIntent().getExtras().getInt("FRAGMENT");
        title = getIntent().getExtras().getString("TITLE", "");
        headerTitle = getIntent().getExtras().getString("HEADER_TTILE", "");
        description = getIntent().getExtras().getString("DESCRIPTION", "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
