package com.unionbankassociation.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.unionbankassociation.R;
import com.unionbankassociation.databinding.ActivityDetailViewBinding;
import com.unionbankassociation.models.NoticData;

public class NoticeDetailActivity extends BaseActivity {

    private ActivityDetailViewBinding mBinding;
    private NoticData mData;

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
        }
    }

    private void setData(NoticData mData) {
        mBinding.tvTitle.setText(mData.getTitle());
        mBinding.tvDescription.setText(mData.getDescription());
        mBinding.tvTime.setText(mData.getCreatedDateTime());
        mBinding.header.title.setText(getNoticeType(mData.getNoticeType()));
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
}
