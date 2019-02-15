package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class PhotoGalleryModel extends BaseResponseBean {

    @SerializedName("RESULT")
    private PhotoGalleryDataModel mNotice;

    public PhotoGalleryDataModel getmNotice() {
        return mNotice;
    }

    public void setmNotice(PhotoGalleryDataModel mNotice) {
        this.mNotice = mNotice;
    }
}
