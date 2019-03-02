package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoticDetailsData extends BaseResponseBean{

    @SerializedName("data")
    private NoticData noticeDetails;

    public NoticData getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(NoticData noticeDetails) {
        this.noticeDetails = noticeDetails;
    }
}
