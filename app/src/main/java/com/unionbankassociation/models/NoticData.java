package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NoticData implements Serializable {


    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("notice_type")
    private int noticeType;
    @SerializedName("image")
    private String image;
    @SerializedName("status")
    private String status;
    @SerializedName("created_date")
    private String createdDateTime;
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
