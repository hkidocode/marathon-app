package com.example.firstmobilebasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterByAdminActivity extends AppCompatActivity {

    int selectedDay, selectedMonth, selectedYear;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutCountry;
    private RadioGroup radioGroup;
    private TextView birthDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_admin);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
        textInputLayoutCountry = findViewById(R.id.textInputLayoutCountry);
        radioGroup = findViewById(R.id.radioGroup);
        Button birthDateBtn = findViewById(R.id.birthDateButton);
        birthDateTextView = findViewById(R.id.textViewBirthDate);
        Button listButton = findViewById(R.id.listButton);


        AutoCompleteTextView countryAutoCompleteTextView = findViewById(R.id.autoCompleteTextViewAdmin);
        String[] itemsDropDown = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, itemsDropDown);
        // to make a default value of countryAutoCompleteTextView the first item
        countryAutoCompleteTextView.setAdapter(arrayAdapter);

        birthDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterByAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    selectedDay = dayOfMonth;
                    selectedMonth = month;
                    selectedYear = year;

                }
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.show();
            String date = selectedDay + "/" + selectedMonth + "/" + selectedYear;
            if (!date.equals("0/0/0")) {
                birthDateTextView.setText(date);
                birthDateTextView.setTextColor(Color.GRAY);
            } else {
                birthDateTextView.setText("Sélectionner votre date");
                birthDateTextView.setTextColor(Color.RED);
            }
        });


        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), "marathon", 1);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            if (!ValidationInput.isValidName(textInputLayoutName) |
                    !ValidationInput.isValidPhone(textInputLayoutPhone) |
                    !ValidationInput.isValidCountry(textInputLayoutCountry) |
                    radioGroup.getCheckedRadioButtonId() == -1
            ) {
                return;
            }
            ContentValues values = new ContentValues();
            values.put("name", textInputLayoutName.getEditText().getText().toString().trim());
            values.put("phone_number", "+212" + textInputLayoutPhone.getEditText().getText().toString());
            values.put("country", textInputLayoutCountry.getEditText().getText().toString());
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            values.put("sex", radioButton.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, selectedDay + 1);
            calendar.set(Calendar.MONTH, selectedMonth + 1);
            calendar.set(Calendar.YEAR, selectedYear);
            values.put("birth_date", calendar.getTimeInMillis());

            long addCandidat = database.insert("candidat", null, values);
            if (addCandidat == -1) {
                Toast.makeText(getApplicationContext(), "Impossible d'insérer ce candidat", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ajouté avec succés", Toast.LENGTH_SHORT).show();
            }
        });

        listButton.setOnClickListener(v -> {
            Intent listIntent = new Intent(getApplicationContext(), ShowCandidatActivity.class);
            startActivity(listIntent);
        });

//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
//            String country = cursor.getString(cursor.getColumnIndex("country"));
//            String sex = cursor.getString(cursor.getColumnIndex("sex"));
//            long birthDate = (long) cursor.getDouble(cursor.getColumnIndex("birth_date"));
//            Candidat candidat = new Candidat(name, phoneNumber, country, sex, birthDate);
//            candidats.add(candidat);
//            cursor.moveToNext();
//        }
//        cursor.close();

    }


}
