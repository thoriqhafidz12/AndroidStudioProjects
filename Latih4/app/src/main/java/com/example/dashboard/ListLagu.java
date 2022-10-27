package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ListLagu extends AppCompatActivity {
    ListView Listview;
    Spinner combo;

    public String elektromix [] = {"Levels - Avicii", "Animals - Martin Garrix", "Feel So Close - Calvin Harris", "Get Lucky - Daft Punk", "Fire Stone - Kygo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lagu);

        Listview = (ListView) findViewById(R.id.ListLagu);
        combo = (Spinner) findViewById(R.id.comboLagu);

        ArrayAdapter adapter = new ArrayAdapter(ListLagu.this,R.layout.support_simple_spinner_dropdown_item,elektromix);
        Listview.setAdapter(adapter);
        combo.setAdapter(adapter);
    }
}