package com.example.personnel;
import  com.example.personnel.Menu;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Rota extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);


    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return true;
    }
}