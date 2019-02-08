package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel extends BaseResponseBean {

    @SerializedName("RESULT")
    private LoginData mLoginData;

    public LoginData getmLoginData() {
        return mLoginData;
    }

    public void setmLoginData(LoginData mLoginData) {
        this.mLoginData = mLoginData;
    }
}
