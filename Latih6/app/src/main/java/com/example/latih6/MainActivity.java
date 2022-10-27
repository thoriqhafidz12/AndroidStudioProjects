package com.example.latih6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    classPlaylist cp;
    ListView ListView;
    EditText editText;
    Button tmblTambah;

    ArrayAdapter adapter;
    ArrayList <String> ListViewku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView=findViewById(R.id.playlist);
        editText=findViewById(R.id.isiplaylist);
        tmblTambah=findViewById(R.id.tomboltambah);
        tampilkan_playlist();
        ListViewku = new ArrayList<>();
        tmblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp.tambahData(editText.getText().toString());
                Toast.makeText(MainActivity.this, "Lagu Ditambahkan", Toast.LENGTH_SHORT).show();
                tampilkan_playlist();
            }
        });
    }

    private void tampilkan_playlist() {
        Cursor cursor = cp.tampilkanplaylist();
        if (cursor.getCount()==0) {
            Toast.makeText(MainActivity.this, "Lagu Kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                ListViewku.add(String.valueOf(cursor.getInt(0))+" "+cursor.getString(1));
            }
            adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,ListViewku);
            ListView.setAdapter(adapter);
        }
    }

}