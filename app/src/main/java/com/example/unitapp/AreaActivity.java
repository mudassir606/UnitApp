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

public class AreaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    EditText editTextValue;
    TextView textViewResult;

    // Conversion factors for area units (in square meters)
    private static final double[] conversionFactors = {
            1.0,          // Square Meter to Square Meter
            1.0e-6,       // Square Meter to Square Kilometer
            1.0e4,        // Square Meter to Square Centimeter
            1.0e6,        // Square Meter to Square Millimeter
            1.0e12,       // Square Meter to Square Micrometer
            0.0001,       // Square Meter to Hectare
            3.861e-7,     // Square Meter to Square Mile
            1.19599,      // Square Meter to Square Yard
            10.7639,      // Square Meter to Square Foot
            1550.0,       // Square Meter to Square Inch
            0.000247105   // Square Meter to Acre
    };

    private static final String[] unitNames = {
            "Sqr Meter",
            "Sqr Kilometer",
            "Sqr Centimeter",
            "Sqr Millimeter",
            "Sqr Micrometer",
            "Hectare",
            "Sqr Mile",
            "Sqr Yard",
            "Sqr Foot",
            "Sqr Inch",
            "Acre"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        editTextValue = findViewById(R.id.number1);
        textViewResult = findViewById(R.id.number2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.area_units, R.layout.spinner1_text);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.area_units, R.layout.spinner2_text);
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