package com.example.latihdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data_lagu.db";
    public static final String TABLE_NAME = "tabel_lagu";
    public static final String COL_1 = "lagu";
    public static final String COL_2 = "artis";
    public static final String COL_3 = "album";
    public static final String COL_4 = "tahun";
    public static final String COL_5 = "label";
    public static final int DATABASE_VERTION = 4;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tabel_lagu (lagu text null, artis text null,album text null, tahun text null, label text null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //Method Tambah Lagu ke Playlist
    public boolean insertData(String lagu, String artis,String album, String tahun, String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,lagu);
        contentValues.put(COL_2,artis);
        contentValues.put(COL_3,album);
        contentValues.put(COL_4,tahun);
        contentValues.put(COL_5,label);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-4){
            return false;
        }
        else {
            return true;
        }
    }

    //Method Edit Data
    public boolean updateData(String lagu, String artis,String album,String tahun,String label){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,lagu);
        contentValues.put(COL_2,artis);
        contentValues.put(COL_3,album);
        contentValues.put(COL_4,tahun);
        contentValues.put(COL_5,label);

        db.update(TABLE_NAME,contentValues,"lagu = ?", new String[] {lagu});
        return true;
    }
    public int deleteData(String lagu){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "lagu = ?", new String[] {lagu});
    }

    //Method Untuk Menampilkan Data
    public Cursor getAllData (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select*from tabel_lagu",null);
        return res;
    }
}
