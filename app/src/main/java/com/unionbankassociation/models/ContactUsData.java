package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactUsData {

    @SerializedName("data")
    private ArrayList<ContactUSListBean> mContactList;

    public ArrayList<ContactUSListBean> getmContactList() {
        return mContactList;
    }

    public void setmContactList(ArrayList<ContactUSListBean> mContactList) {
        this.mContactList = mContactList;
    }
}
