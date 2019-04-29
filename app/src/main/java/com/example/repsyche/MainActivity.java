package com.example.repsyche;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BinDatabaseHelper mBinDatabaseHelper;
    private AdminDatabaseHelper mAdminDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinDatabaseHelper = new BinDatabaseHelper(this);
        mBinDatabaseHelper.addBin("Glass", 45,45);
        mBinDatabaseHelper.addBin("Paper", 55,55);
        mBinDatabaseHelper.addBin("Plastic", 80,80);

        mAdminDatabaseHelper = new AdminDatabaseHelper(this);
        mAdminDatabaseHelper.addAdmin("wb@zoomlionghana.com", "zoomzoom");
        mAdminDatabaseHelper.addAdmin("bigben@gmail.com", "biggybenny");
        mAdminDatabaseHelper.addAdmin("akornor@gmail.com", "akoako");
        mAdminDatabaseHelper.addAdmin("essential@gmail.com", "essence");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
        }
    }

    public void connector1(View v) {
        Intent i = new Intent(this, SignUpPage.class);
        startActivity(i);

    }

}
