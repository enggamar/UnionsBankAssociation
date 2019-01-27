package com.unionbankassociation.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unionbankassociation.databinding.ActivityHomeBinding;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ImageView ivMenu, ivServiceConditionSubmenu;
    private LinearLayout mSubMenuServiceConditions, llServiceCondition;
    private ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();
        setLisner();
    }

    private void setLisner() {
        llServiceCondition.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        llServiceCondition.setSelected(false);
    }

    /*
     * Initialize all views Ids
     * */
    private void initView() {
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        llServiceCondition = (LinearLayout) findViewById(R.id.ll_service_condition);
        ivServiceConditionSubmenu = (ImageView) findViewById(R.id.iv_service_condition_submenu);
        mSubMenuServiceConditions = (LinearLayout) findViewById(R.id.ll_service_condition_submenu);

    }

    private void openMenu() {
        if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else if (!mDrawerLayout.isDrawerOpen(Gravity.END)) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                openMenu();
                break;
            case R.id.ll_about_us:
                break;
            case R.id.ll_achievements:
                break;
            case R.id.ll_activities:
                break;
            case R.id.ll_bank_wise_settlement:
                break;
            case R.id.ll_contact_us:
                break;
            case R.id.ll_news:
                break;
            case R.id.ll_photo_gallery:
                break;
            case R.id.ll_service_condition_submenu:
                break;
            case R.id.tv_at_glance:
                break;
            case R.id.tv_clearical_deployment_condition:
                break;
            case R.id.tv_disciplinary_action:
                break;
            case R.id.tv_discription:
                break;
            case R.id.tv_sub_service_condition:

                break;
            case R.id.tv_new_medical_scheme:
                break;
            case R.id.ll_service_condition:
                if (ivServiceConditionSubmenu.isSelected()) {
                    setSubMenuServiceCondition(false, View.VISIBLE);
                } else {
                    setSubMenuServiceCondition(true, View.GONE);
                }
                break;
            case R.id.rl_logout:
                Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void setSubMenuServiceCondition(boolean selection, int visible) {
        ivServiceConditionSubmenu.setSelected(selection);
        mSubMenuServiceConditions.setVisibility(visible);
        if (!selection)
            ivServiceConditionSubmenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_up_arrow));
        else
            ivServiceConditionSubmenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_down));
    }
}
