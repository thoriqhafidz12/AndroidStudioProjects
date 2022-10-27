package com.ppb.carwash.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ppb.carwash.API.APIRequestData;
import com.ppb.carwash.API.RetroServer;
import com.ppb.carwash.Adapter.AdapterData;
import com.ppb.carwash.Model.DataModel;
import com.ppb.carwash.Model.ResponseModel;
import com.ppb.carwash.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;

    private List<DataModel> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        retrieveData();

    }

    public void retrieveData()
    {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();
        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(MainActivity.this, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterData(MainActivity.this,listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Terhubung ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}