package com.example.firstmobilebasicapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// useful to communicate with database and manipulate queries
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        //when we change the version of database, it delete the last database and create a new database with the same name
        super(context, name, null, version);
    }

    // which tables we want to put in database or if we want to replace old database to new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table candidat" +
                "(_id integer primary key autoincrement not null," +
                "name text not null," +
                "phone_number text not null," +
                "country text not null," +
                "sex text not null," +
                "birth_date real not null)");
    }

    // what we want to do we the old version of database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists candidat");
        onCreate(db);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }
}
