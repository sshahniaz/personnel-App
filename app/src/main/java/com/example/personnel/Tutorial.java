package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.personnel.tutorialFragments.TutorialDashBoard;
import com.example.personnel.tutorialFragments.TutorialHelp;
import com.example.personnel.tutorialFragments.TutorialHoliday;
import com.example.personnel.tutorialFragments.TutorialMessages;
import com.example.personnel.tutorialFragments.TutorialPayslip;
import com.example.personnel.tutorialFragments.TutorialRota;

public class Tutorial extends AppCompatActivity {

    private Button back, next, skip;
    private RelativeLayout fragmentLayout;
    private Fragment[] fragments;
    private int currentFragmentIndex;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String TUTORIAL_SHOWN_KEY = "tutorialShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the tutorial has been shown before
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean tutorialShown = preferences.getBoolean(TUTORIAL_SHOWN_KEY, false);

        //Init DB
        DBHelper initDB = new DBHelper(this);
        SQLiteDatabase db = initDB.getReadableDatabase();
        db.close();
        initDB.close();


        if (tutorialShown) {
            // Tutorial has been shown before, go to the main activity or home screen
            launchMainActivity();
            return;
        }

        setContentView(R.layout.activity_tutorial);

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        skip = findViewById(R.id.skip);
        fragmentLayout = findViewById(R.id.defaultLayout);

        // Initializing fragments in an array to then go through them using their position in the array
        fragments = new Fragment[]{
                new TutorialDashBoard(),
                new TutorialPayslip(),
                new TutorialHoliday(),
                new TutorialRota(),
                new TutorialMessages(),
                new TutorialHelp()
        };

        // The first fragment will be displayed
        currentFragmentIndex = 0;
        displayFragment(fragments[currentFragmentIndex]);
        updateBackButtonVisibility();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragmentIndex > 0) {
                    currentFragmentIndex--;
                    displayFragment(fragments[currentFragmentIndex]);
                    updateBackButtonVisibility();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragmentIndex < fragments.length - 1) {
                    currentFragmentIndex++;
                    displayFragment(fragments[currentFragmentIndex]);
                    updateBackButtonVisibility();
                } else {
                    // Tutorial completed, go to the main activity or home screen
                    launchMainActivity();
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                // Skip the tutorial and go to the main activity or home screen
                launchMainActivity();
            }
        });
    }

    // Helper method for displaying each fragment.
    private void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.defaultLayout, fragment);
        fragmentTransaction.commit();
    }

    // Helper method to change the visibility of the "back" button, where it only appears when the current fragment is >= 1.
    private void updateBackButtonVisibility() {
        if (currentFragmentIndex >= 1) {
            back.setVisibility(View.VISIBLE);
        } else {
            back.setVisibility(View.GONE);
        }
    }

    private void launchMainActivity() {
        // Save that the tutorial has been shown
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(TUTORIAL_SHOWN_KEY, true);
        editor.apply();

        // Go to the main activity or home screen
        Intent intent = new Intent(Tutorial.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}