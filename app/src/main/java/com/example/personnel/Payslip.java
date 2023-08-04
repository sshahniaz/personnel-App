package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personnel.payslipAdapter.PayslipCustomListAdapter;

import java.util.ArrayList;

public class Payslip extends AppCompatActivity {

    private DBHelper db;
    private ListView listView;
    private ArrayList<String> dataList;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private ImageButton menuBtn,info;
    public final String CURRENT_PAGE_KEY = "currentPage";
    private int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip);

        db = new DBHelper(this);
        listView = (ListView) findViewById(R.id.payslips);

        dataList = new ArrayList<>();
        viewAll();

        PayslipCustomListAdapter adapter = new PayslipCustomListAdapter(this, dataList);
        listView.setAdapter(adapter);

        // Request the WRITE_EXTERNAL_STORAGE permission if it's not granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        menuBtn = findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payslip.this, Menu.class);
                intent.putExtra(CURRENT_PAGE_KEY, "payslip");
                intent.putExtra(db.employeeId, empID);
                startActivity(intent);
            }
        });
        info=findViewById(R.id.infoBtn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payslip.this, Help.class);
                startActivity(intent);
            }
        });
    }

    public void viewAll() {
        Cursor dBValues = db.getAlPayslip(2);
        if (dBValues.getCount() == 0) {
            Toast.makeText(Payslip.this, "The database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while (dBValues.moveToNext()) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(dBValues.getString(2)).append("\n");
                buffer.append(dBValues.getString(3)).append("\n");
                buffer.append(dBValues.getString(4)).append("\n");
                dataList.add(buffer.toString());
            }
        }
    }

}