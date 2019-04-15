package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

public class PdfModel extends BaseResponseBean {
    @SerializedName("RESULT")
    private PdfDataModel pdfDataModel;

    public PdfDataModel getPdfDataModel() {
        return pdfDataModel;
    }

    public void setPdfDataModel(PdfDataModel pdfDataModel) {
        this.pdfDataModel = pdfDataModel;
    }
}
