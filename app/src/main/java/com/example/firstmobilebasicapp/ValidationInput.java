package com.example.firstmobilebasicapp;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ValidationInput {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6|7]\\d{8}$");

    public static boolean isValidName(TextInputLayout textInputLayout) {
        String input = textInputLayout.getEditText().getText().toString().trim();

        if (input.isEmpty()) {
            textInputLayout.setError("Le nom complet ne peut pas être vide");
            return false;
        } else if (!NAME_PATTERN.matcher(input).matches()) {
            textInputLayout.setError("Entrez un nom complet valide");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }

    }

    public static boolean isValidPhone(TextInputLayout textInputLayout) {
        String input = textInputLayout.getEditText().getText().toString().trim();

        if (input.isEmpty()) {
            textInputLayout.setError("Le téléphone ne peut pas être vide");
            return false;
        } else if (!PHONE_PATTERN.matcher(input).matches()) {
            textInputLayout.setError("Entrez un téléphone valide");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }

    }

    public static boolean isValidCountry(TextInputLayout textInputLayout) {
        String input = textInputLayout.getEditText().getText().toString();

        if (input.equals("Sélectionner votre pays")) {
            textInputLayout.setError("Le pays ne peut pas être vide");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }

    }
}
