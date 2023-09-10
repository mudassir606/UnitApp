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

public class LengthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    EditText editTextValue;
    TextView textViewResult;

    private static final double[] conversionFactors = {
            1.0,          // Meter
            1000.0,       // Kilometer
            0.01,         // Centimeter
            0.001,        // Millimeter
            1e-6,         // Micrometer
            1e-9,         // Nanometer
            1609.34,      // Mile
            0.9144,       // Yard
            0.3048,       // Foot
            0.0254,       // Inch
            9.461e15      // Light Year
    };

    private static final String[] unitNames = {
            "Meter",
            "Kilometer",
            "Centimeter",
            "Millimeter",
            "Micrometer",
            "Nanometer",
            "Mile",
            "Yard",
            "Foot",
            "Inch",
            "Light Year"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

         spinner1 = findViewById(R.id.spinner1);
         spinner2 = findViewById(R.id.spinner2);
         editTextValue = findViewById(R.id.number1);
        textViewResult = findViewById(R.id.number2);

        ArrayAdapter <CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.length_units, R.layout.spinner1_text);
         adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner1.setAdapter(adapter1);
         spinner1.setOnItemSelectedListener(this);


        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.length_units, R.layout.spinner2_text);
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
            String initialUnit = spinner2.getSelectedItem().toString();
            String finalUnit = spinner1.getSelectedItem().toString();

            // Find the indices of the selected units
            int initialUnitIndex = findUnitIndex(initialUnit);
            int finalUnitIndex = findUnitIndex(finalUnit);

            // Perform the unit conversion based on your logic
            double result = inputValue * (conversionFactors[finalUnitIndex] / conversionFactors[initialUnitIndex]);

            // Display the result in the textViewResult
            textViewResult.setText(String.format("%.4f", result));
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