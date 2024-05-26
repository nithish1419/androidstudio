package com.example.newfastfine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, confirmPasswordEditText, mobileNumberEditText;
    private TextView loginLinkTextView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize views
        fullNameEditText = findViewById(R.id.fullName);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        mobileNumberEditText = findViewById(R.id.mobileNumber);
        loginLinkTextView = findViewById(R.id.loginLink);

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
            // Registration successful, perform further actions here (e.g., save to Firebase)
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Registration successful
                                FirebaseUser user = mAuth.getCurrentUser();
                                saveUserDetails(user.getUid(), fullName, email, mobileNumber);
                            } else {
                                // If registration fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Registration failed. Please try again: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void saveUserDetails(String userId, String fullName, String email, String mobileNumber) {
        // Create a new user with the provided details
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", fullName);
        user.put("email", email);
        user.put("mobileNumber", mobileNumber);

        // Save the user details in Firestore
        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    // User details saved successfully
                    Toast.makeText(RegisterActivity.this, "User details saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                })
                .addOnFailureListener(e -> {
                    // Failed to save user details
                    Toast.makeText(RegisterActivity.this, "Failed to save user details", Toast.LENGTH_SHORT).show();
                });
    }
}
