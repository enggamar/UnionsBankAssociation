package com.unionbankassociation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.adapters.ContactUsAdapter;
import com.unionbankassociation.custom_dialogs.CustomIPlaceDialog;
import com.unionbankassociation.databinding.LayoutContactUsBinding;
import com.unionbankassociation.interfaces.DialogCallBack;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.ContactUSListBean;
import com.unionbankassociation.models.ContactUsBean;
import com.unionbankassociation.network.ApiCall;
import com.unionbankassociation.network.ApiInterface;
import com.unionbankassociation.network.RestApi;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ContactUsFrgament extends Fragment implements View.OnClickListener, NetworkListener, SwipeRefreshLayout.OnRefreshListener {

    private LayoutContactUsBinding mBinding;
    private ArrayList<ContactUSListBean> mList;
    private ContactUsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = LayoutContactUsBinding.inflate(inflater, container, false);
        initView();
        setUpList(mList);
        callApi();
        return mBinding.getRoot();
    }

    private void hitApi() {
        if (mBinding.swipe != null && !mBinding.swipe.isRefreshing())
            mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getContactList(AppSharedPreference.getInstance().getString(getContext(), AppSharedPreference.ACCESS_TOKEN), "1");
        ApiCall.getInstance().hitService(getContext(), call, this, 1);

    }

    private void setUpList(ArrayList<ContactUSListBean> mList) {
        mBinding.rvContactUs.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactUsAdapter(mList, getActivity());
        mBinding.rvContactUs.setAdapter(adapter);
    }

    private void initView() {
        mList = new ArrayList<>();
        mBinding.etSelectPlace.setOnClickListener(this);
        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.contact_us));
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });
        mBinding.swipe.setOnRefreshListener(this);
    }

    public ArrayList<ContactUSListBean> loadJSONFromAsset() {
        ArrayList<ContactUSListBean> contactUs = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("ContactUS.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray m_jArry = jsonObject.getJSONArray("contactUs");
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    ContactUSListBean selectionListBean = new ContactUSListBean();
                    String portfolio = jo_inside.getString("Portfolio");
                    String name = jo_inside.getString("Name");
                    String place = jo_inside.getString("Place");
                    String mobile = jo_inside.getString("Mobile");
                    String email = jo_inside.getString("Email");
                    selectionListBean.setName(name);
                    selectionListBean.setEmail(email);
                    selectionListBean.setMobile(mobile);
                    selectionListBean.setPlace(place);
                    selectionListBean.setPortfolio(portfolio);
                    contactUs.add(selectionListBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return contactUs;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_select_place:
                new CustomIPlaceDialog(getActivity(), mList, new DialogCallBack() {
                    @Override
                    public void onSubmit(String data) {
                        mBinding.etSelectPlace.setText(data);
                        adapter.getFilter().filter(data);
                    }
                }).show();
                break;
        }
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        stopReferesing();
        if (responseCode == 200) {
            ContactUsBean bean = new Gson().fromJson(response, ContactUsBean.class);
            if (mList != null)
                mList.clear();
            mList.addAll(bean.getContactUsData().getmContactList());
            if (mList.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void stopReferesing() {
        if (mBinding.swipe != null) {
            mBinding.swipe.setRefreshing(false);
        }
    }

    @Override
    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        stopReferesing();

    }

    @Override
    public void onFailure() {
        stopReferesing();
        mBinding.progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onRefresh() {
        callApi();
    }

    private void callApi() {
        if (AppUtils.isInternetAvailable(getActivity())) {
            hitApi();
        } else {
            AppUtils.showToast(getActivity(), getString(R.string.no_internet));
        }
    }
}
