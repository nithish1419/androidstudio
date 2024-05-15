package com.example.newfastfine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, confirmPasswordEditText, mobileNumberEditText;
    private TextView loginLinkTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        fullNameEditText = findViewById(R.id.fullName);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        mobileNumberEditText = findViewById(R.id.mobileNumber);
        loginLinkTextView = findViewById(R.id.loginLink);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set click listener for the login link
        loginLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        // Set click listener for the register button
        findViewById(R.id.registerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform registration logic here
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Retrieve user input from EditText fields
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String mobileNumber = mobileNumberEditText.getText().toString().trim();

        // Define password constraints
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

        // Define email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // Define mobile number pattern (allows only digits)
        String mobileNumberPattern = "[0-9]+";

        // Perform validation
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobileNumber.isEmpty()) {
            // Display error message if any field is empty
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(emailPattern)) {
            // Display error message if email format is incorrect
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        } else if (!mobileNumber.matches(mobileNumberPattern)) {
            // Display error message if mobile number contains non-numeric characters
            Toast.makeText(this, "Mobile number should contain only digits", Toast.LENGTH_SHORT).show();
        } else if (!password.matches(passwordPattern)) {
            // Display error message if password does not meet constraints
            Toast.makeText(this, "Password must contain 8 to 16 characters, at least one uppercase letter, one lowercase letter, one number, and one special character", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            // Display error message if passwords do not match
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            // Registration successful, perform further actions here (e.g., save to database)
            long result = databaseHelper.insertUser(fullName, email, password, mobileNumber);
            if (result != -1) {
                // Registration successful
                Toast.makeText(this, "Registration successful. Login now", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));

            } else {
                // Registration failed
                Toast.makeText(this, "Registration failed. Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
