package com.example.firstmobilebasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ShowCandidatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_candidat);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), "marathon", 1);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from candidat", null);
        ListView listView = findViewById(R.id.candidatsListView);
        CandidatAdapter candidatAdapter = new CandidatAdapter(getApplicationContext(), cursor, 2);
        listView.setAdapter(candidatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor candidat = (Cursor) listView.getItemAtPosition(position);
                int candidatId = candidat.getInt(candidat.getColumnIndex("_id"));
                Intent registerIntent = new Intent(getApplicationContext(), UpdateCandidatActivity.class);
                registerIntent.putExtra("candidatId", candidatId);
                startActivity(registerIntent);
            }
        });
    }
}