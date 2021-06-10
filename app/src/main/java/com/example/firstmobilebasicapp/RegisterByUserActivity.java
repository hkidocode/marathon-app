package com.example.firstmobilebasicapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterByUserActivity extends AppCompatActivity {

    private TextInputLayout nameTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout countryTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_user);

        nameTextInputLayout = findViewById(R.id.textInputLayout);
        phoneTextInputLayout = findViewById(R.id.textInputLayout2);
        countryTextInputLayout = findViewById(R.id.textInputLayout3);

        AutoCompleteTextView countryAutoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] itemsDropDown = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, itemsDropDown);
        // to make a default value of countryAutoCompleteTextView the first item
        countryAutoCompleteTextView.setAdapter(arrayAdapter);

        Button button = findViewById(R.id.addButton);
        CheckBox checkBox = findViewById(R.id.checkBox);
        button.setOnClickListener(v -> {

            if (!ValidationInput.isValidName(nameTextInputLayout) |
                    !ValidationInput.isValidPhone(phoneTextInputLayout) |
                    !ValidationInput.isValidCountry(countryTextInputLayout) |
                    !checkBox.isChecked()) {
                Toast.makeText(getApplicationContext(), "Accepter aux condition", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent successIntent = new Intent(getApplicationContext(), SuccessRegisterActivity.class);
            successIntent.putExtra("fullName", nameTextInputLayout.getEditText().getText().toString());
            startActivity(successIntent);

        });
    }



}