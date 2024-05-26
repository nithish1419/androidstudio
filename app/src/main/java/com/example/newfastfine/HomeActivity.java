package com.example.newfastfine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView textViewPayment, textViewMyFines, textViewCalculateFines, textViewMapView;
    private TextSwitcher textViewDescription;

    // Array of description texts
    private String[] descriptions = {
            "Welcome to FastFine! Your one-stop solution for paying fines quickly and easily.",
            "Find fines by entering the vehicle plate number above.",
            "Keep track of your fines, calculate penalties, and explore the map view."
    };

    // Index for current description
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        searchView = findViewById(R.id.searchView);
        textViewPayment = findViewById(R.id.textViewPayment);
        textViewMyFines = findViewById(R.id.textViewMyFines);
        textViewCalculateFines = findViewById(R.id.textViewCalculateFines);
        textViewMapView = findViewById(R.id.textViewMapView);
        textViewDescription = findViewById(R.id.textViewDescription);

        // Set click listeners for navigation options
        textViewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle payment option click
                Toast.makeText(HomeActivity.this, "Opening Payment Page...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, PaymentActivity.class));
            }
        });

        textViewMyFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle my fines option click
                Toast.makeText(HomeActivity.this, "Opening My Fines...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MyFinesActivity.class));
            }
        });

        textViewCalculateFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle calculate fines option click
                Toast.makeText(HomeActivity.this, "Opening Fine Calculator", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
            }
        });

        textViewMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle map view option click
                Toast.makeText(HomeActivity.this, "Opening Map...", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up TextSwitcher for description
        textViewDescription.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(HomeActivity.this);
                textView.setTextSize(35);
                textView.setTextColor(getResources().getColor(R.color.red));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                return textView;
            }
        });

        // Set initial text
        textViewDescription.setText(descriptions[currentIndex]);

        // Set animation for TextSwitcher
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        textViewDescription.setInAnimation(in);
        textViewDescription.setOutAnimation(out);

        // Start description animation
        startDescriptionAnimation();
    }

    // Method to start description animation
    private void startDescriptionAnimation() {
        textViewDescription.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % descriptions.length;
                textViewDescription.setText(descriptions[currentIndex]);
                startDescriptionAnimation();
            }
        }, 5000); // Change the delay time as needed
    }
}
