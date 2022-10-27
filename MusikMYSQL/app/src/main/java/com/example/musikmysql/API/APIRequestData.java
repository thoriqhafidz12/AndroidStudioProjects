package com.example.musikmysql.API;

import com.example.musikmysql.Model.ResponseModel;

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
            @Field("jenis") String jenis,
            @Field("merk") String merk,
            @Field("tipe") String tipe,
            @Field("senar") String senar,
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
            @Field("jenis") String jenis,
            @Field("merk") String merk,
            @Field("tipe") String tipe,
            @Field("senar") String senar,
            @Field("harga") String harga
    );
}
