package com.example.musikmysql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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
    Button btnProses,btnHapus,btnSave,btnShow,btnDelete;
    int angka = 0,hr, bln, thn;
    Spinner combo;

    DataBaseHelper dbRiwayat;

    String namabrg, jumlahbrg, hargabrg, uangbyr;
    double jmlBarang, hrgBarang, uangByr, total, kembali;
    public String outfit [] = {
            "Pili Gitarmu",
            "GIBSON MX II STRING - Rp 12.000.000",
            " FENDER TELECASTER LIGHT STRING - Rp.10.00.000",
            " YAMAHA L SERIES NILON - Rp 6.000.000",
            " SCHETER KLI NILON - Rp 5.000.000",
            " TAYLOR HJP STRING - Rp 3.000.000",
            " GIBSON SC STRING - Rp 5.000.000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);
        Toast.makeText(getApplicationContext(), "KASIR", Toast.LENGTH_SHORT).show();
        combo = (Spinner) findViewById(R.id.pilih);
        ArrayAdapter adapter = new ArrayAdapter(kasir.this,R.layout.support_simple_spinner_dropdown_item,outfit);
        combo.setAdapter(adapter);
        angkakounter = findViewById(R.id.kounter);

        dbRiwayat = new DataBaseHelper(this);

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

        ETnama = (EditText) findViewById(R.id.etNamaBarang);
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
        btnSave =  findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnDelete =  findViewById(R.id.btnDelete);

        viewAll ();
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
                TVtotal.setText("Total : "+total);

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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean IsInserted = dbRiwayat.insertData(ETnama.getText().toString(),ETjmlhbrg.getText().toString(),ETharga.getText().toString(),ETbayar.getText().toString(),TVtotal.getText().toString());

                if (IsInserted == true){
                    Toast.makeText(kasir.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(kasir.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DltLagu = dbRiwayat.deleteData(ETnama.getText().toString());

                if(DltLagu == 1){
                    Toast.makeText(kasir.this, "Riwayat Dihapus", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(kasir.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewAll() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbRiwayat.getAllData();

                if (res.getCount()==0){
                    showMessage("Eror","Tidak Ditemukan");
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("NAMA : "+res.getString(0)+ "\n");
                        buffer.append("JUMLAH : "+res.getString(1)+ "\n");
                        buffer.append("HARGA : "+res.getString(2)+ "\n");
                        buffer.append("BAYAR : "+res.getString(3)+ "\n");
                        buffer.append(""+res.getString(4)+ "\n"+ "\n");

                    }
                    showMessage("RIWAYAT PEMBELIAN ", buffer.toString());
                }
            }
        });
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

