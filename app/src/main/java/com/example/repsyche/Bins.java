package com.example.repsyche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bins extends AppCompatActivity {

    private String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bins);
        user_email = getIntent().getStringExtra("User_email");

    }

    public void scannerconnect(View v) {
        Intent p = new Intent(this, scanner.class);
        p.putExtra("User_email", user_email);

        switch (v.getId()){

            case R.id.GlassBin_btn:
                p.putExtra("Bin", "Glass");
                break;

            case R.id.PaperBin_btn:
                p.putExtra("Bin", "Paper");
                break;

            case R.id.PlasticBin_btn:
                p.putExtra("Bin", "Plastic");
                break;

        }

        startActivity(p);

    }
}
