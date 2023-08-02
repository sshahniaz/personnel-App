package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personnel.DashboardAndMessagesModelClasses.ClockInModel;
import com.example.personnel.DashboardAndMessagesModelClasses.ClockOutModel;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DashBoard extends AppCompatActivity {
boolean isClockedIn = false;
    Button clockBtn, messages;
    TextView datetime, datetime2, shiftDay, shiftStart, shiftEnd;

    boolean clockedIn = true;
    FrameLayout displayBtn;

    String dateString, timeString;
    private int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//      Set views clock in/out function
        datetime = (TextView) findViewById(R.id.dateTime);
        datetime2 = (TextView) findViewById(R.id.dateTime2);
        shiftDay = (TextView) findViewById(R.id.shiftDay);
        shiftStart = (TextView) findViewById(R.id.shiftStart);
        shiftEnd = (TextView) findViewById(R.id.shiftEnd);
        clockBtn = (Button) findViewById(R.id.clockInBtn);


//        Get bundle from intent
         Bundle extras = getIntent().getExtras();
        if (extras != null) {
            empID = extras.getInt("employee_id");
        }
        Toast.makeText(DashBoard.this, "Employee ID = " + empID, Toast.LENGTH_SHORT).show();

        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //                Create calendar object for data extraction for DB
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

//                Declare string variables for date and time
                dateString = date.format(calendar.getTime());
                timeString = time.format(calendar.getTime());

//                 Create object of DB helper class
                DBHelper dbHelper = new DBHelper(DashBoard.this);

                if (!isClockedIn) {
                    // Clock In logic
                    clockBtn.setText("Clock out");

//                    Create object of clock in model class and pass required parameters
                    ClockInModel objClockIn = new ClockInModel(empID, dateString, timeString);
                    boolean success = dbHelper.addClockInData(objClockIn);

//                    Feedback to user on clock in status
                    if (success) {
                        isClockedIn = true;
                        Toast.makeText(DashBoard.this, "Clock In successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DashBoard.this, "Clock In failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Clock Out logic
                    clockBtn.setText("Clock in");

//                    Create object of clock in model class and pass required parameters
                    ClockOutModel objClockOut = new ClockOutModel(empID, timeString, dateString);
               boolean outResult = dbHelper.updateClockOutTime(objClockOut);
                    isClockedIn = false;

//                    Feedback to user on clock out status
                    Toast.makeText(DashBoard.this, "Clock Out successful", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        Instantiate Db Helper, access db and retrieve values
      DBHelper dbHelper = new DBHelper(DashBoard.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.rotaTable + " WHERE " + dbHelper.employeeId + " = ?", new String[]{String.valueOf(empID)});

        if (cursor.moveToFirst()) {
            String dayValue = cursor.getString(2);
            String startTimeValue = cursor.getString(3);
            String endTimeValue = cursor.getString(4);

//            Toast.makeText(DashBoard.this, "Day = " + dayValue + ", Start time = " + startTimeValue + ", End time = " + endTimeValue , Toast.LENGTH_SHORT).show();
            shiftDay.setText(dayValue);
            shiftStart.setText(startTimeValue);
            shiftEnd.setText(endTimeValue);
        }

        cursor.close();
        db.close();
    }



}