package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personnel.holidayFiles.HolidayCardDataModel;
import com.example.personnel.holidayFiles.HolidayListAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Holiday extends AppCompatActivity {
    private static final String LOGIN_PREF = "login_prefs";
    private static final String LOGIN_PREF_UID_KEY= "uid_key";
    RecyclerView holidayCardList;

    MaterialButtonToggleGroup holidayBtnGroup;
    private String selLeaveType;
    CardView datePickStartDate, datePickEndDate;
    DatePickerDialog datePickerDialog;
    Date selectedDate;
    TextView dateStartDay, dateStartMonth, dateStartYear, dateEndDay, dateEndMonth, dateEndYear;
    EditText holidayReason;
    Button bookBtn;
    private int empID;
    ImageButton menuBtn;
    public final String CURRENT_PAGE_KEY = "currentPage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        //Display Holidays
        holidayCardList = findViewById(R.id.holiday_cards_list);
        DBHelper holDB = new DBHelper(this);
        SQLiteDatabase listDB = holDB.getReadableDatabase();
        List<HolidayCardDataModel> dataList = new ArrayList<>();

        //Get Employee id from the intents
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            empID = extras.getInt(holDB.employeeId);
            Toast.makeText(this,"Empid: "+empID,Toast.LENGTH_SHORT).show();
        }
        //Checks if the empID is null or 0 and searches preferences for the correct ID;
        if(empID==0){
            SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
            empID = preferences.getInt(LOGIN_PREF_UID_KEY,0);
        }


        Cursor cursor = listDB.rawQuery("select * from "+holDB.leaveTable+" where "+holDB.employeeId+"=?;",new String[]{String.valueOf(empID)});
        if(cursor.moveToLast()){
            do{
                dataList.add(new HolidayCardDataModel(cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                cursor.getInt(6)));
            }while(cursor.moveToPrevious());
        }
        listDB.close();

        HolidayListAdapter adapter = new HolidayListAdapter(this,dataList);

        holidayCardList.setAdapter(adapter);
        holidayCardList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter.notifyDataSetChanged();
        //Book Holiday

        //Set Current Date on start

        //start date card set time.
        dateStartDay = findViewById(R.id.date_start_day);
        dateStartMonth = findViewById(R.id.date_start_month);
        dateStartYear = findViewById(R.id.date_start_year);

        dateEndDay = findViewById(R.id.date_end_day);
                dateEndMonth = findViewById(R.id.date_end_month);
        dateEndYear = findViewById(R.id.date_end_year);

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        final Calendar cal = Calendar.getInstance();
        Date dateToday = cal.getTime();

        dateStartDay.setText(dayFormat.format(dateToday));
        dateStartMonth.setText(monthFormat.format(dateToday));
        dateStartYear.setText(yearFormat.format(dateToday));

        dateEndDay.setText(dayFormat.format(dateToday));
        dateEndMonth.setText(monthFormat.format(dateToday));
        dateEndYear.setText(yearFormat.format(dateToday));

        //Get Datepicker from card on click
        datePickStartDate = findViewById(R.id.datePick_start_date);
        datePickStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get current date for dialog
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                final Calendar cal = Calendar.getInstance();
                int mYear = cal.get(Calendar.YEAR); // current year
                int mMonth = cal.get(Calendar.MONTH); // current month
                int mDay = cal.get(Calendar.DAY_OF_MONTH); // current day

//                Date now = cal.getTime();


                datePickerDialog = new DatePickerDialog(Holiday.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat req = new SimpleDateFormat("dd-MM-yyyy");
                        String dateSelected = dayOfMonth+"-"+month+"-"+year;

                        try {
                            selectedDate = req.parse(dateSelected);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        String dayNow = dayFormat.format(selectedDate);
                        String monthNow = monthFormat.format(selectedDate);
                        String yearNow = yearFormat.format(selectedDate);



                        dateStartDay.setText(dayNow);
                        dateStartMonth.setText(monthNow);
                        dateStartYear.setText(yearNow);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });


        //Date Picker End date

        datePickEndDate = findViewById(R.id.datePick_end_date);

        datePickEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get current date for dialog
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                final Calendar cal = Calendar.getInstance();
                int mYear = cal.get(Calendar.YEAR); // current year
                int mMonth = cal.get(Calendar.MONTH); // current month
                int mDay = cal.get(Calendar.DAY_OF_MONTH); // current day


                datePickerDialog = new DatePickerDialog(Holiday.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat req = new SimpleDateFormat("dd-MM-yyyy");
                        String dateSelected = dayOfMonth+"-"+month+"-"+year;

                        try {
                            selectedDate = req.parse(dateSelected);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        String dayNow = dayFormat.format(selectedDate);
                        String monthNow = monthFormat.format(selectedDate);
                        String yearNow = yearFormat.format(selectedDate);



                        dateEndDay.setText(dayNow);
                        dateEndMonth.setText(monthNow);
                        dateEndYear.setText(yearNow);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });

        //Button Group selection
        holidayBtnGroup = findViewById(R.id.holiday_btn_group);
        holidayBtnGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                if(isChecked){
                    if(checkedId==R.id.holiday_btn_holiday){
                        selLeaveType = "Holiday";

                    }
                    if (checkedId==R.id.holiday_btn_inlieu) {
                        selLeaveType = "Lieu";

                    }
                    if(checkedId==R.id.holiday_btn_sick){
                        selLeaveType = "Sick";

                    }


                }
            }
        });


        holidayReason = findViewById(R.id.holiday_reason);
        bookBtn = findViewById(R.id.holiday_book_btn);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Booking here
                DBHelper leaveDB = new DBHelper(getApplicationContext());
                SQLiteDatabase db = leaveDB.getWritableDatabase();

                if(selLeaveType!=null && holidayReason!=null){
                    //Leave status is int so 0 == Pending, 1 == Approved, -1 == Rejected;
                    ContentValues values = new ContentValues();
                    values.put(leaveDB.employeeId, empID);
                    values.put(leaveDB.startDate,(dateStartDay.getText().toString()+"-"+dateStartMonth.getText().toString()+"-"+dateStartYear.getText().toString()));
                    values.put(leaveDB.endDate,(dateEndDay.getText().toString()+"-"+dateEndMonth.getText().toString()+"-"+dateEndYear.getText().toString()));
                    values.put(leaveDB.leaveType,selLeaveType);
                    values.put(leaveDB.reason,holidayReason.getText().toString());
                    values.put(leaveDB.status,0);

                    //Test
                    long result = db.insert(leaveDB.leaveTable,null,values);
                    if (result == -1){
                        Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();

                    }

                    db.close();

                }else{
                    bookBtn.setError("Please Select Holiday Type and Reason");
                    holidayReason.setError("Reason cannot be empty");
                }


            }
        });

        menuBtn = findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Holiday.this, Menu.class);
                intent.putExtra(CURRENT_PAGE_KEY, "holiday");
                intent.putExtra(holDB.employeeId, empID);
                startActivity(intent);
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;

    }
}