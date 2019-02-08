package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class NoticModel extends BaseResponseBean {

    @SerializedName("RESULT")
    private NoticDataModel mNotice;
    @SerializedName("next_page")
    private int nextpage;

    public int getNextpage() {
        return nextpage;
    }

    public void setNextpage(int nextpage) {
        this.nextpage = nextpage;
    }

    public NoticDataModel getmNotice() {
        return mNotice;
    }

    public void setmNotice(NoticDataModel mNotice) {
        this.mNotice = mNotice;
    }
}
