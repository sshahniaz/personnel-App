package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

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
        SQLiteDatabase db = leaveDB.getWritableDatabase();

        //Get date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        String dateNow = dateFormat.format(now);


        //Leave status is int so 0 == Pending, 1 == Approved, -1 == Rejected;
        ContentValues values = new ContentValues();
        values.put(leaveDB.employeeId, 1);
        values.put(leaveDB.startDate,dateNow);
        values.put(leaveDB.endDate,dateNow);
        values.put(leaveDB.leaveType,"Sick");
        values.put(leaveDB.reason,"Due to severe fever and headache");
        values.put(leaveDB.status,0);

        //Test
//        long result = db.insert(leaveDB.leaveTable,null,values);
//        if (result == -1){
//            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show();
//
//        }








    }
}