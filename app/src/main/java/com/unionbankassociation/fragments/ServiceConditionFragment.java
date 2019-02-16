package com.unionbankassociation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.databinding.LayoutServiceConditionTypeBinding;

public class ServiceConditionFragment extends Fragment implements View.OnClickListener {

    private AppCompatTextView tvTitle;
    private AppCompatImageView ivBack;


    private LayoutServiceConditionTypeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutServiceConditionTypeBinding.inflate(inflater, container, false);
        initView(mBinding);
        return mBinding.getRoot();
    }

    private void initView(LayoutServiceConditionTypeBinding view) {

        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.service_condition));
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
        mBinding.cardClericalStaff.setSelected(false);
        mBinding.cardClericalStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mBinding.cardClericalStaff.isSelected()) {
                    mBinding.cardClericalStaff.setSelected(true);
                    mBinding.llClericalStaffSubMenu.setVisibility(View.VISIBLE);
                } else {
                    mBinding.cardClericalStaff.setSelected(false);
                    mBinding.llClericalStaffSubMenu.setVisibility(View.GONE);
                }
                mBinding.llSubStaffSubMenu.setVisibility(View.GONE);
            }
        });
        mBinding.cardSubstaff.setSelected(false);
        mBinding.cardSubstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.cardSubstaff.isSelected()) {
                    mBinding.cardSubstaff.setSelected(true);
                    mBinding.llSubStaffSubMenu.setVisibility(View.VISIBLE);
                } else {
                    mBinding.cardSubstaff.setSelected(false);
                    mBinding.llSubStaffSubMenu.setVisibility(View.GONE);
                }
                mBinding.llClericalStaffSubMenu.setVisibility(View.GONE);
            }
        });
        mBinding.cardPartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityForFragments(12);
            }
        });
        mBinding.cardClericalStaffSalary.setOnClickListener(this);
        mBinding.cardClericalStaffJobProfile.setOnClickListener(this);
        mBinding.cardClericalStaffLeavePolicy.setOnClickListener(this);
        mBinding.cardClericalStaffOthersAllowance.setOnClickListener(this);
        mBinding.cardClericalStaffWorkingHours.setOnClickListener(this);
        mBinding.cardSubStaffSalary.setOnClickListener(this);
        mBinding.cardSubStaffJobProfile.setOnClickListener(this);
        mBinding.cardSubStaffLeavePolicy.setOnClickListener(this);
        mBinding.cardSubStaffOthersAllowance.setOnClickListener(this);
        mBinding.cardSubStaffWorkingHours.setOnClickListener(this);
    }

    private void openActivityForFragments(int type) {

        Intent intent = new Intent(getActivity(), CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_sub_staff_salary:
                openCommonActivity(19);
                break;
            case R.id.card_clerical_staff_salary:
                openCommonActivity(15);
//                openCommonActivity(getString(R.string.basic_pay), getString(R.string.basic_pay_service_condition), getString(R.string.basic_pay));
                break;
            case R.id.card_clerical_staff_job_profile:
                openCommonActivity(18);
                break;
            case R.id.card_sub_staff_leave_policy:
            case R.id.card_clerical_staff_leave_policy:
                openCommonActivity(17);
                break;
            case R.id.card_leave_policy:
                break;
            case R.id.card_sub_staff_job_profile:
            case R.id.card_substaff_job_profile:
                openCommonActivity(16, getString(R.string.duties_of_substaff), getString(R.string.job_profile_substaff), getString(R.string.job_profile));
                break;
            case R.id.card_clerical_staff_others_allowance:
                openCommonActivity(16, getString(R.string.other_allowance_serverice), getString(R.string.other_allowance_service_condition), getString(R.string.other_allowances));
                break;
            case R.id.card_clerical_staff_working_hours:
                openCommonActivity(16, getString(R.string.working_hours), getString(R.string.working_hours_service_condition), getString(R.string.working_hours));
                break;

            case R.id.card_sub_staff_others_allowance:
                openCommonActivity(16, getString(R.string.other_allowance_serverice), getString(R.string.other_allowance_service_condition), getString(R.string.other_allowances));
                break;
            case R.id.card_sub_staff_working_hours:
                openCommonActivity(16, getString(R.string.working_hours), getString(R.string.working_hours_service_condition), getString(R.string.working_hours));
                break;
        }
    }

    private void openCommonActivity(int type) {
        Intent intent = new Intent(getActivity(), CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        startActivity(intent);
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
