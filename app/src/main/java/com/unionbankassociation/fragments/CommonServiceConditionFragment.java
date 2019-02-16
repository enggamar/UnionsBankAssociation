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
import com.unionbankassociation.databinding.LayoutCommonViewBinding;

public class CommonServiceConditionFragment extends Fragment {
    LayoutCommonViewBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutCommonViewBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();

    }

    private void initView() {
        getArgumentsData();
        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
    }

    private void getArgumentsData() {
        if (getArguments() != null) {
            String title = getArguments().getString("TITLE", "");
            String description = getArguments().getString("DESCRIPTION", "");
            String headerTitle = getArguments().getString("HEADER_TTILE", "");
            mBinding.tvDescription.setText(description);
            mBinding.tvTitle.setText(title);
            mBinding.header.title.setText(headerTitle);

        }
    }
}
