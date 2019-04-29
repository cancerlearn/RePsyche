package com.example.repsyche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BinDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "BinDatabaseHelper";

    private static final String TABLE_NAME = "bin_table";
    private static final String COL1 = "bin";
    private static final String COL2 = "capacity";
    private static final String COL3 = "available_capacity";

    public BinDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("+
                COL1 + " TEXT PRIMARY KEY, " +
                COL2 + " INTEGER NOT NULL, "+
                COL3 + " INTEGER NOT NULL)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addBin(String bin, int cap, int available_cap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, bin);
        contentValues.put(COL2, cap);
        contentValues.put(COL3, available_cap);

        Log.d(TAG, "addData: Adding " + "Bin: " + bin + "Capacity: "+ "Available Capacity: " +
                available_cap + " to " + TABLE_NAME);

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

    public boolean decreaseBinCapacity(String bin){

        SQLiteDatabase db = this.getWritableDatabase();

        int a_cap = getAvailableCapacity(bin);

        if (a_cap == 0){
            return false;
        }

        String updateQuery = "UPDATE " + TABLE_NAME +
                " SET " + COL3 + " = " + (a_cap-1) + " WHERE " + COL1 + " = '" + bin + "';";

        db.execSQL(updateQuery);

        return true;
    }

    public int getAvailableCapacity(String bin){

        Cursor allData = getData();

        int available_cap;

        while (allData.moveToNext()){

            if (allData.getString(0).equals(bin)){
                available_cap = allData.getInt(2);
                return available_cap;
            }

        }

        return 0;
    }

}
