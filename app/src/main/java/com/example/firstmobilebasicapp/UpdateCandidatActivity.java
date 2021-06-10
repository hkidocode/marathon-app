package com.example.firstmobilebasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class UpdateCandidatActivity extends AppCompatActivity {

    int selectedDay, selectedMonth, selectedYear;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_candidat);

        TextInputLayout textInputLayoutName;
        TextInputLayout textInputLayoutPhone;
        TextInputLayout textInputLayoutCountry;
        TextView birthDateTextView;

        textInputLayoutName = findViewById(R.id.textInputLayoutNameUpdate);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhoneUpdate);
        textInputLayoutCountry = findViewById(R.id.textInputLayoutCountryUpdate);
        birthDateTextView = findViewById(R.id.textViewBirthDateUpdate);
        radioGroup = findViewById(R.id.radioGroup);
        Button birthDateBtn = findViewById(R.id.birthDateButton);
        Button updateButton = findViewById(R.id.updateButton);

        AutoCompleteTextView countryAutoCompleteTextView = findViewById(R.id.autoCompleteTextViewAdmin);
        String[] itemsDropDown = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, itemsDropDown);
        // to make a default value of countryAutoCompleteTextView the first item
        countryAutoCompleteTextView.setAdapter(arrayAdapter);


        int candidatId = getIntent().getIntExtra("candidatId", -1);
        if (candidatId != -1) {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), "marathon", 2);
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("select * from candidat where _id=?", new String[]{candidatId + ""});
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phone_number"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            long birthDate = (long) cursor.getDouble(cursor.getColumnIndex("birth_date"));
            textInputLayoutName.getEditText().setText(name);
            textInputLayoutPhone.getEditText().setText(phoneNumber.substring(4));
            textInputLayoutCountry.getEditText().setText(country);
            birthDateTextView.setText(birthDate + "");
            RadioButton manRadioButton = findViewById(R.id.manRadioButton);
            RadioButton womenRadioButton = findViewById(R.id.womenRadioButton);
            RadioButton[] radioButtons = {manRadioButton, womenRadioButton};
            for (RadioButton radioButton: radioButtons) {
                if (radioButton.getText().toString().equals(sex)) {
                    radioButton.setChecked(true);
                }
            }

            birthDateBtn.setOnClickListener(v -> {
                Calendar calendar = Calendar.getInstance();
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateCandidatActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                } else {
                    birthDateTextView.setText("Sélectionner votre date");
                    birthDateTextView.setTextColor(Color.RED);
                }
            });

            updateButton.setOnClickListener(v -> {
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

                long updateCandidat =   database.update("marathon", values, "_id=?", new String[]{candidatId + ""});
                if (updateCandidat == -1) {
                    Toast.makeText(getApplicationContext(), "Impossible update ce candidat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Update avec succés", Toast.LENGTH_SHORT).show();
                    Intent candidatsIntent = new Intent(getApplicationContext(), ShowCandidatActivity.class);
                    startActivity(candidatsIntent);
                }
            });


        }

    }
}