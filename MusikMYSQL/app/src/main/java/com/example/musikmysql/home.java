package com.example.musikmysql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            }
    public void lanjut(View view) {
            Intent intent = new Intent(home.this, menu.class);
            startActivity(intent);
            }
}
