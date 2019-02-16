package com.unionbankassociation.fragments;

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

public class SalaryComponentFragment extends Fragment {
    private LayoutSalaryComponentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutSalaryComponentBinding.inflate(inflater, container, false);

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
    }
}
