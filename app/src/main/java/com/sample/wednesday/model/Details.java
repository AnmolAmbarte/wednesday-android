package com.sample.wednesday.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Details {


    @Expose
    @SerializedName("ad")
    private Ad ad;
    @Expose
    @SerializedName("data")
    private List<Data> data;
    @Expose
    @SerializedName("total_pages")
    private int total_pages;
    @Expose
    @SerializedName("total")
    private int total;

    public Ad getAd() {
        return ad;
    }

    public List<Data> getData() {
        return data;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal() {
        return total;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getPage() {
        return page;
    }

    @Expose
    @SerializedName("per_page")
    private int per_page;
    @Expose
    @SerializedName("page")
    private int page;
}
