package com.doozycod.getmaster.Service;

import retrofit2.Retrofit;

public class ApiUtils {
    //      api service for webservices
    private ApiUtils() {}
    public static final String BASE_URL = "http://etitbit.com/figma/";

    public static ApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
