package com.example.repsyche;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText firstname, lasttname, username, email, password, confirm_password;

    UserDatabaseHelper mUserDatabaseHelper;

    static {
        System.loadLibrary("DatabaseOperations_native");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserDatabaseHelper = new UserDatabaseHelper(this);
        setContentView(R.layout.activity_sign_up_page);
        firstname = (EditText) findViewById(R.id.FirstName);
        lasttname = (EditText) findViewById(R.id.LastName);
        username = (EditText) findViewById(R.id.SignUp_Username);
        email = (EditText) findViewById(R.id.SignUp_email);
        password = (EditText) findViewById(R.id.SignUp_password);
        confirm_password = (EditText) findViewById(R.id.SignUp_ConfirmPassword);

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
        String fname_content = firstname.getText().toString();
        String lname_content = lasttname.getText().toString();
        String username_content = username.getText().toString();
        String email_content = email.getText().toString();
        String password_content = password.getText().toString();
        String confirmpassword_content = confirm_password.getText().toString();

        String user_details = username_content + " | " + email_content + " | " +  fname_content + " | " + lname_content + " | " +
                 password_content + " | " + "0";

        if (fname_content.equals("") || lname_content.equals("") || username_content.equals("") ||
                email_content.equals("") || password_content.equals("") || confirmpassword_content.equals("")){
            createAlertBox("Empty Field(s)", "Fill all fields and try again!");
            return;
        }

        boolean new_user = new_user(username_content, email_content);

        //If user info does not exist, if password does not contain special delimiter used
        //for storing user details, and if password and confirm password match.
        if (new_user && !password_content.contains(" | ") && password_content.equals(confirmpassword_content)){

            System.out.println(user_details);
            boolean signUp_status = mUserDatabaseHelper.addUser(user_details);

            if (signUp_status){ // If signUp is successful.

                System.out.println("Succesful insertion: "+user_details);
                Intent n = new Intent(this, LoginPage.class);
                startActivity(n);

            }
            else{
                createAlertBox("Sign-up Error", "Sign-up Unsuccessful. Try Again!");
            }
        }
        else if (!new_user(username_content, email_content)){ //Show alert box if username or email exist.
            createAlertBox("User Exists.", "The username or email provided is already in use.");
        }
        else if (!password_content.equals(confirmpassword_content)){ //Show alert box if password and confirm password do not match.
            createAlertBox("Password Confirmation Error.", "Password and confirmed password do not match.");
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

    public boolean new_user(String username, String email){

        Cursor allData = mUserDatabaseHelper.getData();

        while (allData.moveToNext()){

            if (username.equals(allData.getString(0)) ||
            email.equals(allData.getString(1))){

                return false;
            }

        }

        return true;

    }

}

