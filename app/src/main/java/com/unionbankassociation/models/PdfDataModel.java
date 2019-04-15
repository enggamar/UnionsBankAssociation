package com.unionbankassociation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PdfDataModel {

    @SerializedName("result")
    private ArrayList<PdfData> pdfData;
    @SerializedName("total")
    private int total;
    @SerializedName("next_page")
    private int next_page;

    public ArrayList<PdfData> getPdfData() {
        return pdfData;
    }

    public void setPdfData(ArrayList<PdfData> pdfData) {
        this.pdfData = pdfData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNext_page() {
        return next_page;
    }

    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }
}
