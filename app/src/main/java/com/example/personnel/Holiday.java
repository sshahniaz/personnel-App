package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personnel.holidayFiles.HolidayCardDataModel;
import com.example.personnel.holidayFiles.HolidayListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Holiday extends AppCompatActivity {
    RecyclerView holidayCardList;
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

        db.close();






    }
}