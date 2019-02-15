package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.adapters.NotificationAdapter;
import com.unionbankassociation.databinding.FragmentCommonBinding;
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

public class CommonFragment extends Fragment implements NetworkListener {
    private FragmentCommonBinding mBinding;
    private boolean isLoading;
    private ImageView ivMenu, ivServiceConditionSubmenu;
    private LinearLayout mSubMenuServiceConditions, llServiceCondition, llAboutus;
    private ArrayList<NoticData> mNotificationList;
    private NotificationAdapter adapter;
    private AppCompatTextView tvGlance, tvClearicalDeploymentCondition;
    private AppCompatTextView tvPensionScheme;
    private AppCompatTextView tvDispliniary;
    private int currentPageNumber = 1, Type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentCommonBinding.inflate(inflater, container, false);
        initView(mBinding);
        setUpView();
        hitNewsListing(currentPageNumber);
        return mBinding.getRoot();
    }

    /*
     * Initialize all views Ids
     * */
    private void initView(FragmentCommonBinding view) {
        view.toolbar.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        if (getArguments() != null) {
            Type = getArguments().getInt("TYPE");
            mBinding.toolbar.title.setText(getType(Type));
        }
        view.toolbar.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });

        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPageNumber = 1;
                if (AppUtils.isInternetAvailable(getActivity()))
                    hitNewsListing(1);
                else
                    AppUtils.showToast(getActivity(), getString(R.string.no_internet));
            }
        });

    }

    private String getType(int type) {
        String titleType = "";
        switch (type) {
            case 1:
                titleType = getString(R.string.news);
                break;
            case 3:
                titleType = getString(R.string.achivments);
                break;
            case 2:
                titleType = getString(R.string.bank_wise_sttlement);
                break;
            case 4:
                titleType = getString(R.string.our_activities);
                break;
        }
        return titleType;
    }

    private void setUpView() {
        mNotificationList = new ArrayList<>();
        adapter = new NotificationAdapter(getContext(), mNotificationList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
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
                            currentPageNumber++;
                            if (AppUtils.isInternetAvailable(getActivity()))
                                hitNewsListing(currentPageNumber);
                            else
                                AppUtils.showToast(getActivity(), getString(R.string.no_internet));

                        }
                    }
                }

            }
        });
    }

    private void hitNewsListing(int currentPage) {
        if (mBinding.swipe != null && !mBinding.swipe.isRefreshing())
            mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getNotice(AppSharedPreference.getInstance().getString(getContext(), AppSharedPreference.ACCESS_TOKEN), String.valueOf(Type), currentPage);
        ApiCall.getInstance().hitService(getContext(), call, this, 1);

    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
               String token = null, refreshToken = null;
        try {
            if (mBinding.swipe.isRefreshing()) {
                mNotificationList.clear();
            }
            if (mBinding.swipe != null) {
                mBinding.swipe.setRefreshing(false);
            }
            JSONObject object = new JSONObject(response);
            AppUtils.showToast(getActivity(), object.getString(AppConstant.message));
            NoticModel bean = new Gson().fromJson(response, NoticModel.class);
            int code = object.getInt(AppConstant.code);

            if (bean.getmNotice().getNextpage() > currentPageNumber) {
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
        if (mNotificationList.size() > 0) {
            mBinding.rvNotification.setVisibility(View.VISIBLE);
            mBinding.noData.setVisibility(View.GONE);
        } else {
            mBinding.rvNotification.setVisibility(View.GONE);
            mBinding.noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
        if (mBinding.swipe != null) {
            mBinding.swipe.setRefreshing(false);
        }
        if (mNotificationList.size() > 0) {
            mBinding.rvNotification.setVisibility(View.VISIBLE);
            mBinding.noData.setVisibility(View.GONE);
        } else {
            mBinding.rvNotification.setVisibility(View.GONE);
            mBinding.noData.setVisibility(View.VISIBLE);
        }
    }

}
