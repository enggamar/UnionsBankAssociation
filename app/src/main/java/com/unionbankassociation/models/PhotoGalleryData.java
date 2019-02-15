package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoGalleryData implements Serializable {


    @SerializedName("id")
    private String id;
    @SerializedName("banner_name")
    private String imgName;
    @SerializedName("notice_type")
    private int noticeType;
    @SerializedName("image")
    private String image;
    @SerializedName("status")
    private String status;
    @SerializedName("created_date")
    private String createdDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
