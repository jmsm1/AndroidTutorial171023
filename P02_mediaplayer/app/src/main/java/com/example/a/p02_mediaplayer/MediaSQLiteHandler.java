package com.example.a.p02_mediaplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a on 2017-10-25.
 */

public class MediaSQLiteHandler {
    MediaSQLiteOpenHelper helper;

    public MediaSQLiteHandler(Context context){
        helper = new MediaSQLiteOpenHelper(context,"mp",null,1);
    }

    public void insert(String path , String fileName , int position) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("path" , path);
        values.put("fileName" , fileName);
        values.put("position" , position);
        db.insert("mp" , null , values);
    }
    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("mp", "fileName = ?" , new String[]{name});
    }

    public void update(String path , String fileName , int position){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fileName" , fileName);
        values.put("position" , position);
        db.update("mp", values , "fileName = ?" , new String[]{fileName});
    }

    public int selectPosition(String path , String fileName , int position){
        int curPosition  = 0;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("mp", null, "fileName = ?", new String[]{fileName} , null,null,"position desc", "1");
        if(c.moveToFirst()){
            curPosition = c.getInt(c.getColumnIndex("position"));
        }else{
            insert( path , fileName , position);
        }

        return curPosition;
    }

    public String selectAll(){
        String res = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("mp",null,null,null,null,null,null);
        while(c.moveToNext()){
            String path = c.getString(c.getColumnIndex("path"));
            String fileName = c.getString(c.getColumnIndex("fileName"));
            int position = c.getInt(c.getColumnIndex("position"));
            res += "path : "+ path + " fileName :" + fileName + " position :"+ position;
            res += "\n";
        }
        return res;
    }

}