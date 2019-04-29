package com.example.repsyche;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomePage extends AppCompatActivity {

    private UserDatabaseHelper mUserDatabaseHelper;
    private EditText userEmail_TextView;
    private String user_email;
    private Double user_credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mUserDatabaseHelper = new UserDatabaseHelper(this);
        userEmail_TextView = findViewById(R.id.userEmail);
        user_email = getIntent().getStringExtra("user_email");
        getCredit();
        userEmail_TextView.setText("Welcome, "+user_email);
    }

    public void binsconnect(View v) {
        Intent p = new Intent(this, Bins.class);
        p.putExtra("User_email", user_email);
        p.putExtra("User_credit", user_credit);
        startActivity(p);

    }

    public void getCredit(){

        Cursor allData = mUserDatabaseHelper.getData();

        while (allData.moveToNext()){

            if (allData.getString(1).equals(user_email)){
                user_credit = allData.getDouble(5);

            }
        }

    }

    public void user_detail_onclick(View v){

        getCredit();

        createAlertBox("Account Details", "\nCredit: "+Double.toString(user_credit));

    }

    public void createAlertBox(String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNegativeButton(android.R.string.no, null);
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.show();
    }
}
