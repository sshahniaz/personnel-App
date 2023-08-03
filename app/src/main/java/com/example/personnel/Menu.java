package com.example.personnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity {

    //For SharedPreferances
    private static final String LOGIN_PREF = "login_prefs";
    private static final String LOGIN_PREF_UID_KEY= "uid_key";
    private static final String LOGIN_PREF_CHECK_KEY= "check_key";

    public final String CURRENT_PAGE_KEY = "currentPage";
    private ImageButton holidayNav, homeNav, info, rotaNav, messagesNav, payslipNav, logoutNav, backArrow;
    private DBHelper dbHelper;
    private TextView headerText;
    private int empID;
    private String prevPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        homeNav=(ImageButton) findViewById(R.id.homeNav);
        holidayNav=(ImageButton) findViewById(R.id.holidayNav);
        rotaNav=(ImageButton) findViewById(R.id.rotaNav);
        messagesNav=(ImageButton) findViewById(R.id.messagesNav);
        payslipNav=(ImageButton) findViewById(R.id.payslipNav);
        logoutNav=(ImageButton) findViewById(R.id.logoutNav);
        backArrow=(ImageButton) findViewById(R.id.backArrow);


        //Get header
        headerText = findViewById(R.id.header_text);



        dbHelper=new DBHelper(this);

        //Get Extras for style and employee id

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Check DashBoard near end to check how the intents are set up and copy for all pages that require it.
            empID = extras.getInt(dbHelper.employeeId);
            prevPage = extras.getString(CURRENT_PAGE_KEY);
        }else{
            SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
            empID = preferences.getInt(LOGIN_PREF_UID_KEY,0);
        }

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(prevPage.equals("dashBoard")){
                    Intent intent=new Intent(Menu.this, DashBoard.class);
                    startActivity(intent);

                }
                if(prevPage.equals("rota")){
                    Intent intent=new Intent(Menu.this, Rota.class);
                    startActivity(intent);

                }
                if(prevPage.equals("payslip")){
                    Intent intent=new Intent(Menu.this, Payslip.class);
                    startActivity(intent);

                }
                if(prevPage.equals("holiday")){
                    Intent intent=new Intent(Menu.this, Holiday.class);
                    startActivity(intent);

                }
                if(prevPage.equals("messages")){
                    Intent intent=new Intent(Menu.this, Messages.class);
                    startActivity(intent);

                }
                if(prevPage.equals("help")){
                    Intent intent=new Intent(Menu.this, Help.class);
                    startActivity(intent);

                }

            }
        });
        //Style buttons with if



        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+dbHelper.employeeTable+" where "+dbHelper.employeeId+" = ?", new String[]{String.valueOf(empID)});
        if(cursor.moveToFirst()){
            headerText.setText("Welcome "+cursor.getString(1));
        }


        info=findViewById(R.id.infoBtn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Help.class);
                startActivity(intent);
            }
        });

        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, DashBoard.class);
                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });
        holidayNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Holiday.class);
                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });
        rotaNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Rota.class);
                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });
        messagesNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Messages.class);
                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });
        payslipNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, Payslip.class);
                intent.putExtra(dbHelper.employeeId,empID);
                startActivity(intent);
            }
        });
        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(LOGIN_PREF_CHECK_KEY, false);
                editor.putInt(LOGIN_PREF_UID_KEY,0);
                editor.apply();

                Intent intent=new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


   }




}