package com.example.manuel.virtualtattoo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lisab on 25.06.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "photo.db";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_PHOTOS = "CREATE TABLE " + PhotoContract.PhotoEntry.TABLE_NAME +
            " (" + PhotoContract.PhotoEntry._ID + " INTEGER PRIMARY KEY," +
            PhotoContract.PhotoEntry.COLUMN_NAME + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_PHOTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
