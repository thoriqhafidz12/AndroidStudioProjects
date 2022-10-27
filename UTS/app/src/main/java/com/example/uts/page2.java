package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        Toast.makeText(getApplicationContext(), "FITUR MENU", Toast.LENGTH_SHORT).show();
    }
    public void barang(View view) {
        Intent intent = new Intent(page2.this, brang.class);
        startActivity(intent);
    }
    public void calcu(View view) {
        Intent intent = new Intent(page2.this, kasir.class);
        startActivity(intent);
    }
    public void det(View view) {
        Intent intent = new Intent(page2.this, detail.class);
        startActivity(intent);
    }
    public void tentang(View view) {
        Intent intent = new Intent(page2.this, tentang.class);
        startActivity(intent);
    }
}