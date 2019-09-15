package com.destiny.pos.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.destiny.pos.Model.Model;

import java.util.LinkedList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "session.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_TIPE = "tipe";
    public static final String COLUMN_KATEGORI = "kategori";
    public static final String COLUMN_JUMLAH = "jumlah";
    public static final String COLUMN_JENIS = "jenis";
    public static final String COLUMN_KETERANGAN = "keterangan";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (" +
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_DESKRIPSI+" TEXT NOT NULL, "+
                COLUMN_TANGGAL+" TEXT NOT NULL,"+
                COLUMN_TIPE+" TEXT NOT NULL,"+
                COLUMN_KATEGORI+" TEXT NOT NULL,"+
                COLUMN_JUMLAH+" INTEGER NOT NULL,"+
                COLUMN_JENIS+" TEXT NOT NULL,"+
                COLUMN_KETERANGAN+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    public List<Model> ListDeskripsii(String deksripsi) {
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_DESKRIPSI+" = '"+deksripsi+"'";

        List<Model> LinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Model model;

        if (cursor.moveToFirst()) {
            do {
                model = new Model();
                model.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                model.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESKRIPSI)));
                model.setTanggal(cursor.getString(cursor.getColumnIndex(COLUMN_TANGGAL)));
                model.setTipe(cursor.getString(cursor.getColumnIndex(COLUMN_TIPE)));
                model.setKategori(cursor.getString(cursor.getColumnIndex(COLUMN_KATEGORI)));
                model.setJumlah(cursor.getString(cursor.getColumnIndex(COLUMN_JUMLAH)));
                model.setJenis(cursor.getString(cursor.getColumnIndex(COLUMN_JENIS)));
                model.setKeterangan(cursor.getString(cursor.getColumnIndex(COLUMN_KETERANGAN)));
                LinkedList.add(model);
            } while (cursor.moveToNext());
        }
        return LinkedList;
    }
    public List<Model> List(String deksripsi) {
        String query = "SELECT  * FROM " + TABLE_NAME;

        List<Model> LinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Model model;

        if (cursor.moveToFirst()) {
            do {
                model = new Model();
                model.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                model.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESKRIPSI)));
                model.setTanggal(cursor.getString(cursor.getColumnIndex(COLUMN_TANGGAL)));
                model.setTipe(cursor.getString(cursor.getColumnIndex(COLUMN_TIPE)));
                model.setKategori(cursor.getString(cursor.getColumnIndex(COLUMN_KATEGORI)));
                model.setJumlah(cursor.getString(cursor.getColumnIndex(COLUMN_JUMLAH)));
                model.setJenis(cursor.getString(cursor.getColumnIndex(COLUMN_JENIS)));
                model.setKeterangan(cursor.getString(cursor.getColumnIndex(COLUMN_KETERANGAN)));
                LinkedList.add(model);
            } while (cursor.moveToNext());
        }
        return LinkedList;
    }
    public void InsertData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESKRIPSI, model.getDeskripsi());
        values.put(COLUMN_TANGGAL, model.getTanggal());
        values.put(COLUMN_TIPE, model.getTipe());
        values.put(COLUMN_KATEGORI, model.getKategori());
        values.put(COLUMN_JUMLAH, model.getJumlah());
        values.put(COLUMN_JENIS, model.getJenis());
        values.put(COLUMN_KETERANGAN,model.getKeterangan());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }
    public void deleteData(String id,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE id= '"+id);
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }
    public void updateData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESKRIPSI, model.getDeskripsi());
        values.put(COLUMN_TANGGAL, model.getTanggal());
        values.put(COLUMN_TIPE, model.getTipe());
        values.put(COLUMN_KATEGORI, model.getKategori());
        values.put(COLUMN_JUMLAH, model.getJumlah());
        values.put(COLUMN_JENIS, model.getJenis());
        values.put(COLUMN_KETERANGAN,model.getKeterangan());

        // insert
        db.update(TABLE_NAME,values,COLUMN_ID+"="+model.getId(),null);
        db.close();
    }
    public Cursor checkData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+" WHERE id = '"+id+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor Checker(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor Hitung(String tipe,String deskripsi){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_TIPE+" = '"+tipe+"' AND "+COLUMN_DESKRIPSI+" = '"+deskripsi+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor hitung(String deskripsi){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT SUM("+COLUMN_JUMLAH+") AS total FROM "+TABLE_NAME+" WHERE "+COLUMN_TIPE+" = '"+deskripsi+"' ";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}

