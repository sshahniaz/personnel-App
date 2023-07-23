package com.example.personnel.DashboardFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.personnel.DBHelper;
import com.example.personnel.R;
import com.example.personnel.DashboardAndMessagesModelClasses.ClockOutModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class clockOutFragment extends Fragment {
    View view;
    Context context;
    Button clockoutBtn;
String outDateString, outTimeString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and set in view variable
       view = inflater.inflate(R.layout.fragment_clock_out, container, false);

//       set button for on click function
       Button clockoutBtn = (Button) view.findViewById(R.id.clockOutbtn);

//       set on click function
       clockoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

//               Create Calendar

               Calendar outCalendar = Calendar.getInstance();

//               Set formats for date and time

               SimpleDateFormat outDate = new SimpleDateFormat("dd-MM-yyyy ");
               SimpleDateFormat outTime = new SimpleDateFormat("HH:mm:ss ");

//               Set dates in string

               outDateString = outDate.format(outCalendar.getTime());
               outTimeString = outTime.format(outCalendar.getTime());

//               Create object of clockOutModel class and pass params to constructor

               ClockOutModel objClockOut = new ClockOutModel(-1, outTimeString, outDateString);

//               Create object of clock in fragment
               clockInFragment clockInFragmentObj = new clockInFragment();
               FragmentTransaction clockOutTransaction = getFragmentManager().beginTransaction();
               clockOutTransaction.replace(R.id.buttonFragment, clockInFragmentObj);
               clockOutTransaction.commit();

//               Create object of db helper class

               DBHelper outDbHelper = new DBHelper(getContext());

               boolean outResult = outDbHelper.updateClockOutTime(objClockOut);

               Toast.makeText(getContext(), objClockOut.toString() + "Result= " + outResult, Toast.LENGTH_LONG).show();

           }
       });

        return view;
    }
}