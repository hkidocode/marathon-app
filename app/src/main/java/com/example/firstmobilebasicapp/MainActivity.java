package com.example.firstmobilebasicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button userBtn = findViewById(R.id.welcomeButtonUser);
        Button adminBtn = findViewById(R.id.welcomeButtonAdmin);

        userBtn.setOnClickListener(v -> {
            Intent registerIntent = new Intent(getApplicationContext(), RegisterByUserActivity.class);
            startActivity(registerIntent);
        });

        adminBtn.setOnClickListener(v -> {
            Intent registerIntent = new Intent(getApplicationContext(), RegisterByAdminActivity.class);
            startActivity(registerIntent);
        });
    }

}