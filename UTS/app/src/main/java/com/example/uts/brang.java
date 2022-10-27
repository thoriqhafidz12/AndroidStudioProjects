package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class brang extends AppCompatActivity {
    ListView listView;

    public String outfit [] = {"Hoodie", "Sepatu", "Tas","Crewneck","Beanie Hat","Topi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brang);
        Toast.makeText(getApplicationContext(), "BARANG", Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.stok2);

        ArrayAdapter adapter = new ArrayAdapter(brang.this,R.layout.support_simple_spinner_dropdown_item,outfit);
        listView.setAdapter(adapter);

    }

}