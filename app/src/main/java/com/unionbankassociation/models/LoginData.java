package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("name")
    private String name;
    @SerializedName("access_token")
    private String accessToken;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
