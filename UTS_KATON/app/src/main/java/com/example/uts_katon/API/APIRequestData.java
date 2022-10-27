package com.example.uts_katon.API;

import com.example.uts_katon.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("crate.php")
    Call<ResponseModel> ardCrateData(
            @Field("nama") String nama,
            @Field("jenis") String jenis,
            @Field("ukuran") String ukuran,
            @Field("harga") String harga
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("no") int no
    );
    @FormUrlEncoded
    @POST("get.php")
    Call<ResponseModel> ardGetData(
            @Field("no") int no
    );
    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("no")int no,
            @Field("nama") String nama,
            @Field("jenis") String jenis,
            @Field("ukuran") String ukuran,
            @Field("harga") String harga
    );
}
