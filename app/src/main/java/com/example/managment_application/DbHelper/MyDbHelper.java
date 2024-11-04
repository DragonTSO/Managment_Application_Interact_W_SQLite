package com.example.managment_application.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context) {
        super(context, "QLBH.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCaterogy ="CREATE TABLE tb_cat (id INTEGER PRIMARY KEY UNIQUE NOT NULL, name TEXT NOT NULL);";
        sqLiteDatabase.execSQL(sqlCaterogy);
        String sqlProduct = "CREATE TABLE tb_product (id INTEGER PRIMARY KEY UNIQUE, name TEXT NOT NULL, price REAL NOT NULL, id_cat REFERENCES tb_cat(id));";
        sqLiteDatabase.execSQL(sqlProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i < i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_cat");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_product");
            onCreate(sqLiteDatabase);
        }
    }
}
