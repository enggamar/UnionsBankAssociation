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

public class ServiceConditionFragment extends Fragment {

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

    }

    private void openActivityForFragments(int type) {

        Intent intent = new Intent(getActivity(), CommonActivityForFragment.class);
        intent.putExtra("FRAGMENT", type);
        startActivity(intent);
    }
}
