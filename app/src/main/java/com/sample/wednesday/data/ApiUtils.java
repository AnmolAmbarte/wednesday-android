package com.sample.wednesday.data;


public class ApiUtils {

    private ApiUtils() {
    }

    private static final String BASE_URL = "https://itunes.apple.com/";

    public static APIServiceInterface getAPIService() {

        return RetrofitService.getClient(BASE_URL).create(APIServiceInterface.class);
    }
}
