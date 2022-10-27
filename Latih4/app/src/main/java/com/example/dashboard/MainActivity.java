package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dj(View view) {
        Intent intent = new Intent(MainActivity.this,ListLagu.class);
        startActivity(intent);
    }

    public void rock(View view) {
        Intent intent = new Intent(MainActivity.this,oldrock.class);
        startActivity(intent);
    }
}