package com.example.firstmobilebasicapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Calendar;

public class CandidatAdapter extends CursorAdapter {


    public CandidatAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    // create view
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.candidats_list_item, parent, false);
    }

    // add data to view
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        TextView textViewCountry = view.findViewById(R.id.textViewCountry);
        TextView textViewNaissance = view.findViewById(R.id.textViewNaissance);

        String name = cursor.getString(cursor.getColumnIndex("name"));
        String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
        String country = cursor.getString(cursor.getColumnIndex("country"));
        long birthDate = (long) cursor.getDouble(cursor.getColumnIndex("birth_date"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH + 1);
        int day = calendar.get(Calendar.DAY_OF_MONTH + 1);
        textViewName.setText(name);
        textViewPhone.setText(phoneNumber);
        textViewCountry.setText(country);
        textViewNaissance.setText(day + "/" + month + "/" + year);
    }
}
