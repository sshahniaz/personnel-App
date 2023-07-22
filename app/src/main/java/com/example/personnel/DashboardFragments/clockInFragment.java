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
import com.example.personnel.DashboardModelClasses.clockInModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class clockInFragment extends Fragment {
View view;
Context context;
Button clockinBtn;
String dateString, timeString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clock_in, container, false);

//        Select button for click listener

        Button clockinBtn = (Button) view.findViewById(R.id.clockInBtn);

//      Set click listener
        clockinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Create calendar object for data extraction for DB

                Calendar calendar = Calendar.getInstance();

//                Set formats for date and time

                SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss ");
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyy ");

//                Declare string variables for date and time

                dateString = date.format(calendar.getTime());
                timeString = time.format(calendar.getTime());

//                Create object of clock in model class and pass required parameters

                clockInModel objClockIn = new clockInModel(-1, dateString, timeString);

//                Create object of DB helper class

                DBHelper dbHelper = new DBHelper(getContext());

//                insert data to table using addClockInData method

                boolean success = dbHelper.addClockInData(objClockIn);

//                test clock in model params and success of method

                Toast.makeText(getContext(), objClockIn.toString() + "result= " + success, Toast.LENGTH_LONG).show();

//            Create object of clock out fragment, create transaction, replace view and commit

                clockOutFragment clockOutFragmentObj = new clockOutFragment();
                FragmentTransaction clockInTransaction = getFragmentManager().beginTransaction();
                clockInTransaction.replace(R.id.buttonFragment, clockOutFragmentObj);
                clockInTransaction.commit();
            }
        });
        return view;
    }
}