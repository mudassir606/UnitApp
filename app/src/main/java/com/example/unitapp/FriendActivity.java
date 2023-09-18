package com.example.unitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class FriendActivity extends AppCompatActivity {

     EditText editTextMyName;
     EditText editTextFriendName;
     TextView textViewResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        editTextMyName = findViewById(R.id.name1);
        editTextFriendName = findViewById(R.id.name2);
        textViewResult = findViewById(R.id.textresult);

        // Set up a TextWatcher for both EditText widgets
        editTextMyName.addTextChangedListener(textWatcher);
        editTextFriendName.addTextChangedListener(textWatcher);
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
            calculateFriendshipPercentage();
        }
    };

    private void calculateFriendshipPercentage() {
        String myName = editTextMyName.getText().toString().trim();
        String friendName = editTextFriendName.getText().toString().trim();

        // Calculate the friendship percentage (you can use any logic here)
        int percentage = calculatePercentage(myName, friendName);

        // Display the result
        textViewResult.setText("Friendship Percentage: " + percentage + "%");
    }

    private int calculatePercentage(String name1, String name2) {
        // Replace this with your own logic for calculating the percentage
        // For a simple example, you can count the number of common characters
        int commonChars = 0;

        for (char c : name1.toCharArray()) {
            if (name2.contains(String.valueOf(c))) {
                commonChars++;
            }
        }

        int maxLength = Math.max(name1.length(), name2.length());

        return (int) (((double) commonChars / maxLength) * 100);
    }
}