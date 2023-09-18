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

public class VolumeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    EditText editTextValue;
    TextView textViewResult;

    // Conversion factors for volume units (in cubic meters)
    private static final double[] conversionFactors = {
            1.0,          // Cubic Meter to Cubic Meter
            1.0e9,        // Cubic Meter to Cubic Kilometer
            1.0e6,        // Cubic Meter to Cubic Centimeter
            1.0e3,        // Cubic Meter to Cubic Millimeter
            1000.0,       // Cubic Meter to Liter
            1.0e6,        // Cubic Meter to Milliliter
            4.1682e-13,   // Cubic Meter to Cubic Mile
            1.30795,      // Cubic Meter to Cubic Yard
            35.3147,      // Cubic Meter to Cubic Foot
            61023.7       // Cubic Meter to Cubic Inch
    };

    private static final String[] unitNames = {
            "Cub Meter",
            "Cub Kilometer",
            "Cub Centimeter",
            "Cub Millimeter",
            "Liter",
            "Milliliter",
            "Cub Mile",
            "Cub Yard",
            "Cub Foot",
            "Cub Inch"
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editTextValue = findViewById(R.id.number1);
        textViewResult = findViewById(R.id.number2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.volume_units, R.layout.spinner1_text);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.volume_units, R.layout.spinner2_text);
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
            double result = inputValue * (conversionFactors[initialUnitIndex] / conversionFactors[finalUnitIndex]);

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