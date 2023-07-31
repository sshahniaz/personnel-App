package com.example.personnel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        drawerLayout=findViewById(R.id.drawer_layout);
//
//        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,R.string.close_nav);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//
////        not showing the app title
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//


//        if(savedInstanceState==null)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_frag()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//            Intent intent=new Intent(Menu.this, DashBoard.class);
//            startActivity(intent);
//        }
   }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int item_id=item.getItemId();
        if(item_id==R.id.nav_home)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_frag()).commit();
            Intent intent=new Intent(Menu.this, DashBoard.class);
            startActivity(intent);

        }
        else
        if(item_id==R.id.nav_holiday)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Holiday_frag()).commit();
            Intent intent=new Intent(Menu.this, Holiday.class);
            startActivity(intent);
        }
        else
        if(item_id==R.id.nav_payslip)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Payslip_frag()).commit();
            Intent intent=new Intent(Menu.this, Payslip.class);
            startActivity(intent);
        }
        else
        if(item_id==R.id.nav_rota)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Rota_frag()).commit();
            Intent intent=new Intent(Menu.this, Rota.class);
            startActivity(intent);
        }
        else
        if(item_id==R.id.nav_messages)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Messages_frag()).commit();
            Intent intent=new Intent(Menu.this, Messages.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}