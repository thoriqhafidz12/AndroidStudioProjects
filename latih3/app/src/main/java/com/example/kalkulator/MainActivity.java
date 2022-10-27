package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ang1, ang2;
    TextView hasilnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ang1 =(EditText) findViewById(R.id.angka1);
        ang2 =(EditText) findViewById(R.id.angka2);
        hasilnya =(TextView) findViewById(R.id.hasil);
    }

    public void penjumlahan(View view) {
        int a1=Integer.parseInt(ang1.getText().toString());
        int a2=Integer.parseInt(ang2.getText().toString());
        Integer hsl= a1+a2;

        hasilnya.setText(hsl.toString());
    }

    public void pengurangan(View view) {
        int a1=Integer.parseInt(ang1.getText().toString());
        int a2=Integer.parseInt(ang2.getText().toString());
        Integer hsl= a1-a2;

        hasilnya.setText(hsl.toString());
    }

    public void perkalian(View view) {int a1=Integer.parseInt(ang1.getText().toString());
        int a2=Integer.parseInt(ang2.getText().toString());
        Integer hsl= a1*a2;

        hasilnya.setText(hsl.toString());
    }

    public void pembagian(View view) {
        int a1=Integer.parseInt(ang1.getText().toString());
        int a2=Integer.parseInt(ang2.getText().toString());
        Integer hsl= a1/a2;

        hasilnya.setText(hsl.toString());
    }
}