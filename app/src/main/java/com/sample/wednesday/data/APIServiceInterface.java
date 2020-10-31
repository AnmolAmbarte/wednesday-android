package com.sample.wednesday.data;

import com.sample.wednesday.model.Details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceInterface {

    @GET("users")
    Call<Details> getDetails(@Query("page") int page, @Query("per_page") int per_page);
}
