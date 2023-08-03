package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Help extends AppCompatActivity {
    Button guide1btn, guide2btn, guide3btn, guide4btn, guide5btn, backGuide1, backGuide2, backGuide3, backGuide4, backGuide5, contact;
    private int empID;
    ImageButton menuBtn;

    DBHelper dbHelper;
    public final String CURRENT_PAGE_KEY = "currentPage";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        dbHelper=new DBHelper(this);
        menuBtn = findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, Menu.class);
                intent.putExtra(CURRENT_PAGE_KEY, "help");
                intent.putExtra(dbHelper.employeeId, empID);
                startActivity(intent);
            }
        });

        Dialog guide1 = new Dialog(this);
        guide1.setContentView(R.layout.guide1_pop_up);
        guide1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_guide_layout));
        guide1btn = findViewById(R.id.guide1btn);
        backGuide1 = guide1.findViewById(R.id.backGuide1);
        guide1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Dialog guide2 = new Dialog(this);
        guide2.setContentView(R.layout.guide2_pop_up);
        guide2.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_guide_layout));
        guide2btn = (Button)findViewById(R.id.guide2btn);
        backGuide2 = guide2.findViewById(R.id.backGuide2);
        guide2.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Dialog guide3 = new Dialog(this);
        guide3.setContentView(R.layout.guide3_pop_up);
        guide3.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_guide_layout));
        guide3btn = (Button)findViewById(R.id.guide3btn);
        backGuide3 = guide3.findViewById(R.id.backGuide3);
        guide3.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Dialog guide4 = new Dialog(this);
        guide4.setContentView(R.layout.guide4_pop_up);
        guide4.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_guide_layout));
        guide4btn = (Button)findViewById(R.id.guide4btn);
        backGuide4 = guide4.findViewById(R.id.backGuide4);
        guide4.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Dialog guide5 = new Dialog(this);
        guide5.setContentView(R.layout.guide5_pop_up);
        guide5.getWindow().setBackgroundDrawable(getDrawable(R.drawable.pop_up_guide_layout));
        guide5btn = (Button)findViewById(R.id.guide5btn);
        backGuide5 = guide5.findViewById(R.id.backGuide5);
        guide5.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        guide1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide1.show();
            }
        });
        backGuide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide1.dismiss();
            }
        });


        guide2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide2.show();
            }
        });
        backGuide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide2.dismiss();
            }
        });


        guide3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide3.show();
            }
        });
        backGuide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide3.dismiss();
            }
        });


        guide4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide4.show();
            }
        });
        backGuide4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide4.dismiss();
            }
        });


        guide5btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide5.show();
            }
        });
        backGuide5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guide5.dismiss();
            }
        });

        contact = (Button) findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:+44 7700 900716");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
    }
}