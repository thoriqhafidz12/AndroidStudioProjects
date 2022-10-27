package com.example.musikmysql;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tentang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        Toast.makeText(getApplicationContext(), "ABOUT US", Toast.LENGTH_SHORT).show();
    }
}
