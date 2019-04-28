package com.example.repsyche;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    UserDatabaseHelper mUserDatabaseHelper;
    EditText email, password;

    static {
        System.loadLibrary("DatabaseOperations_native");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mUserDatabaseHelper = new UserDatabaseHelper(this);
        email = findViewById(R.id.SignIn_email);
        password = findViewById(R.id.SignUp_password);
    }

    public void SignIn_onclick_homepageconnect(View v) {

        String email_content = email.getText().toString();
        String password_content = password.getText().toString();

        if (confirmUser(email_content, password_content)){

            Intent p = new Intent(this, HomePage.class);
            startActivity(p);

        }
        else {
            createAlertBox("Invalid Info", "Username or password is incorrect.");
        }

    }

    public void createAlertBox(String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNegativeButton(android.R.string.no, null);
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.show();
    }

    public boolean confirmUser(String user_email, String user_password){

        Cursor allData = mUserDatabaseHelper.getData();

        while (allData.moveToNext()){

            if (user_email.equals(allData.getString(1))
                    && user_password.equals(allData.getString(4))){

                return true;

            }

        }

        return false;

    }
}
