package com.appscrip.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowdataListview extends AppCompatActivity {

    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Name = new ArrayList<String>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata_listview);
        lv = (ListView) findViewById(R.id.list);

    }

    @Override
    protected void onResume() {
        DisplayData();

        super.onResume();
    }
    public void DisplayData()
    {
        db = controllerdb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  UserDetails",null);
        Id.clear();
        Name.clear();

        if (cursor.moveToFirst()) {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                Name.add(cursor.getString(cursor.getColumnIndex("Username")));

            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(ShowdataListview.this,Id, Name);
        lv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }

}
