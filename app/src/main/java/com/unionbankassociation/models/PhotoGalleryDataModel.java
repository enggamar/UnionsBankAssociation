package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotoGalleryDataModel {
    public ArrayList<PhotoGalleryData> getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(ArrayList<PhotoGalleryData> noticeDetails) {
        this.noticeDetails = noticeDetails;
    }

    @SerializedName("result")
    private ArrayList<PhotoGalleryData> noticeDetails;
    @SerializedName("next_page")
    private int nextpage;

    public int getNextpage() {
        return nextpage;
    }

    public void setNextpage(int nextpage) {
        this.nextpage = nextpage;
    }
}
