package com.unionbankassociation.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    private LinearLayout tvNews, tvAchievment, tvBankWiseSettleMent;
    private int currentPageNumber = 1;
    private boolean isLoading;
    private RelativeLayout rlLogOut;
    private LinearLayout llActivities;

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
        adapter = new NotificationAdapter(this, mNotificationList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvNotification.setLayoutManager(layoutManager);
        mBinding.rvNotification.setAdapter(adapter);
        mBinding.rvNotification.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItems = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if (isLoading) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItems
                                && firstVisibleItemPosition >= 0) {
                            isLoading = false;
                            hitNewsListing(currentPageNumber++);

                        }
                    }
                }

            }
        });
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
        tvAchievment.setOnClickListener(this);
        tvNews.setOnClickListener(this);
        tvBankWiseSettleMent.setOnClickListener(this);
        rlLogOut.setOnClickListener(this);
        llActivities.setOnClickListener(this);
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
        tvNews = (LinearLayout) findViewById(R.id.ll_news);
        tvBankWiseSettleMent = (LinearLayout) findViewById(R.id.ll_bank_wise_settlement);
        tvAchievment = (LinearLayout) findViewById(R.id.ll_achievements);
        rlLogOut = (RelativeLayout) findViewById(R.id.rl_logout);
        llActivities = (LinearLayout) findViewById(R.id.ll_activities);
        mBinding.toolbar.title.setText(getString(R.string.news));
        hitNewsListing(1);
        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hitNewsListing(1);
            }
        });

    }

    private void hitNewsListing(int currentPage) {
        if (!mBinding.swipe.isRefreshing())
            mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getNotice(AppSharedPreference.getInstance().getString(this, AppSharedPreference.ACCESS_TOKEN), "1", currentPage);
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
                openActivityForFragments(8);
                break;
            case R.id.ll_activities:
                openActivityForFragments(9);
                break;
            case R.id.ll_bank_wise_settlement:
                openActivityForFragments(7);
                break;
            case R.id.ll_contact_us:
                break;
            case R.id.ll_news:
                openActivityForFragments(6);
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
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
        Intent intent = new Intent(HomeActivity.this, CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        startActivity(intent);
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        if (mBinding.swipe != null) {
            mBinding.swipe.setRefreshing(false);
            mNotificationList.clear();
        }
        String token = null, refreshToken = null;
        try {
            JSONObject object = new JSONObject(response);
            AppUtils.showToast(HomeActivity.this, object.getString(AppConstant.message));
            NoticModel bean = new Gson().fromJson(response, NoticModel.class);
            int code = object.getInt(AppConstant.code);

            if (bean.getNextpage() > 0) {
                isLoading = true;
            } else {
                isLoading = false;
            }
            mNotificationList.addAll(bean.getmNotice().getNoticeDetails());
            if (mNotificationList.size() > 0) {
                mBinding.rvNotification.setVisibility(View.VISIBLE);
                mBinding.noData.setVisibility(View.GONE);
            } else {
                mBinding.rvNotification.setVisibility(View.GONE);
                mBinding.noData.setVisibility(View.VISIBLE);
            }

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
        }

    }


    @Override
    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        if (mBinding.swipe != null) {
            mBinding.swipe.setRefreshing(false);
        }
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
        if (mBinding.swipe != null) {
            mBinding.swipe.setRefreshing(false);
        }
    }
}
