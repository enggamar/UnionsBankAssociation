package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.adapters.PdfAdapter;
import com.unionbankassociation.databinding.FragmentCommonBinding;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.PdfData;
import com.unionbankassociation.models.PdfModel;
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

public class PdfFragment extends Fragment implements NetworkListener {
    private FragmentCommonBinding mBinding;
    private boolean isLoading;
    private ArrayList<PdfData> mNotificationList;
    private PdfAdapter adapter;
    private int currentPageNumber = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentCommonBinding.inflate(inflater, container, false);
        initView(mBinding);
        setUpView();
        hitPdfListing(currentPageNumber);
        return mBinding.getRoot();
    }

    /*
     * Initialize all views Ids
     * */
    private void initView(FragmentCommonBinding view) {
        view.toolbar.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));

        mBinding.toolbar.title.setText(getString(R.string.pdf));

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
                    hitPdfListing(1);
                else
                    AppUtils.showToast(getActivity(), getString(R.string.no_internet));
            }
        });

    }

    private void setUpView() {
        mNotificationList = new ArrayList<>();
        adapter = new PdfAdapter(getContext(), mNotificationList);
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
                                hitPdfListing(currentPageNumber);
                            else
                                AppUtils.showToast(getActivity(), getString(R.string.no_internet));

                        }
                    }
                }

            }
        });
    }

    private void hitPdfListing(int currentPage) {
        if (mBinding.swipe != null && !mBinding.swipe.isRefreshing())
            mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getPdfListing(AppSharedPreference.getInstance().getString(getContext(), AppSharedPreference.ACCESS_TOKEN), currentPage);
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
            PdfModel bean = new Gson().fromJson(response, PdfModel.class);
            int code = object.getInt(AppConstant.code);

            if (bean.getPdfDataModel().getNext_page() > currentPageNumber) {
                isLoading = true;
            } else {
                isLoading = false;
            }

            try {
                mNotificationList.addAll(bean.getPdfDataModel().getPdfData());
            } catch (Exception e) {

            }
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
