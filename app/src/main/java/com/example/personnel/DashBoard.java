package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import com.example.personnel.DashboardFragments.clockInFragment;

public class DashBoard extends AppCompatActivity {

    Button clockBtn, messages;
    TextView datetime, datetime2, shiftDay, shiftStart, shiftEnd;

    boolean clockedIn = true;
    FrameLayout displayBtn;

    String dateString, timeString;
    int empID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//      Set views clock in/out function
        datetime = (TextView) findViewById(R.id.dateTime);
        datetime2 = (TextView) findViewById(R.id.dateTime2);
        shiftStart = (TextView) findViewById(R.id.shiftStart);
        shiftEnd = (TextView) findViewById(R.id.shiftEnd);
        displayBtn=(FrameLayout) findViewById(R.id.buttonFragment);

//        Get bundle from intent
        Bundle extras = getIntent().getExtras();
        empID = extras.getInt("employee_id");
        Toast.makeText(DashBoard.this, "Employee ID = " + empID, Toast.LENGTH_LONG).show();

//      *******  DEFAULT FRAGMENT DISPLAY *******

//        Create fragment object and set as default view
        clockInFragment clockInFragmentObj = new clockInFragment();
        FragmentTransaction defaultTransaction = getSupportFragmentManager().beginTransaction();

//        Bundle to send values to fragment
        Bundle bundle = new Bundle();
        bundle.putString("empId", String.valueOf(empID));
        clockInFragmentObj.setArguments(bundle);
        defaultTransaction.replace(R.id.buttonFragment, clockInFragmentObj);
        defaultTransaction.commit();

//        Instantiate Db Helper, access db and retrieve values
        DBHelper dbHelper = new DBHelper(DashBoard.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.rotaTable + " WHERE " + dbHelper.employeeId + " = ?", new String[]{String.valueOf(empID)});

        if (cursor.moveToFirst()) {
            String dayValue = cursor.getString(2);
            String startTimeValue = cursor.getString(3);
            String endTimeValue = cursor.getString(4);

            Toast.makeText(DashBoard.this, "Day = " + dayValue + ", Start time = " + startTimeValue + ", End time = " + endTimeValue , Toast.LENGTH_LONG).show();
            shiftDay.setText(dayValue);
            shiftStart.setText(startTimeValue);
            shiftEnd.setText(endTimeValue);
        }

        cursor.close();
        db.close();
    }

}