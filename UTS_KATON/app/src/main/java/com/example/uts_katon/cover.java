package com.example.uts_katon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uts_katon.Activity.market;

public class cover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
    }

    public void catalog(View view) {
        Intent intent = new Intent(cover.this, produk.class);
        startActivity(intent);
    }
    public void jen(View view) {
        Intent intent = new Intent(cover.this, market.class);
        startActivity(intent);
    }
    public void kasir(View view) {
        Intent intent = new Intent(cover.this, bayar.class);
        startActivity(intent);
    }
    public void tentang(View view) {
        Intent intent = new Intent(cover.this, about.class);
        startActivity(intent);
    }
}