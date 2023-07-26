package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Payslip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip);
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;

    }
}