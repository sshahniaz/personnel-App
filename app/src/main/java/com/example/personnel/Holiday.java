package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Holiday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        //Display Holidays



        //Book Holiday
        DBHelper leaveDB = new DBHelper(this);
        SQLiteDatabase holidayDB = leaveDB.getWritableDatabase();

        //Get date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        String dateNow = dateFormat.format(now);


        //Leave status is int so 0 == Pending, 1 == Approved, -1 == Rejected;
        ContentValues values = new ContentValues();





    }
}