package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class kasir extends AppCompatActivity {
    EditText ETnama, ETjmlhbrg, ETharga, ETbayar;
    TextView TVnamabrg, TVjumlah, TVharga, TVbayar, TVtotal, TVkembali, TVket,tgl,angkakounter;
    Button btnProses,btnHapus;
    int angka = 0,hr, bln, thn;
    Spinner combo;

    String namabrg, jumlahbrg, hargabrg, uangbyr;
    double jmlBarang, hrgBarang, uangByr, total, kembali;
    public String outfit [] = {
            "Pili Outfit Kamu",
            "HOODIE - @buah Rp 140.000",
            "CREWNECK - @buah Rp. 100.000",
            "TAS - @buah  Rp 60.000",
            "BEANIE HAT - @buah Rp 50.000",
            "SEPATU - @buah Rp 300.000",
            "TOPI - @buah Rp 50.000"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);
        Toast.makeText(getApplicationContext(), "KASIR", Toast.LENGTH_SHORT).show();
        combo = (Spinner) findViewById(R.id.pilih);
        ArrayAdapter adapter = new ArrayAdapter(kasir.this,R.layout.support_simple_spinner_dropdown_item,outfit);
        combo.setAdapter(adapter);
        angkakounter = findViewById(R.id.kounter);
        //Tanggal
        Calendar calendar = Calendar.getInstance();
        hr = calendar.get(Calendar.DAY_OF_MONTH);
        bln = calendar.get(Calendar.MONTH);
        thn = calendar.get(Calendar.YEAR);

        String finalTanggal = hr + "/" + bln + "/" + thn;

        tgl = (TextView) findViewById(R.id.tanggal);
        tgl.setText(finalTanggal);
        //EditText

        ETnama = findViewById(R.id.etNamaBarang);
        ETjmlhbrg = findViewById(R.id.etJmlBarang);
        ETharga = findViewById(R.id.etHarga);
        ETbayar = findViewById(R.id.etJmlUang);

        //TextView
        TVnamabrg = findViewById(R.id.tvNamaBarang);
        TVjumlah = findViewById(R.id.tvJmlBarang);
        TVharga = findViewById(R.id.tvHarga);
        TVbayar = findViewById(R.id.tvUangBayar);
        TVtotal = findViewById(R.id.tvTotal);
        TVkembali = findViewById(R.id.tvKembalian);

        TVket = findViewById(R.id.tvKeterangan);

        //Button
        btnProses = findViewById(R.id.btnProses);
        btnHapus = findViewById(R.id.btnHapus);
        //Proses
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namabrg = ETnama.getText().toString().trim();
                jumlahbrg = ETjmlhbrg.getText().toString().trim();
                hargabrg = ETharga.getText().toString().trim();
                uangbyr = ETbayar.getText().toString().trim();

                jmlBarang = Double.parseDouble(jumlahbrg);
                hrgBarang = Double.parseDouble(hargabrg);
                uangByr = Double.parseDouble(uangbyr);
                total = (jmlBarang * hrgBarang);

                TVnamabrg.setText("Nama Barang : " + namabrg);
                TVjumlah.setText("Jumlah Barang : " + jumlahbrg);
                TVharga.setText("Harga Barang : " + hargabrg);
                TVbayar.setText("Uang bayar : " + uangbyr);

                kembali = (uangByr - total);
                if (uangByr < total) {
                    TVket.setText("Keterangan : Uang bayar kurang Rp. " + (-kembali));
                } else if(uangByr > total) {
                    TVkembali.setText("Uang Kembalian : Rp. " + kembali);
                }
                else {
                    TVket.setText("Uang Pas");
                }
                angka = angka + 1;
                angkakounter.setText(Integer.toString(angka));
                Toast.makeText(getApplicationContext(), "CETAK NOTA", Toast.LENGTH_SHORT).show();
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TVnamabrg.setText("");
                TVjumlah.setText("");
                TVharga.setText("");
                TVbayar.setText("");
                TVkembali.setText("");
                TVket.setText("");
                TVtotal.setText("");
                Toast.makeText(getApplicationContext(), "DATA DIHAPUS", Toast.LENGTH_SHORT).show();
            }
        });
    }
}