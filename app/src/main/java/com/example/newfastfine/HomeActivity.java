package com.example.newfastfine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SearchView searchView;
    private TextView textViewPayment, textViewMyFines, textViewCalculateFines, textViewMapView;
    private TextSwitcher textViewDescription;
    private DrawerLayout drawerLayout;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
                Toast.makeText(HomeActivity.this, "Opening Payment Page...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, PaymentActivity.class));
            }
        });

        textViewMyFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Opening My Fines...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MyFinesActivity.class));
            }
        });

        textViewCalculateFines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Opening Fine Calculator", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, CalculatorActivity.class));
            }
        });

        textViewMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_register) {
            startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
        } else if (id == R.id.nav_vehicle) {
            // Handle vehicle activity
        } else if (id == R.id.nav_settings) {
            // Handle settings activity
        } else if (id == R.id.nav_logout) {
            showLogoutDialog();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure logging out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle logout
                        // For example
                        Toast.makeText(HomeActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        finish();                     }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, simply dismiss the dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
