package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
//    This is the login page / main page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is a test
        DBHelper personnelDB = new DBHelper(this);
        SQLiteDatabase db = personnelDB.getWritableDatabase();


    }
}