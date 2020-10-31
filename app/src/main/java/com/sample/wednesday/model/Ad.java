package com.sample.wednesday.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad {
    @Expose
    @SerializedName("text")
    private String text;
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("company")
    private String company;
}
