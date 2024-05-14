package com.example.newfastfine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private TextView textViewProfile, textViewMyFines, textViewCalculateFines, textViewMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        editTextSearch = findViewById(R.id.editTextSearch);
        textViewProfile = findViewById(R.id.textViewProfile);
        textViewMyFines = findViewById(R.id.textViewMyFines);
        textViewCalculateFines = findViewById(R.id.textViewCalculateFines);
        textViewMapView = findViewById(R.id.textViewMapView);

        // Set click listeners for navigation options
        textViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle profile option click
                Toast.makeText(HomeActivity.this, "Opening Profile...", Toast.LENGTH_SHORT).show();
            }
        });

        textViewMyFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle my fines option click
                Toast.makeText(HomeActivity.this, "My Fines...", Toast.LENGTH_SHORT).show();
            }
        });

        textViewCalculateFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle calculate fines option click
                Toast.makeText(HomeActivity.this, "Opening Fine Calculator", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
                finish();
            }
        });

        textViewMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle map view option click
                Toast.makeText(HomeActivity.this, "Opening Map...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
