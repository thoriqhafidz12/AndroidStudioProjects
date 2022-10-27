package com.example.uts_katon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class bayar extends AppCompatActivity {

    EditText brg,jmlh,hsl;
    TextView tvBrg,tvJmlh,tvHsl;
    Button byr;
    Spinner combo;

    String jnsBrg, jmlhBrg, hslByr;
    double jmlBrg, hrgBrg, total;
    public String cetak [] = {"BANNER - @meter Rp 15.000",
            "UNDANGAN - @buah Rp. 7.500",
            "POSTER A3 - @buah  Rp 3.000",
            "KARTU UCAPAN - @buah Rp 10.000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        combo = (Spinner) findViewById(R.id.pilih);
        ArrayAdapter adapter = new ArrayAdapter(bayar.this,R.layout.support_simple_spinner_dropdown_item,cetak);
        combo.setAdapter(adapter);
        //edittext
        brg = findViewById(R.id.brg);
        jmlh = findViewById(R.id.jmlh);
        hsl = findViewById(R.id.hsl);
        //textview
        tvBrg = findViewById(R.id.tvBrg);
        tvJmlh = findViewById(R.id.tvJmlh);
        tvHsl = findViewById(R.id.tvHsl);
        //button
        byr = findViewById(R.id.byr);

        byr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jnsBrg = brg.getText().toString().trim();
                jmlhBrg = jmlh.getText().toString().trim();
                hslByr = hsl.getText().toString().trim();

                jmlBrg = Double.parseDouble(jmlhBrg);
                hrgBrg = Double.parseDouble(hslByr);
                total = (jmlBrg * hrgBrg);

                tvBrg.setText("Nama Barang  : " + jnsBrg);
                tvJmlh.setText("Jumlah @meter / @buah : " + jmlhBrg);
                tvHsl.setText("Total : Rp. " + total);

            }
        });
    }
}