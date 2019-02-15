package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class NoticModel extends BaseResponseBean {

    @SerializedName("RESULT")
    private NoticDataModel mNotice;

    public NoticDataModel getmNotice() {
        return mNotice;
    }

    public void setmNotice(NoticDataModel mNotice) {
        this.mNotice = mNotice;
    }
}
