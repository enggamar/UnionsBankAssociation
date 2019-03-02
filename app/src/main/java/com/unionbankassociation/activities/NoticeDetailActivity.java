package com.unionbankassociation.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.databinding.ActivityDetailViewBinding;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.NoticData;
import com.unionbankassociation.models.NoticDetailsModel;
import com.unionbankassociation.network.ApiCall;
import com.unionbankassociation.network.ApiInterface;
import com.unionbankassociation.network.RestApi;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.utils.AppUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class NoticeDetailActivity extends BaseActivity implements NetworkListener {

    private ActivityDetailViewBinding mBinding;
    private NoticData mData;
    private String mNoticeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_view);
        getIntentData();
        initView();

    }

    private void getIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("DETAILS")) {
            mData = (NoticData) getIntent().getExtras().get("DETAILS");
            if (mData != null)
                setData(mData);
        } else if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("notice_id")) {
            mNoticeId = getIntent().getExtras().getString("notice_id");
            if (mNoticeId != null && mNoticeId.length() > 0) {
                hitNoticeDetailsAPi(mNoticeId);
            }
        }
    }

    private void hitNoticeDetailsAPi(String mNoticeId) {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getNoticeDetails(AppSharedPreference.getInstance().getString(this, AppSharedPreference.ACCESS_TOKEN), mNoticeId);
        ApiCall.getInstance().hitService(this, call, this, 1);

    }

    private void setData(NoticData mData) {
        mBinding.tvTitle.setText(mData.getTitle());
        mBinding.tvDescription.setText(mData.getDescription());
        mBinding.tvTime.setText(mData.getCreatedDateTime());
        mBinding.header.title.setText(getNoticeType(mData.getNoticeType()));
        Glide.with(this).asBitmap().load(mData.getImage()).apply(RequestOptions.placeholderOf(R.drawable.ic_logo)).into(mBinding.ivNotice);

    }

    private String getNoticeType(int noticeType) {
        String type = "";
        switch (noticeType) {
            case 1:
                type = getString(R.string.news);
                break;
            case 2:
                type = getString(R.string.achivments);
                break;
            case 3:
                type = getString(R.string.bank_wise_sttlement);
                break;
        }
        return type;
    }

    private void initView() {
        mBinding.header.ivMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        NoticDetailsModel bean = new Gson().fromJson(response, NoticDetailsModel.class);
        if (bean.getCODE() == 200) {
            setData(bean.getNoticeDetails().getNoticeDetails());
        }

    }

    @Override
    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        AppUtils.showToast(this, response);
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
    }
}
