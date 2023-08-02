package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personnel.payslipAdapter.PayslipCustomListAdapter;

import java.util.ArrayList;

public class Payslip extends AppCompatActivity {

    DBHelper db;
    ListView listView;
    ArrayList<String> dataList;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

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
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;

    }
}