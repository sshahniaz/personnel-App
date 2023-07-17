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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.example.personnel.Fragments.clockInFragment;
import com.example.personnel.Fragments.clockOutFragment;
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


//      *******  DEFAULT FRAGMENT DISPLAY *******


//      set variables for clock in/out function
        datetime = (TextView) findViewById(R.id.dateTime);
        datetime2 = (TextView) findViewById(R.id.dateTime2);
        shiftStart = (TextView) findViewById(R.id.shiftStart);
        shiftEnd = (TextView) findViewById(R.id.shiftEnd);
        displayBtn=(FrameLayout) findViewById(R.id.buttonFragment);


//        create fragment object and set as default fragment

        clockInFragment clockInFragmentObj = new clockInFragment();
        FragmentTransaction defaultTransaction = getSupportFragmentManager().beginTransaction();
        defaultTransaction.replace(R.id.buttonFragment, clockInFragmentObj);
        defaultTransaction.commit();

    }

}