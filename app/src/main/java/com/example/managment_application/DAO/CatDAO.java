package com.example.managment_application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.managment_application.DTO.CatDTO;
import com.example.managment_application.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    public CatDAO(Context Context) {
        dbHelper = new MyDbHelper(Context);
        db = dbHelper.getWritableDatabase();
    }
    public int insert(String objname) {
        ContentValues values = new ContentValues();
        values.put("name", objname);
        return (int) db.insert("tb_cat", null, values);
    }
    public boolean update(int objid, String objname) {
        ContentValues values = new ContentValues();
        values.put("name", objname);
        return db.update("tb_cat", values, "id=?", new String[]{String.valueOf(objid)}) > 0;
    }
    public ArrayList<CatDTO> getList(){
        ArrayList<CatDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, name FROM tb_cat", null);
        // nên Query rõ ràng, ko nên select sao vì sẽ tốn tài nguyên
        if (c != null && c.getCount() > 0){
            c.moveToFirst();
            //duyệt vòng lặp
            // thứ tự cột: id là 0,name là 1
            int id = c.getInt(0);
            String name = c.getString(1);
            CatDTO ogjcat = new CatDTO();
            ogjcat.setId(id);
            ogjcat.setName(name);
            //add vào list
            list.add(ogjcat);

        }else{
            Log.d("AAAAA", "CatDao::getlist: Không lấy đc dữ liệu");
        }
        return list;
    }

    public boolean update(CatDTO objCurrentCat) {
        ContentValues values = new ContentValues();
        values.put("name", objCurrentCat.getName());
        return db.update("tb_cat", values, "id=?", new String[]{String.valueOf(objCurrentCat.getId())}) > 0;
    }
}
