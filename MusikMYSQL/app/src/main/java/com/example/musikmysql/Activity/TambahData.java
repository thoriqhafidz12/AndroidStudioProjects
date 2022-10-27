package com.example.musikmysql.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahData extends AppCompatActivity {

    private EditText etJenis, etMerk, etTipe,etSenar,etHarga;
    private Button btnSimpan;
    private String jenis, merk, tipe,senar,harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etJenis = findViewById(R.id.et_jenis);
        etMerk = findViewById(R.id.et_merk);
        etTipe = findViewById(R.id.et_tipe);
        etSenar = findViewById(R.id.et_senar);
        etHarga = findViewById(R.id.et_harga);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jenis = etJenis.getText().toString();
                merk = etMerk.getText().toString();
                tipe = etTipe.getText().toString();
                senar = etSenar.getText().toString();
                harga = etHarga.getText().toString();

                if(jenis.trim().equals("")){
                    etJenis.setError("Jenis Harus Diisi");
                }
                else if(merk.trim().equals("")){
                    etMerk.setError("Merk Harus Diisi");
                }
                else if(tipe.trim().equals("")){
                    etTipe.setError("Tipe Gitar Harus Diisi");
                }
                else if(senar.trim().equals("")){
                    etSenar.setError("Senar Harus Diisi");
                }
                else if(harga.trim().equals("")){
                    etHarga.setError("Harga Harus Diisi");
                }
                else{
                    createData();
                }
            }
            public void createData(){
                APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> simpanData = ardData.ardCrateData(jenis, merk, tipe, senar, harga);
                simpanData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(TambahData.this, "Kode : " +kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(TambahData.this, "Gagal Terkoneksi ke Server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}