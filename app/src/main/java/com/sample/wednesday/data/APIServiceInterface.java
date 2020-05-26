package com.sample.wednesday.data;

import com.sample.wednesday.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceInterface {

    @GET("search")
    Call<Example> getNewsList(@Query("term")String term);

}
