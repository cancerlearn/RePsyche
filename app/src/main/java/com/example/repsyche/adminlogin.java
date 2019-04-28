package com.example.repsyche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
    }

    public void adminpageconnect(View v) {
        Intent p = new Intent(this, AdminPage.class);
        startActivity(p);

    }
}
