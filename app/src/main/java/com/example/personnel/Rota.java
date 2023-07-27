package com.example.personnel;
import  com.example.personnel.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Rota extends AppCompatActivity {

    RecyclerView recyclerView;
    Rota_adapter mainAdapter;
    List<Rota_Model> rotaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota);

        //return data from the db

        DBHelper dbHelper2=new DBHelper(Rota.this);
        rotaList=dbHelper2.getAllRota();

   //set adapter to the recycler view
        mainAdapter=new Rota_adapter(rotaList);
        recyclerView=(RecyclerView) findViewById(R.id.reciclerView);

//Linear Layout Manager (make sure the orientation)
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//increases performance-only use if sizes won't change
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu,menu);
//        return true;
//    }
}