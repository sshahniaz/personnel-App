package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personnel.holidayFiles.HolidayCardDataModel;
import com.example.personnel.holidayFiles.HolidayListAdapter;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Holiday extends AppCompatActivity {
    RecyclerView holidayCardList;

    MaterialButtonToggleGroup holidayBtnGroup;
    private String selLeaveType;
    CardView datePickStartDate, datePickEndDate;
    DatePickerDialog datePickerDialog;
    Date selectedDate;
    TextView dateStartDay, dateStartMonth, dateStartYear, dateEndDay, dateEndMonth, dateEndYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        //Display Holidays
        holidayCardList = findViewById(R.id.holiday_cards_list);
        DBHelper holDB = new DBHelper(this);
        SQLiteDatabase listDB = holDB.getReadableDatabase();
        List<HolidayCardDataModel> dataList = new ArrayList<>();

        Cursor cursor = listDB.rawQuery("select * from "+holDB.leaveTable+" where "+holDB.employeeId+" = ?",new String[]{"1"});
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
        //Book Holiday
        DBHelper leaveDB = new DBHelper(this);
        SQLiteDatabase db = leaveDB.getWritableDatabase();

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





        //Leave status is int so 0 == Pending, 1 == Approved, -1 == Rejected;
        ContentValues values = new ContentValues();
//        values.put(leaveDB.employeeId, 1);
//        values.put(leaveDB.startDate,dateNow);
//        values.put(leaveDB.endDate,dateNow);
//        values.put(leaveDB.leaveType,"Sick");
//        values.put(leaveDB.reason,"Due to severe fever and headache");
//        values.put(leaveDB.status,0);

        //Test
//        long result = db.insert(leaveDB.leaveTable,null,values);
//        if (result == -1){
//            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show();
//
//        }

        db.close();






    }
}