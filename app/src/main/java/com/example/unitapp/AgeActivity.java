package com.example.unitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeActivity extends AppCompatActivity {

     EditText editTextDateOfBirth;
     TextView textViewAgeResult;
    String dateOfBirthStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        editTextDateOfBirth = findViewById(R.id.name1);
        textViewAgeResult = findViewById(R.id.textresult);

        editTextDateOfBirth.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            calculateAge();
        }
    };

    public void calculateAge() {

        dateOfBirthStr = editTextDateOfBirth.getText().toString();

        if (!dateOfBirthStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = dateFormat.parse(dateOfBirthStr);

                Calendar today = Calendar.getInstance();
                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(dateOfBirth);

                int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

                // Check if the birthdate has occurred this year
                if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                    age--;
                }

                textViewAgeResult.setText("Your age is " + age + " years.");
            } catch (ParseException e) {
                textViewAgeResult.setText("Invalid date format");
            }
        } else {
            textViewAgeResult.setText("Please enter your date of birth.");
        }
    }
}