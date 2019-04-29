package com.example.repsyche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class adminlogin extends AppCompatActivity {

    private EditText admin_email, admin_password;
    private AdminDatabaseHelper mAdminDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        admin_email = findViewById(R.id.Admin_SignIn_email);
        admin_password = findViewById(R.id.Admin_password);
        mAdminDatabaseHelper = new AdminDatabaseHelper(this);
    }

    public void adminpageconnect(View v) {
        String email_input = admin_email.getText().toString();
        String password_input = admin_password.getText().toString();

        if (valid_admin(email_input, password_input)) {
            Intent p = new Intent(this, AdminPage.class);
            p.putExtra("Admin_email", email_input);
            startActivity(p);
        }

    }

    public boolean valid_admin(String email, String password){

        Cursor allData = mAdminDatabaseHelper.getData();

        while (allData.moveToNext()){

            if (email.equals(allData.getString(0)) &&
                    password.equals(allData.getString(1))){

                return true;

            }

        }

        return false;
    }

}
