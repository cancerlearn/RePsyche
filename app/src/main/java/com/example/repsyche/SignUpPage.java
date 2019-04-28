package com.example.repsyche;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class SignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


    }

    public void signinconnect(View v) {
        Intent u = new Intent(this, LoginPage.class);
        startActivity(u);

    }

    public native boolean newUser(String userName, String email);
    public native boolean signUp(String user_details);

}