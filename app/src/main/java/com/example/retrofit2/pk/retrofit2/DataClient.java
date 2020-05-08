package com.example.retrofit2.pk.retrofit2;

import com.example.retrofit2.pk.model.ProductData;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface DataClient {

    @GET("get-product-database.php")
    Call<List<ProductData>> getProduct();

    @Multipart
    @PUT("upload-img.php")
    Call<String> uploadImage(@Part MultipartBody.Part photo);
}
