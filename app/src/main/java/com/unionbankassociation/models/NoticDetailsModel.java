package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoticDetailsModel extends BaseResponseBean {

    @SerializedName("RESULT")
    private NoticDetailsData noticeDetails;

    public NoticDetailsData getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(NoticDetailsData noticeDetails) {
        this.noticeDetails = noticeDetails;
    }
}
