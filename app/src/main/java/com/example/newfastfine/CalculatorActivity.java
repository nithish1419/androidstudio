package com.example.newfastfine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText editTextSpeedLimit, editTextActualSpeed;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize views
        editTextSpeedLimit = findViewById(R.id.editTextSpeedLimit);
        editTextActualSpeed = findViewById(R.id.editTextActualSpeed);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Set click listener for the calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calculate fine based on input values
                calculateFine();
            }
        });
    }

    private void calculateFine() {
        // Get input values
        int speedLimit = Integer.parseInt(editTextSpeedLimit.getText().toString());
        int actualSpeed = Integer.parseInt(editTextActualSpeed.getText().toString());

        // Calculate fine (example calculation)
        int fine = (actualSpeed - speedLimit) * 10; // Example: $10 per km/h over the limit

        // Display result
        textViewResult.setText("Fine: Rs." + fine);
    }
}
