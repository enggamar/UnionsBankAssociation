package com.unionbankassociation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.databinding.LayoutClericalStaffBinding;

public class ClericalStaffFragment extends Fragment implements View.OnClickListener {

    private LayoutClericalStaffBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = LayoutClericalStaffBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mBinding.cardHeadCashier.setOnClickListener(this);
        mBinding.cardSpecialAssistant.setOnClickListener(this);
        mBinding.cardSwoA.setOnClickListener(this);
        mBinding.cardSwoB.setOnClickListener(this);
        mBinding.header.ivMenu.setOnClickListener(this);
        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.job_profile));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.card_swo_a:
                openCommonActivity(16, getString(R.string.duties_of_swo), getString(R.string.swo_a_data), getString(R.string.swo_a));
                break;
            case R.id.card_swo_b:
                openCommonActivity(16, getString(R.string.duties_of_swo_b), getString(R.string.swo_b_data), getString(R.string.swo_b));
                break;
            case R.id.card_head_cashier:
                openCommonActivity(16, getString(R.string.duties_of_head_cashier), getString(R.string.head_cashier_data), getString(R.string.head_cashier));
                break;
            case R.id.card_special_assistant:
                openCommonActivity(16, getString(R.string.duties_of_special_assistant), getString(R.string.special_assistant_data), getString(R.string.special_assistant));
                break;
            case R.id.iv_menu:
                ((CommonActivityForFragment) getActivity()).finish();
                break;
        }
    }

    private void openCommonActivity(int type, String title, String description, String headerTitle) {
        Intent intent = new Intent(getActivity(), CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        intent.putExtra("TITLE", title);
        intent.putExtra("DESCRIPTION", description);
        intent.putExtra("HEADER_TTILE", headerTitle);
        startActivity(intent);
    }
}
