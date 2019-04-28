package com.example.repsyche;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }

    public void connector1(View v) {
        Intent i = new Intent(this, SignUpPage.class);
        startActivity(i);

    }

}
