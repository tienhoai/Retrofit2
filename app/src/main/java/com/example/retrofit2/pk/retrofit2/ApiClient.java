package com.example.retrofit2.pk.retrofit2;

public class ApiClient {
//    public static final String baseUrl = "https://android-data.000webhostapp.com/data/vns-demo/";
    public static final String baseUrl = "https://acid8.000webhostapp.com/api/";
//    public static final String baseUrl = "http://192.168.1.33:82/my-pham/";

    public static DataClient apiGetData() {
        return RetrofitClient.retrofitGetData(baseUrl).create(DataClient.class);
    }
}
