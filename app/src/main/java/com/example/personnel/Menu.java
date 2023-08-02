package com.example.personnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    private ImageButton holidayNav, homeNav, helpNav, rotaNav, messagesNav, payslipNav, logoutNav;
    private DBHelper dbHelper;
    private int employeeID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helpNav=(ImageButton) findViewById(R.id.helpNav);
        homeNav=(ImageButton) findViewById(R.id.homeNav);
        holidayNav=(ImageButton) findViewById(R.id.holidayNav);
        rotaNav=(ImageButton) findViewById(R.id.rotaNav);
        messagesNav=(ImageButton) findViewById(R.id.messagesNav);
        payslipNav=(ImageButton) findViewById(R.id.payslipNav);
        logoutNav=(ImageButton) findViewById(R.id.logoutNav);

        dbHelper=new DBHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(employeeTable, columns, null, null, null, null, null);


        helpNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Help.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });

        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, DashBoard.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        holidayNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Holiday.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        rotaNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Rota.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        messagesNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Messages.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        payslipNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Payslip.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });
        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, MainActivity.class);
                intent.putExtra(db.employeeId,cursor.getInt(3));
                startActivity(intent);
            }
        });


   }




}