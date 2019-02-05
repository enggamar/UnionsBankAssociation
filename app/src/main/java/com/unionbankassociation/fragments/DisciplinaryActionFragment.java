package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisciplinaryActionFragment extends Fragment {

    private AppCompatTextView tvTitle;
    private AppCompatImageView ivBack;

    @BindView(R.id.card_old_pension)
    CardView cardViewOldPension;
    @BindView(R.id.card_old_pension_title)
    CardView cardViewOldPensionTitle;
    @BindView(R.id.card_new_pension)
    CardView cardNewPension;
    @BindView(R.id.card_new_pension_data)
    CardView cardNewPensionData;
    @BindView(R.id.card_graduity)
    CardView cardGraduity;
    @BindView(R.id.tv_pf_graduity)
    CardView cardPfGraduity;
    @BindView(R.id.tv_suspension_allowance)
    CardView tvSuspensionAllowance;
    @BindView(R.id.card_tv_suspension_allowance)
    CardView carSuspension;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_disciplinary_action, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.title);
        ivBack = view.findViewById(R.id.iv_menu);
        ivBack.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        tvTitle.setText(getString(R.string.pension_scheme));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
        cardViewOldPensionTitle.setSelected(false);
        cardViewOldPensionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardGraduity.setVisibility(View.GONE);
                if (!cardViewOldPensionTitle.isSelected()) {
                    cardViewOldPensionTitle.setSelected(true);
                    cardViewOldPension.setVisibility(View.VISIBLE);
                } else {
                    cardViewOldPensionTitle.setSelected(false);

                    cardViewOldPension.setVisibility(View.GONE);

                }
                cardNewPensionData.setVisibility(View.GONE);
            }
        });
        cardNewPension.setSelected(false);
        cardNewPension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardGraduity.setVisibility(View.GONE);
                if (!cardNewPension.isSelected()) {
                    cardNewPension.setSelected(true);
                    cardNewPensionData.setVisibility(View.VISIBLE);
                } else {
                    cardNewPension.setSelected(false);
                    cardNewPensionData.setVisibility(View.GONE);

                }
                carSuspension.setVisibility(View.GONE);
                cardViewOldPension.setVisibility(View.GONE);
            }
        });
        cardPfGraduity.setSelected(false);
        cardPfGraduity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cardPfGraduity.isSelected()) {
                    cardPfGraduity.setSelected(true);

                    cardGraduity.setVisibility(View.VISIBLE);
                } else {
                    cardPfGraduity.setSelected(false);

                    cardGraduity.setVisibility(View.GONE);

                }
                cardViewOldPension.setVisibility(View.GONE);
                cardNewPensionData.setVisibility(View.GONE);
                carSuspension.setVisibility(View.GONE);
            }
        });
        tvSuspensionAllowance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvSuspensionAllowance.isSelected()) {
                    tvSuspensionAllowance.setSelected(true);

                    carSuspension.setVisibility(View.VISIBLE);
                } else {
                    tvSuspensionAllowance.setSelected(false);

                    carSuspension.setVisibility(View.GONE);

                }
                cardGraduity.setVisibility(View.GONE);
                cardViewOldPension.setVisibility(View.GONE);
                cardNewPensionData.setVisibility(View.GONE);
            }
        });
    }
}
