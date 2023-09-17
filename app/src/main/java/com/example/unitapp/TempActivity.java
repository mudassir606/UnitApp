package com.example.unitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    EditText editTextValue;
    TextView textViewResult;

    // Conversion factors for temperature units
    private static final double[] conversionFactors = {
            1.0,         // Celsius to Celsius
            33.8,        // Celsius to Fahrenheit
            274.15,      // Celsius to Kelvin
            -17.2222,    // Fahrenheit to Celsius
            1.0,         // Fahrenheit to Fahrenheit
            255.928,     // Fahrenheit to Kelvin
            -272.15,     // Kelvin to Celsius
            -457.87,     // Kelvin to Fahrenheit
            1.0          // Kelvin to Kelvin
    };

    private static final String[] unitNames = {
            "Celsius",
            "Fahrenheit",
            "Kelvin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editTextValue = findViewById(R.id.number1);
        textViewResult = findViewById(R.id.number2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.temp_units, R.layout.spinner1_text);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.temp_units, R.layout.spinner2_text);
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
        // Get the input value from the EditText
        String inputValueStr = editTextValue.getText().toString();

        if (inputValueStr.isEmpty()) {
            // Handle empty input
            textViewResult.setText("");
            return;
        }

        try {
            // Parse the input value to a double
            double inputValue = Double.parseDouble(inputValueStr);

            // Get the selected units from the spinners
            String initialUnit = spinner1.getSelectedItem().toString();
            String finalUnit = spinner2.getSelectedItem().toString();

            // Find the indices of the selected units
            int initialUnitIndex = findUnitIndex(initialUnit);
            int finalUnitIndex = findUnitIndex(finalUnit);

            // Perform the unit conversion based on your logic
            double result = 0.0;

            if (initialUnitIndex == finalUnitIndex) {
                // If the initial and final units are the same, the result is the same as the input
                result = inputValue;
            } else if (initialUnitIndex == 0 && finalUnitIndex == 1) {
                // Celsius to Fahrenheit
                result = (inputValue * 9.0 / 5.0) + 32.0;
            } else if (initialUnitIndex == 0 && finalUnitIndex == 2) {
                // Celsius to Kelvin
                result = inputValue + 273.15;
            } else if (initialUnitIndex == 1 && finalUnitIndex == 0) {
                // Fahrenheit to Celsius
                result = (inputValue - 32.0) * 5.0 / 9.0;
            } else if (initialUnitIndex == 1 && finalUnitIndex == 2) {
                // Fahrenheit to Kelvin
                result = (inputValue + 459.67) * 5.0 / 9.0;
            } else if (initialUnitIndex == 2 && finalUnitIndex == 0) {
                // Kelvin to Celsius
                result = inputValue - 273.15;
            } else if (initialUnitIndex == 2 && finalUnitIndex == 1) {
                // Kelvin to Fahrenheit
                result = (inputValue * 9.0 / 5.0) - 459.67;
            }

            // Display the result in the textViewResult
            textViewResult.setText(String.format("%.2f", result));
        } catch (NumberFormatException e) {
            // Handle invalid input format
            textViewResult.setText("Invalid input");
        }
    }

    private int findUnitIndex(String unitName) {
        for (int i = 0; i < unitNames.length; i++) {
            if (unitNames[i].equals(unitName)) {
                return i;
            }
        }
        return -1; // Unit not found
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        convertAndDisplayResult();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}