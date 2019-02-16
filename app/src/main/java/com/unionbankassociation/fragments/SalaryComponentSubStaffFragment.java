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
import com.unionbankassociation.databinding.LayoutSalaryComponentBinding;
import com.unionbankassociation.databinding.LayoutSubSalaryComponentBinding;

public class SalaryComponentSubStaffFragment extends Fragment implements View.OnClickListener {
    private LayoutSubSalaryComponentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutSubSalaryComponentBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {

        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.salary_components));
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });

        mBinding.cardBasicPay.setOnClickListener(this);
        mBinding.cardDearnessAllowance.setOnClickListener(this);
        mBinding.cardHra.setOnClickListener(this);
        mBinding.cardMedicalAid.setOnClickListener(this);
        mBinding.cardOfficating.setVisibility(View.GONE);
        mBinding.cardPopFpp.setVisibility(View.GONE);
        mBinding.cardSpecialAllownace.setOnClickListener(this);
        mBinding.cardSpecialPay.setOnClickListener(this);
        mBinding.cardStagnation.setVisibility(View.GONE);
        mBinding.cardTransportAllowance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_basic_pay:
                openCommonActivity(16, getString(R.string.substaff_pay), getString(R.string.basic_pay_sub_staff), getString(R.string.substaff_pay));

                break;
            case R.id.card_dearness_allowance:
                openCommonActivity(16, getString(R.string.dearnes_allowance_title), getString(R.string.dearness_allowance_data_), getString(R.string.dearnes_allowance_title));

                break;
            case R.id.card_hra:
                openCommonActivity(16, getString(R.string.hra_housing_rent_allowance), getString(R.string.hra_data), getString(R.string.hra_housing_rent_allowance));

                break;
            case R.id.card_medical_aid:
                openCommonActivity(16, getString(R.string.medical_aid_title), getString(R.string.medical_aid_data_), getString(R.string.medical_aid_title));

                break;
//            case R.id.card_pop_fpp:
//                openCommonActivity(16, getString(R.string.professional_qualification), getString(R.string.professional_pay), getString(R.string.professional_qualification));
//
//                break;
//            case R.id.card_officating:
//                openCommonActivity(16, getString(R.string.officiating_allowance), getString(R.string.officating_allowance_data_), getString(R.string.officiating_allowance));
//
//                break;
            case R.id.card_special_allownace:
                openCommonActivity(16, getString(R.string.special_allowance), getString(R.string.special_allowanc_data_), getString(R.string.special_allowance));

                break;
            case R.id.card_special_pay:
                openCommonActivity(16, getString(R.string.special_pay_), getString(R.string.special_pay_sub_staff), getString(R.string.special_pay_));

                break;
//            case R.id.card_stagnation:
//                openCommonActivity(16, getString(R.string.stagnation_increment), getString(R.string.stafnation_data), getString(R.string.stagnation_increment));
//
//                break;
            case R.id.card_Transport_allowance:
                openCommonActivity(16, getString(R.string.tarnsport_allowance), getString(R.string.transport_data), getString(R.string.tarnsport_allowance));
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
