package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoticDataModel {
    public ArrayList<NoticData> getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(ArrayList<NoticData> noticeDetails) {
        this.noticeDetails = noticeDetails;
    }

    @SerializedName("result")
    private ArrayList<NoticData> noticeDetails;
}
