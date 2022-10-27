package com.example.uts_katon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uts_katon.API.APIRequestData;
import com.example.uts_katon.API.RetroServer;
import com.example.uts_katon.Model.ResponseModel;
import com.example.uts_katon.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahData extends AppCompatActivity {


    private int xNo;
    private String xNama, xJenis, xUkuran,xHarga;
    private EditText etNama, etJenis, etUkuran, etHarga;
    private Button btnUbah;
    private String yNama, yJenis, yUkuran,yHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data);

        Intent terima = getIntent();
        xNo = terima.getIntExtra("xNo",-1);
        xNama = terima.getStringExtra("xNama");
        xJenis = terima.getStringExtra("xJenis");
        xUkuran = terima.getStringExtra("xUkuran");
        xHarga = terima.getStringExtra("xHarga");

        etNama = findViewById(R.id.et_nama);
        etJenis = findViewById(R.id.et_jenis);
        etUkuran = findViewById(R.id.et_ukuran);
        etHarga = findViewById(R.id.et_harga);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(xNama);
        etJenis.setText(xJenis);
        etUkuran.setText(xUkuran);
        etHarga.setText(xHarga);
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNama = etNama.getText().toString();
                yJenis = etJenis.getText().toString();
                yUkuran = etUkuran.getText().toString();
                yHarga = etHarga.getText().toString();
                updateData();
            }

            private void updateData() {
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> ubahData = ardData.ardUpdateData(xNo, yNama, yJenis, yUkuran,yHarga);
                ubahData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(UbahData.this, "Kode : " +kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UbahData.this, "Gagal Terkoneksi ke Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}