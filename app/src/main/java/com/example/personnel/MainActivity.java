package com.example.personnel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    This is the login page / main page


    private DBHelper personnelDB;
    //TODO:Shared Preferences for login
    //Shared preferences keys and constants

    private static final String LOGIN_PREF = "login_prefs";
    private static final String LOGIN_PREF_CHECK_KEY= "check_key";
    private static final String LOGIN_PREF_UID_KEY= "uid_key";

    private SharedPreferences pref;
    //Setting default false so that user can get to login page.
    private boolean prefCheck = false;
    private int uid;

    private EditText userName,pswdInput;
    private Button login;
    //for checking if all the fields are valid are valid.
    private boolean isAllChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Get Views
        userName = findViewById(R.id.username_input);
        pswdInput = findViewById(R.id.pswd_input);
        login = findViewById(R.id.login);

        //init shared prefs
        pref = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        prefCheck = pref.getBoolean(LOGIN_PREF_CHECK_KEY, false);
        uid = pref.getInt(LOGIN_PREF_UID_KEY,0);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks for invalid data and does not proceed until all the data is valid
                isAllChecked = validateData();
                if (isAllChecked){
                    personnelDB = new DBHelper(getApplicationContext());
                    SQLiteDatabase db = personnelDB.getReadableDatabase();
                    Cursor cursor = db.rawQuery("select * from "+personnelDB.usersTable+" where "+personnelDB.username+" = ?",new String[]{userName.getText().toString().trim()});
                    if(cursor.moveToFirst()){

                    if((userName.getText().toString().trim()).equals(cursor.getString(1))){
                        //Toast for now will check for password hash later
                        Toast.makeText(getApplicationContext(),"Username VAlid empID: "+cursor.getInt(3),Toast.LENGTH_SHORT).show();
                        if((pswdInput.getText().toString().trim()).equals(cursor.getString(2))){

                            Toast.makeText(getApplicationContext(),"Password VAlid empID: "+cursor.getInt(3),Toast.LENGTH_SHORT).show();
                            //Make SharedPref Editor Object for editing.
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean(LOGIN_PREF_CHECK_KEY, true);
                            //Saving uid for next use.
                            editor.putInt(LOGIN_PREF_UID_KEY,cursor.getInt(3));
                            editor.apply();

                            //TODO: get employee id and transfer to next page

                            Intent intent = new Intent(MainActivity.this, DashBoard.class);
                            intent.putExtra(personnelDB.employeeId,cursor.getInt(3));
                            startActivity(intent);
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Username Invalid empID: "+cursor.getInt(3),Toast.LENGTH_SHORT).show();
                    }
                    }
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        prefCheck = pref.getBoolean(LOGIN_PREF_CHECK_KEY, false);
        uid = pref.getInt(LOGIN_PREF_UID_KEY,0);
        if(prefCheck && (uid==0)){
            Intent intent = new Intent(MainActivity.this, DashBoard.class);
            intent.putExtra("employee_id",uid);
            startActivity(intent);
            finish();
        }
    }


    private boolean validateData(){

        if(userName.length() == 0){
            userName.setError("This field is required");
            return false;
        }
        if(pswdInput.length() == 0){
            pswdInput.setError("This field is required");
            return false;
        }

        if (!(userName.getText().toString().trim()).matches("^[a-zA-Z0-9]([.](?![.])|[a-zA-Z0-9]){3,25}[a-zA-Z0-9]$")){
            userName.setError("Invalid Username");
            return false;
        }

        //password
        //valid only if Minimum eight characters, at least one letter and one number

        if(!(pswdInput.getText().toString().trim()).matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            //valid only if Minimum eight characters, at least one letter and one number
            pswdInput.setError("Invalid Password");
            return false;
        }

        return true;
    }
}

