package com.example.unitapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimeZoneActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

     Spinner spinner1, spinner2;
     EditText editTextValue;
     TextView textViewResult;
     String sourceCountry ,targetCountry, initialTime, sourceTimeZoneId, targetTimeZoneId;
     Map<String, String> countryToTimeZone = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_zone);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editTextValue = findViewById(R.id.number1);
        textViewResult = findViewById(R.id.number2);




            countryToTimeZone.put("Pakistan", "Asia/Karachi");
            countryToTimeZone.put("Belgium", "Europe/Brussels");
            countryToTimeZone.put("USA", "America/New_York");




        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.time_zone_units, R.layout.spinner1_text);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.time_zone_units, R.layout.spinner2_text);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                convertAndDisplayResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });
    }

    private void convertAndDisplayResult() {
        sourceCountry = spinner2.getSelectedItem().toString();
        targetCountry = spinner1.getSelectedItem().toString();
        initialTime = editTextValue.getText().toString();

        // Check if the initial time is empty
        if (initialTime.isEmpty()) {
            textViewResult.setText("");
            return;
        }

        sourceTimeZoneId = countryToTimeZone.get(sourceCountry);
        targetTimeZoneId = countryToTimeZone.get(targetCountry);

        if (sourceTimeZoneId == null || targetTimeZoneId == null) {
            textViewResult.setText("Invalid country name");
            return;
        }

        try {
            // Parse the initial time as HH:mm format
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone(sourceTimeZoneId));
            Date initialDate = sdf.parse(initialTime);

            // Calculate the time difference between the source and target time zones
            long timeDifferenceMillis = TimeZone.getTimeZone(targetTimeZoneId).getRawOffset() - TimeZone.getTimeZone(sourceTimeZoneId).getRawOffset();

            // Convert the time by adding the time difference
            long convertedTimeMillis = initialDate.getTime() + timeDifferenceMillis;

            // Format the converted time as HH:mm
            Date convertedDate = new Date(convertedTimeMillis);
            String convertedTime = new SimpleDateFormat("HH:mm").format(convertedDate);

            // Display the converted time
            textViewResult.setText(convertedTime);
        } catch (Exception e) {
            // Handle invalid input or parsing errors
            textViewResult.setText("Invalid input");
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        convertAndDisplayResult();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }
}