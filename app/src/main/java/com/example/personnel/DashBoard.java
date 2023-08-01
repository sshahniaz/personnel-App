package com.example.personnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity {
    private ImageButton holiday, rota, messages, payslip;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        holiday=(ImageButton) findViewById(R.id.holidayButton);
        rota=(ImageButton) findViewById(R.id.rotaButton);
        payslip=(ImageButton) findViewById(R.id.payslipButton);
        messages=(ImageButton) findViewById(R.id.messageButton);

        dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Holiday.class);
                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Rota.class);
                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Messages.class);
                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        payslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this, Payslip.class);
                intent.putExtra(db.empoyeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });

    }





}