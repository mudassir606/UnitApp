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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CurrencyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1,spinner2;
    EditText editTextValue;
    TextView textViewResult;
    String fromCurrency, toCurrency;

     Map<String, Double> exchangeRates = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        try {
            spinner1 = findViewById(R.id.spinner1);
            spinner2 = findViewById(R.id.spinner2);
            editTextValue = findViewById(R.id.number1);
            textViewResult = findViewById(R.id.number2);

            exchangeRates.put("USD", 1.0);
            exchangeRates.put("EUR", 0.85);
            exchangeRates.put("GBP", 0.73);
            exchangeRates.put("JPY", 110.48);
            exchangeRates.put("CAD", 1.26);
            exchangeRates.put("PKR", 292.21);
            exchangeRates.put("INR", 83.32);

            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.currency_units, R.layout.spinner1_text);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);
            spinner1.setOnItemSelectedListener(this);


            ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.currency_units, R.layout.spinner2_text);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(this);

            // Set default currency selection
            spinner1.setSelection(adapter1.getPosition("USD"));
            spinner2.setSelection(adapter2.getPosition("EUR"));


            editTextValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // Do nothing
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    convertCurrency();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Do nothing
                }
            });
        }catch (Exception e){
            Toast.makeText(CurrencyActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void convertCurrency() {

        String amountStr = editTextValue.getText().toString();

        if (amountStr.isEmpty()) {

        } else {
            double amount = Double.parseDouble(amountStr);
            fromCurrency = spinner1.getSelectedItem().toString();
            toCurrency = spinner2.getSelectedItem().toString();

            double fromRate = getExchangeRate(fromCurrency);
            double toRate = getExchangeRate(toCurrency);

            double convertedAmount = (amount / fromRate) * toRate;

            textViewResult.setText(String.format("%.2f %s", convertedAmount, toCurrency));
        }
    }


    private double getExchangeRate(String currencyCode) {
        if (exchangeRates.containsKey(currencyCode)) {
            return exchangeRates.get(currencyCode);
        } else {
            return 1.0;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        convertCurrency();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}