package com.unionbankassociation.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.adapters.NotificationAdapter;
import com.unionbankassociation.databinding.ActivityHomeBinding;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.NoticData;
import com.unionbankassociation.models.NoticModel;
import com.unionbankassociation.network.ApiCall;
import com.unionbankassociation.network.ApiInterface;
import com.unionbankassociation.network.RestApi;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.utils.AppUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NetworkListener {
    private DrawerLayout mDrawerLayout;
    private ImageView ivMenu, ivServiceConditionSubmenu;
    private LinearLayout mSubMenuServiceConditions, llServiceCondition, llAboutus;
    private ActivityHomeBinding mBinding;
    private ArrayList<NoticData> mNotificationList;
    private NotificationAdapter adapter;
    private AppCompatTextView tvGlance, tvClearicalDeploymentCondition;
    private AppCompatTextView tvPensionScheme;
    private AppCompatTextView tvDispliniary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();
        setLisner();
        setUpView();
    }

    private void setUpView() {
        mNotificationList = new ArrayList<>();
        adapter = new NotificationAdapter(this, mNotificationList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvNotification.setLayoutManager(manager);
        mBinding.rvNotification.setAdapter(adapter);
    }

    private void setLisner() {
        llServiceCondition.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        llServiceCondition.setSelected(false);
        llAboutus.setOnClickListener(this);
        tvGlance.setOnClickListener(this);
        tvClearicalDeploymentCondition.setOnClickListener(this);
        tvPensionScheme.setOnClickListener(this);
        tvDispliniary.setOnClickListener(this);
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
        llAboutus = (LinearLayout) findViewById(R.id.ll_about_us);
        tvGlance = (AppCompatTextView) findViewById(R.id.tv_at_glance);
        tvClearicalDeploymentCondition = (AppCompatTextView) findViewById(R.id.tv_clearical_deployment_condition);
        tvPensionScheme = (AppCompatTextView) findViewById(R.id.tv_pansion_scheme);
        tvDispliniary = (AppCompatTextView) findViewById(R.id.tv_disciplinary_action);
        hitNewsListing();

    }

    private void hitNewsListing() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getNotice(AppSharedPreference.getInstance().getString(this, AppSharedPreference.ACCESS_TOKEN), "1", 1);
        ApiCall.getInstance().hitService(HomeActivity.this, call, this, 1);

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
                openActivityForFragments(1);
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
                openActivityForFragments(2);
                break;
            case R.id.tv_clearical_deployment_condition:
                openActivityForFragments(3);
                break;
            case R.id.tv_disciplinary_action:
                openActivityForFragments(5);
                break;
            case R.id.tv_discription:
                break;
            case R.id.tv_sub_service_condition:

                break;
            case R.id.tv_new_medical_scheme:
                break;
            case R.id.tv_pansion_scheme:
                openActivityForFragments(4);
                break;
            case R.id.ll_service_condition:
                if (ivServiceConditionSubmenu.isSelected()) {
                    setSubMenuServiceCondition(false, View.VISIBLE);
                } else {
                    setSubMenuServiceCondition(true, View.GONE);
                }
                break;
            case R.id.rl_logout:
                AppSharedPreference.getInstance().clearAllPrefs(this);
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

    private void openActivityForFragments(int type) {
        Intent intent = new Intent(HomeActivity.this, CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        startActivity(intent);
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        String token = null, refreshToken = null;
        try {
            JSONObject object = new JSONObject(response);
            AppUtils.showToast(HomeActivity.this, object.getString(AppConstant.message));
            NoticModel bean = new Gson().fromJson(response, NoticModel.class);
            int code = object.getInt(AppConstant.code);
            mNotificationList.addAll(bean.getmNotice().getNoticeDetails());
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
        }

    }


    @Override
    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
    }
}
