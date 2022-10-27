package com.example.uts_katon.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahData extends AppCompatActivity {

    private EditText etNama, etJenis, etUkuran,etHarga;
    private Button btnSimpan;
    private String nama, jenis, ukuran, harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etNama = findViewById(R.id.et_nama);
        etJenis = findViewById(R.id.et_jenis);
        etUkuran = findViewById(R.id.et_ukuran);
        etHarga = findViewById(R.id.et_harga);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = etNama.getText().toString();
                jenis = etJenis.getText().toString();
                ukuran = etUkuran.getText().toString();
                harga = etHarga.getText().toString();


                if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }
                else if(jenis.trim().equals("")){
                    etJenis.setError("Jenis Harus Diisi");
                }
                else if(ukuran.trim().equals("")){
                    etUkuran.setError("Ukuran Harus Diisi");
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
                Call<ResponseModel> simpanData = ardData.ardCrateData(nama, jenis, ukuran, harga);
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