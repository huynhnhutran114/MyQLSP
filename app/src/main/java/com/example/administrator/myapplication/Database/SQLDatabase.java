package com.example.administrator.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.administrator.myapplication.KindProductPK.KindProduct;
import com.example.administrator.myapplication.ProductPK.Product;

import java.util.ArrayList;

public class SQLDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "QLSanPham.sqlite";
    public static final String TABLE_KINDPRODUCT = "KINDPRODUCT";
    public static final String STT = "STT";
    public static final String CODEKIND = "CODEKIND";
    public static final String NAMEKIND = "NAMEKIND";
    public static final String CREATE_KINDPRODUCT = "create table if not exists KINDPRODUCT(STT INTEGER KEY AUTOINCREMENT, CODEKIND TEXT NOT NULL, NAMEKIND TEXT NOT NULL)";
    public static final String TABLE_PRODUCT = "PRODUCT";
    public static final String ID = "ID";
    public static final String CODEPRODUCT = "CODEPRODUCT";
    public static final String NAMEPRODUCT = "NAMEPRODUCT";
    public static final String PRICEPRODUCT = "PRICEPRODUCT";
    public static final String STTKIND = "STTKIND";

    public SQLDatabase(Context context) {
        super(context, "QLSanPham.sqlite", (SQLiteDatabase.CursorFactory)null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.queryData("DROP TABLE IF EXISTS KINDPRODUCT");
        this.onCreate(db);
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> kindProduct = new ArrayList();
        SQLiteDatabase Database = this.getReadableDatabase();
        Cursor data = this.getData("SELECT * FROM KINDPRODUCT");

        while(data.moveToNext()) {
            int stt = data.getInt(0);
            String codeKind = data.getString(1);
            String nameKind = data.getString(2);
            kindProduct.add(stt + " " + codeKind + " " + nameKind);
        }

        return kindProduct;
    }

    public void queryData(String sql) {
        SQLiteDatabase Database = this.getWritableDatabase();
        Database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase Database = this.getReadableDatabase();
        return Database.rawQuery(sql, (String[])null);
    }

    public boolean InsertKind(String CodeKind, String NameKind) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CODEKIND", CodeKind);
        cv.put("NAMEKIND", NameKind);
        long result = SQ.insert("KINDPRODUCT", (String)null, cv);
        return result != -1L;
    }

    public boolean updateKind(KindProduct KP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CODEKIND", KP.getCodeKind());
        cv.put("NAMEKIND", KP.getNameKind());
        long result = (long)db.update("KINDPRODUCT", cv, "STT=?", new String[]{String.valueOf(KP.getStt())});
        db.close();
        return result != -1L;
    }

    public void Delete(int stt) {
        SQLiteDatabase data = this.getWritableDatabase();
        long result = (long)data.delete("KINDPRODUCT", "STT=?", new String[]{String.valueOf(stt)});
    }

    public boolean InsertProduct(Product p) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CODEPRODUCT", p.getCodeProduct());
        cv.put("NAMEPRODUCT", p.getNameProduct());
        cv.put("PRICEPRODUCT", p.getPriceProduct());
        cv.put("STTKIND", p.getKindProduct());
        long result = SQ.insert("PRODUCT", (String)null, cv);
        SQ.close();
        return result != -1L;
    }

    public boolean updateProduct(Product p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CODEPRODUCT", p.getCodeProduct());
        cv.put("NAMEPRODUCT", p.getNameProduct());
        cv.put("PRICEPRODUCT", p.getPriceProduct());
        cv.put("STTKIND", p.getKindProduct());
        long result = (long)db.update("PRODUCT", cv, "ID=?", new String[]{String.valueOf(p.getID())});
        db.close();
        return result != -1L;
    }
}
