package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class ContactUsBean extends BaseResponseBean {
    @SerializedName("RESULT")
    private ContactUsData contactUsData;

    public ContactUsData getContactUsData() {
        return contactUsData;
    }

    public void setContactUsData(ContactUsData contactUsData) {
        this.contactUsData = contactUsData;
    }
}
