package com.example.latih6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class classPlaylist extends SQLiteOpenHelper {
    public static final String db_playlist = "db_playlist";
    public static final String KODE = "kode";
    private static final String judul_lagu = "judul_lagu";
    private static final String tabel_playlist = "tabel_playlist";

    public classPlaylist(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_playlist, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String nama_tabel = "create table" + tabel_playlist + "(" + KODE + " integer primary key auto increment, " + judul_lagu + " text";
        sqLiteDatabase.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean tambahData(String nama)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put(judul_lagu, nama);
        Long hasil = db.insert(tabel_playlist,null,ContentValues);
        return hasil != -1;
    }


    public Cursor tampilkanplaylist() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + tabel_playlist;
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }
}
