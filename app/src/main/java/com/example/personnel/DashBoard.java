package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DashBoard extends AppCompatActivity {

    Button clockBtn;
    TextView datetime, datetime2, shiftStart, shiftEnd;
    boolean clockedIn = true;
    FrameLayout displayBtn;
    String dateString, timeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


//      *******  CLOCK IN AND CLOCK OUT FUNCTION *******



//      set variables for clock in/out function
        datetime = (TextView) findViewById(R.id.dateTime);
        datetime2 = (TextView) findViewById(R.id.dateTime2);
//        clockBtn = (Button) findViewById(R.id.clockBtn);
        shiftStart = (TextView) findViewById(R.id.shiftStart);
        shiftEnd = (TextView) findViewById(R.id.shiftEnd);
        displayBtn=(FrameLayout) findViewById(R.id.buttonFragment);
//        create on click method
        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                create date object
                Calendar calendar = Calendar.getInstance();

//                set date format
                SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss ");
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy ");

//                declare string variable

                dateString = date.format(calendar.getTime());
                timeString = time.format(calendar.getTime());

//                Testing date object
                datetime.setText(timeString);
                datetime2.setText(dateString);


//                call toggle method to switch between clocked in/out state
                toggleClockStatus();
            }
        });



//        ******* DATABASE FUNCTIONS *******



//        create object from db helper class
        DBHelper objDBHelper = new DBHelper(this);

//        call writable method to insert values to database
        SQLiteDatabase db = objDBHelper.getWritableDatabase();

//        create values object for key value pairs to pass to writable method
        ContentValues values = new ContentValues();

//      test values to insert into database
        values.put(objDBHelper.date, dateString);
        values.put(objDBHelper.clockInTime, "09:00");
        values.put(objDBHelper.clockOutTime, "17:00");

//        insert to database
//        long result = db.insert("attendance", null, values);
//
////        feedback on status
//        if(result ==-1) {
//            Toast.makeText(DashBoard.this, "Values not inserted", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(DashBoard.this, "Values inserted on row" + result, Toast.LENGTH_LONG).show();
//        }
//
    }

//    clock toggle method change button text
    public void toggleClockStatus() {
        clockedIn = !clockedIn;
        if (clockedIn) {
            clockBtn.setText("Clock in");
        } else {
            clockBtn.setText("Clock out");
        }
    }
}