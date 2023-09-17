package com.example.unitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cardlength, cardtime, cardweight, cardarea, cardcurrency, cardfriend, cardbmi, cardage, cardpressure, cardspeed, cardtemperature, cardvolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardarea = findViewById(R.id.card1);
        cardcurrency = findViewById(R.id.card2);
        cardfriend = findViewById(R.id.card3);
        cardbmi = findViewById(R.id.card4);
        cardlength = findViewById(R.id.card5);
        cardage = findViewById(R.id.card6);
        cardpressure = findViewById(R.id.card7);
        cardspeed = findViewById(R.id.card8);
        cardtemperature = findViewById(R.id.card9);
        cardtime = findViewById(R.id.card10);
        cardvolume = findViewById(R.id.card11);
        cardweight = findViewById(R.id.card12);

        cardarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardcurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardlength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the LengthActivity when the button is clicked
                startActivity(new Intent(MainActivity.this, LengthActivity.class));
            }
        });

        cardage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardpressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardtemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TempActivity.class);
                startActivity(intent);
            }
        });

        cardtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TimeActivity.class));
            }
        });

        cardvolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        cardweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WeightActivity.class));
            }
        });

    }
}