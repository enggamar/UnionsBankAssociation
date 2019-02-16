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
import com.unionbankassociation.databinding.LayoutLeavePolicyBinding;

public class LeavePolicyFragment extends Fragment implements View.OnClickListener {

    private LayoutLeavePolicyBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutLeavePolicyBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mBinding.cardCasualLeave.setOnClickListener(this);
        mBinding.cardExtraordinaryLeave.setOnClickListener(this);
        mBinding.cardJoiningTimeLeave.setOnClickListener(this);
        mBinding.cardLeaveFareConcession.setOnClickListener(this);
        mBinding.cardMaternityLeave.setOnClickListener(this);
        mBinding.cardPaternityLeave.setOnClickListener(this);
        mBinding.cardPrivilegeLeave.setOnClickListener(this);
        mBinding.cardSickLeave.setOnClickListener(this);
        mBinding.cardSpecialLeave.setOnClickListener(this);
        mBinding.cardSpecialSickLeave.setOnClickListener(this);
        mBinding.header.ivMenu.setOnClickListener(this);
        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.leave_policy));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_casual_leave:
                openCommonActivity(16, getString(R.string.casual_leave), getString(R.string.casual_leave_data), getString(R.string.casual_leave));
                break;
            case R.id.card_extraordinary_leave:
                openCommonActivity(16, getString(R.string.extraordinary_leave), getString(R.string.extraordinary_data), getString(R.string.extraordinary_leave));
                break;
            case R.id.card_joining_time_leave:
                openCommonActivity(16, getString(R.string.joining_time_leave), getString(R.string.joining_time_leave_data), getString(R.string.joining_time_leave));
                break;
            case R.id.card_maternity_leave:
                openCommonActivity(16, getString(R.string.maternity_leave), getString(R.string.maternity_leave_data), getString(R.string.maternity_leave));
                break;
            case R.id.card_paternity_leave:
                openCommonActivity(16, getString(R.string.paternity_leave), getString(R.string.paternity_leave_data), getString(R.string.paternity_leave));
                break;
            case R.id.card_leave_fare_concession:
                openCommonActivity(16, getString(R.string.leave_fare_concession), getString(R.string.leave_fare_concession_data), getString(R.string.leave_fare_concession));
                break;
            case R.id.card_sick_leave:
                openCommonActivity(16, getString(R.string.sick_leave), getString(R.string.sick_leave_data), getString(R.string.sick_leave));
                break;
            case R.id.card_special_sick_leave:
                openCommonActivity(16, getString(R.string.special_sick_leave), getString(R.string.special_sick_leave_data), getString(R.string.special_sick_leave));
                break;
            case R.id.card_special_leave:
                openCommonActivity(16, getString(R.string.special_leave), getString(R.string.special_leave_data), getString(R.string.special_leave));

                break;
            case R.id.card_privilege_leave:
                openCommonActivity(16, getString(R.string.privilege_leave), getString(R.string.privilage_leave_data), getString(R.string.privilege_leave));
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
