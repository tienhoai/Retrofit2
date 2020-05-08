package com.example.retrofit2.pk.retrofit2;

public class ApiClient {
    private static final String baseUrl = "https://android-data.000webhostapp.com/data/vns-demo/";

    public static DataClient apiGetData() {
        return RetrofitClient.retrofitGetData(baseUrl).create(DataClient.class);
    }
}
