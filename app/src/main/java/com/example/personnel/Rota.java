package com.example.personnel;
//import  com.example.personnel.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Rota extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView2,recyclerView3,recyclerView4;
    Rota_adapter week1Adapter, week2Adapter, week3Adapter, week4Adapter;
    List<Rota_Model> rotaList, rotaList2, rotaList3, rotaList4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        //return data from the db

        DBHelper dbHelper2=new DBHelper(Rota.this);
        rotaList=dbHelper2.getWeek1();
        rotaList2=dbHelper2.getWeek2();


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

        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setAdapter(week3Adapter);

        recyclerView4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setAdapter(week4Adapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu,menu);
//        return true;
//    }
}