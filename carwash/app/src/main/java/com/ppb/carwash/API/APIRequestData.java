package com.ppb.carwash.API;

import com.ppb.carwash.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET ("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

}
