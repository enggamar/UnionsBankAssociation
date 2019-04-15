package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class PdfData {

    @SerializedName("id")
    private String id;
    @SerializedName("document_title")
    private String documentTitle;
    @SerializedName("document_link")
    private String documentLink;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private int status;
    @SerializedName("created_date")
    private String createdDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
