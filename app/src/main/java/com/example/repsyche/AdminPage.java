package com.example.repsyche;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdminPage extends AppCompatActivity {

    UserDatabaseHelper mUserDatabaseHelper;
    EditText admin_email_ET;
    String adminEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        mUserDatabaseHelper = new UserDatabaseHelper(this);
        adminEmail = getIntent().getStringExtra("Admin_email");
        admin_email_ET = findViewById(R.id.Admin_email);
        admin_email_ET.setText("Admin: " + adminEmail);
    }

    public void accessTotalCredits(View v){

        Cursor allData = mUserDatabaseHelper.getData();

        double total_credit = 0;

        while (allData.moveToNext()){

            total_credit += allData.getDouble(5);

        }

        createAlertBox("Total User Credits", "Total: " + Double.toString(total_credit));

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
