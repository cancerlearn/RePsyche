package com.example.repsyche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Arrays;

import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "UserDatabaseHelper";

    private static final String TABLE_NAME = "users_table";
    private static final String COL1 = "username";
    private static final String COL2 = "email";
    private static final String COL3 = "first_name";
    private static final String COL4 = "last_name";
    private static final String COL5 = "password";
    private static final String COL6 = "credit";

    public UserDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("+
                COL1 + " TEXT PRIMARY KEY, " +
                COL2 + " TEXT NOT NULL, "+
                COL3 + " TEXT NOT NULL, "+
                COL4 + " TEXT NOT NULL, "+
                COL5 + " TEXT NOT NULL, "+
                COL6 + " REAL NOT NULL)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser(String user_detail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] details = user_detail.split(" | ");
        contentValues.put(COL1, details[0]);
        contentValues.put(COL2, details[2]);
        contentValues.put(COL3, details[4]);
        contentValues.put(COL4, details[6]);
        contentValues.put(COL5, details[8]);
        contentValues.put(COL6, Integer.parseInt(details[10]));

        Log.d(TAG, "addData: Adding " + Arrays.toString(details) + " to " + TABLE_NAME);

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

    public boolean addUserCredit(String user_email, double extra_credit){

        SQLiteDatabase db = this.getWritableDatabase();

        try{

            double credit = getUserCredit(user_email);

            String updateQuery = "UPDATE " + TABLE_NAME + " SET credit = " +
                    (credit+extra_credit) + " WHERE " + " email = '" + user_email + "';";

            db.execSQL(updateQuery);

            return true;

        } catch (IllegalArgumentException e){

            return false;

        }
    }

    public double getUserCredit(String user_email) throws IllegalArgumentException{

        Cursor allData = getData();

        double current_credit;

        while (allData.moveToNext()){

            if (allData.getString(1).equals(user_email)){
                current_credit = allData.getDouble(5);
                return current_credit;
            }

        }

        throw new IllegalArgumentException();
    }


}
