package com.example.latihdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etLagu,etArtis,etAlbum,etTahun,etLabel,et;
    Button btnSave,btnShow,btnDelete,btnUpdate;

    DataBaseHelper dbPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbPlaylist = new DataBaseHelper(this);
        etLagu = (EditText) findViewById(R.id.etLagu);
        etArtis = (EditText) findViewById(R.id.etArtis);
        etAlbum = (EditText) findViewById(R.id.etAlbum);
        etTahun = (EditText) findViewById(R.id.etTahun);
        etLabel = (EditText) findViewById(R.id.etLabel);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        viewAll ();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean IsInserted = dbPlaylist.insertData(etLagu.getText().toString(),etArtis.getText().toString(),etAlbum.getText().toString(),etTahun.getText().toString(),etLabel.getText().toString());

                if (IsInserted == true){
                    Toast.makeText(MainActivity.this, "Lagu Disimpan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean updateData = dbPlaylist.updateData(etLagu.getText().toString(),etArtis.getText().toString(),etAlbum.getText().toString(),etTahun.getText().toString(),etLabel.getText().toString());

            if (updateData == false){
                Toast.makeText(MainActivity.this, "Data Belum Ada", Toast.LENGTH_SHORT).show();

            }
            else {
                StringBuffer buffer = new StringBuffer();
                buffer.append("Lagu        : " +etLagu.getText().toString()+ "\n");
                buffer.append("Artis        : " +etArtis.getText().toString()+ "\n");
                buffer.append("Album        : " +etAlbum.getText().toString()+ "\n");
                buffer.append("Tahun        : " +etTahun.getText().toString()+ "\n");
                buffer.append("Label        : " +etLabel.getText().toString()+ "\n");

                showMessage("Update Profile Lagu : ", buffer.toString());
            }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DltLagu = dbPlaylist.deleteData(etLagu.getText().toString());

                if(DltLagu == 1){
                    Toast.makeText(MainActivity.this, "Lagu dihapus", Toast.LENGTH_SHORT).show();
                    etLagu.setText("");
                    etArtis.setText("");
                    etAlbum.setText("");
                    etTahun.setText("");
                    etLabel.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewAll() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbPlaylist.getAllData();

                if (res.getCount()==0){
                    showMessage("Eror","Tidak Ditemukan");
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Lagu        : "+res.getString(0)+ "\n");
                        buffer.append("Artis        : "+res.getString(1)+ "\n");
                        buffer.append("Album        : "+res.getString(2)+ "\n");
                        buffer.append("Tahun        : "+res.getString(3)+ "\n");
                        buffer.append("Label        : "+res.getString(4)+ "\n"+"\n");

                    }
                    showMessage("Playlist lagu: ", buffer.toString());
                }
            }
        });
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}