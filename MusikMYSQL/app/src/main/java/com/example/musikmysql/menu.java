package com.example.musikmysql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musikmysql.Activity.MainActivity;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toast.makeText(getApplicationContext(), "FITUR MENU", Toast.LENGTH_SHORT).show();
    }
    public void barang(View view) {
        Intent intent = new Intent(menu.this, MainActivity.class);
        startActivity(intent);
    }
    public void calcu(View view) {
        Intent intent = new Intent(menu.this, kasir.class);
        startActivity(intent);
    }
    public void det(View view) {
        Intent intent = new Intent(menu.this, detail.class);
        startActivity(intent);
    }
    public void tentang(View view) {
        Intent intent = new Intent(menu.this, tentang.class);
        startActivity(intent);
    }
}

