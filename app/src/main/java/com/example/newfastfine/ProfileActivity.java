package com.example.newfastfine;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, emailTextView, mobileTextView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize views
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        mobileTextView = findViewById(R.id.mobileTextView);

        // Get current user
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Retrieve user details from Firestore
            db.collection("users").document(user.getUid()).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String fullName = document.getString("fullName");
                                String email = document.getString("email");
                                String mobileNumber = document.getString("mobileNumber");

                                // Set user details to TextViews
                                nameTextView.setText(fullName);
                                emailTextView.setText(email);
                                mobileTextView.setText(mobileNumber);
                            } else {
                                // Handle case where document does not exist
                            }
                        } else {
                            // Handle task failure
                        }
                    });
        }
    }
}
