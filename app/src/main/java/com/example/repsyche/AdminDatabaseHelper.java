package com.example.repsyche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AdminDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "AdminDatabaseHelper";

    private static final String TABLE_NAME = "admin_table";
    private static final String COL1 = "email";
    private static final String COL2 = "password";


    public AdminDatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " TEXT PRIMARY KEY, "+
                COL2 + " TEXT NOT NULL)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addAdmin(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, email);
        contentValues.put(COL2, password);

        Log.d(TAG, "add Data: Adding" + email+", " + password + "to " + TABLE_NAME);

        long result;

        try{
            result = db.insert(TABLE_NAME, null, contentValues);
        } catch (SQLException e){
            return false;
        }

        //if data inserted incorrectly it will return -1
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db =   this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
