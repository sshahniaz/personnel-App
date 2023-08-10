package com.example.personnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.personnel.DashboardAndMessagesModelClasses.ClockInModel;
import com.example.personnel.DashboardAndMessagesModelClasses.ClockOutModel;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DashBoard extends AppCompatActivity {
    boolean isClockedIn = false;
    public final String CURRENT_PAGE_KEY = "currentPage";
    Button clockBtn;
    TextView datetime, datetime2, shiftDay, shiftStart, shiftEnd;

    boolean clockedIn = true;
    FrameLayout displayBtn;

    String dateString, timeString;
    private int empID;

    private ImageButton holiday, rota, messages, payslip, menuBtn,info;
    private DBHelper dbHelper;
    private static final String LOGIN_PREF = "login_prefs";
    private static final String LOGIN_PREF_UID_KEY= "uid_key";
    private Cursor cursor;
    
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
        }else{
            SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
            empID = preferences.getInt(LOGIN_PREF_UID_KEY,0);
            if(empID == 0){
                Intent intent = new Intent(DashBoard.this, MainActivity.class);
                startActivity(intent);
            }
            finish();

        }
//        Toast.makeText(DashBoard.this, "Employee ID = " + empID, Toast.LENGTH_SHORT).show();

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
               dbHelper = new DBHelper(DashBoard.this);

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
        cursor = db.rawQuery("SELECT * FROM " + dbHelper.rotaTable + " WHERE " + dbHelper.employeeId + " = ?", new String[]{String.valueOf(empID)});

        if (cursor.moveToFirst()) {
            String dayValue = cursor.getString(2);
            String startTimeValue = cursor.getString(3);
            String endTimeValue = cursor.getString(4);

//            Toast.makeText(DashBoard.this, "Day = " + dayValue + ", Start time = " + startTimeValue + ", End time = " + endTimeValue , Toast.LENGTH_SHORT).show();
            shiftDay.setText(dayValue);
            shiftStart.setText(startTimeValue);
            shiftEnd.setText(endTimeValue);
        }



        holiday=(ImageButton) findViewById(R.id.holidayButton);
        rota=(ImageButton) findViewById(R.id.rotaButton);
        payslip=(ImageButton) findViewById(R.id.payslipButton);
        messages=(ImageButton) findViewById(R.id.messageButton);



        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Holiday.class);

                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });

        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Rota.class);
//                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Messages.class);
//                intent.putExtra(dbHelper.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        payslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Payslip.class);
//                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });

        //Menu Btn


        menuBtn = findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, Menu.class);
                intent.putExtra(CURRENT_PAGE_KEY, "dashBoard");
                intent.putExtra(dbHelper.employeeId, empID);
                startActivity(intent);


            }
        });

        info=findViewById(R.id.infoBtn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Help.class);
                startActivity(intent);
            }
        });

        cursor.close();
        db.close();

    }

}