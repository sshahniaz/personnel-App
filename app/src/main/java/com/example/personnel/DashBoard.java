package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//        create object from db helper class
        DBHelper objDBHelper = new DBHelper(this);

//        call writable method to insert values to database
        SQLiteDatabase db = objDBHelper.getWritableDatabase();

//        create values object for key value pairs to pass to writable method
        ContentValues values = new ContentValues();

//      test values to insert into database
        values.put(objDBHelper.date, "11/07/2023");
        values.put(objDBHelper.clockInTime, "09:00");
        values.put(objDBHelper.clockOutTime, "17:00");

//        insert to database
        long result = db.insert("attendance", null, values);

//        feedback on status
        if(result ==-1) {
            Toast.makeText(DashBoard.this, "Values not inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(DashBoard.this, "Values inserted on row" + result, Toast.LENGTH_LONG).show();
        }
    }
}