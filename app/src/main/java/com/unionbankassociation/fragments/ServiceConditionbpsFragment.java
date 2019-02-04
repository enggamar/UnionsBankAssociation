package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;

public class ServiceConditionbpsFragment extends Fragment {

    private TextView tvTitle;
    private AppCompatImageView ivBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_features_tens_bps, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.title);
        ivBack = view.findViewById(R.id.iv_menu);
        ivBack.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        tvTitle.setText("10th BPS At Glance");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
    }
}
