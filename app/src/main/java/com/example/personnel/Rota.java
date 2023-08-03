package com.example.personnel;
//import  com.example.personnel.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class Rota extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2,recyclerView3,recyclerView4;
    Rota_adapter week1Adapter, week2Adapter, week3Adapter, week4Adapter;
    List<Rota_Model> rotaList, rotaList2, rotaList3, rotaList4;
    ImageButton menuBtn;
    private static final String LOGIN_PREF = "login_prefs";
    private static final String LOGIN_PREF_UID_KEY= "uid_key";
    public final String CURRENT_PAGE_KEY = "currentPage";
    private int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);


        //return data from the db

        DBHelper dbHelper2=new DBHelper(Rota.this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Check DashBoard near end to check how the intents are set up and copy for all pages that require it.
            empID = extras.getInt(dbHelper2.employeeId);

        }else{
            SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
            empID = preferences.getInt(LOGIN_PREF_UID_KEY,0);
        }
        rotaList=dbHelper2.getWeek1(empID);
        rotaList2=dbHelper2.getWeek2(empID);
        rotaList3=dbHelper2.getWeek3(empID);
        rotaList4=dbHelper2.getWeek4(empID);


        menuBtn = findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rota.this, Menu.class);
                intent.putExtra(CURRENT_PAGE_KEY, "rota");
                intent.putExtra(dbHelper2.employeeId, empID);
                startActivity(intent);
            }
        });
   //set adapter to the recycler view
        week1Adapter=new Rota_adapter(rotaList);
        recyclerView=(RecyclerView) findViewById(R.id.reciclerView);
        recyclerView2=(RecyclerView) findViewById(R.id.reciclerView2);
        recyclerView3=(RecyclerView) findViewById(R.id.reciclerView3);
        recyclerView4=(RecyclerView) findViewById(R.id.reciclerView4);

//Linear Layout Manager (make sure the orientation)
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//increases performance-only use if sizes won't change
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(week1Adapter);

        week2Adapter=new Rota_adapter(rotaList2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(week2Adapter);

        week3Adapter=new Rota_adapter(rotaList3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setAdapter(week3Adapter);

        week4Adapter=new Rota_adapter(rotaList4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setAdapter(week4Adapter);

    }


}