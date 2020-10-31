package com.sample.wednesday.data;


import okhttp3.OkHttpClient;

public class ApiUtils {

    private ApiUtils() {
    }

    private static final String BASE_URL = "https://reqres.in/api/";

    public static APIServiceInterface getAPIService(OkHttpClient okHttpClient) {

        return RetrofitService.getClient(BASE_URL,okHttpClient).create(APIServiceInterface.class);
    }
}
