package com.example.manuel.virtualtattoo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.virtualtattoo.dto.Photo;

import java.util.List;

/**
 * Created by lisab on 25.06.2017.
 */

public class DBRepository {
    private static DBHelper dbHelper;

    public DBRepository(Context context) {
        if (dbHelper == null)
            dbHelper = new DBHelper(context);
    }


    public void InsertPhotoData(String title, List<Photo> data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(PhotoContract.PhotoEntry.TABLE_NAME, null,
                PhotoContract.PhotoEntry.COLUMN_NAME + "=?",
                new String[] { title },
                null, null, null);

        long photoId;
        if (cursor.moveToFirst()) {
            photoId = cursor.getLong(cursor.getColumnIndex(PhotoContract.PhotoEntry._ID));
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(PhotoContract.PhotoEntry.COLUMN_NAME, title);
            photoId = db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);
        }

        for (Photo item : data) {
            ContentValues values = new ContentValues();
            values.put(PhotoContract.PhotoEntry.COLUMN_NAME, item.getTitle());
            db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);
        }

        cursor.close();
    }

    private static final String GET_OLD_DATA_SQL = "SELECT * FROM " + PhotoContract.PhotoEntry.TABLE_NAME +
          " WHERE " + PhotoContract.PhotoEntry.COLUMN_NAME + "=?";

}
