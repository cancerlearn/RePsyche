package com.example.repsyche;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

    public void adminsigninconnect(View v) {
        Intent n = new Intent(this, LoginPage.class);
        startActivity(n);

    }

    public void signUp_onclick(View view){
        EditText firstname = (EditText) findViewById(R.id.FirstName);
        String fname_content = firstname.getText().toString();

        EditText lasttname = (EditText) findViewById(R.id.LastName);
        String lname_content = lasttname.getText().toString();

        EditText username = (EditText) findViewById(R.id.SignUp_Username);
        String username_content = username.getText().toString();

        EditText email = (EditText) findViewById(R.id.SignUp_Email);
        String email_content = username.getText().toString();

        EditText password = (EditText) findViewById(R.id.SignUp_Password);
        String password_content = password.getText().toString();

        EditText confirm_password = (EditText) findViewById(R.id.SignUp_ConfirmPassword);
        String confirmpassword_content = confirm_password.getText().toString();
    }

    public native boolean newUser(String userName, String email);
    public native boolean signUp(String user_details);
}