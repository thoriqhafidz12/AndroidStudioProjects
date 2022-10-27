package com.example.musikmysql.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musikmysql.API.APIRequestData;
import com.example.musikmysql.API.RetroServer;
import com.example.musikmysql.Model.ResponseModel;
import com.example.musikmysql.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {


    private int xNo;
    private String xJenis,xMerk,xTipe,xSenar,xHarga;
    private EditText etJenis, etMerk, etTipe,etSenar,etHarga;
    private Button btnUbah;
    private String yJenis,yMerk,yTipe,ySenar,yHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xNo = terima.getIntExtra("xNo",-1);
        xJenis = terima.getStringExtra("xJenis");
        xMerk = terima.getStringExtra("xMerk");
        xTipe = terima.getStringExtra("xTipe");
        xSenar = terima.getStringExtra("xSenar");
        xHarga = terima.getStringExtra("xHarga");

        etJenis = findViewById(R.id.et_jenis);
        etMerk = findViewById(R.id.et_merk);
        etTipe = findViewById(R.id.et_tipe);
        etSenar = findViewById(R.id.et_senar);
        etHarga = findViewById(R.id.et_harga);
        btnUbah = findViewById(R.id.btn_ubah);

        etJenis.setText(xJenis);
        etMerk.setText(xMerk);
        etTipe.setText(xTipe);
        etSenar.setText(xSenar);
        etHarga.setText(xHarga);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yJenis = etJenis.getText().toString();
                yMerk = etMerk.getText().toString();
                yTipe = etTipe.getText().toString();
                ySenar = etSenar.getText().toString();
                yHarga = etHarga.getText().toString();
                updateData();
            }

            private void updateData() {
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> ubahData = ardData.ardUpdateData(xNo,yJenis,yMerk,yTipe,ySenar,yHarga);
                ubahData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(UbahActivity.this, "Kode : " +kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UbahActivity.this, "Gagal Terkoneksi ke Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}