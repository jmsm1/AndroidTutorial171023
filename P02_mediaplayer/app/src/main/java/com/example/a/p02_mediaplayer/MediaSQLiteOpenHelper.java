package com.example.a.p02_mediaplayer;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2017-10-25.
 */

public class MediaSQLiteOpenHelper extends SQLiteOpenHelper {

    public MediaSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MediaSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //처음설치되고 처음 실행될때 (한번만 실행된다) 테이블 생성시 사용
        @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블생성 쿼리
            String sql = "CREATE TABLE mp (id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "path TEXT , fileName TEXT , position INT)";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
