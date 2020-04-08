package com.appscrip.trivia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Controllerdb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="SqliteListviewDB";
    public Controllerdb(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS UserDetails(Id INTEGER PRIMARY KEY AUTOINCREMENT,Username VARCHAR);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query ;
        query = "DROP TABLE IF EXISTS UserDetails";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
