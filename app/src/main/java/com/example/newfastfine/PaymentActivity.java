package com.example.newfastfine;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 1;
    private ListView listViewUnpaidFines;
    private ArrayAdapter<String> unpaidFinesAdapter;
    private ArrayList<String> unpaidFines;
    private ArrayList<String> selectedFines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        listViewUnpaidFines = findViewById(R.id.listViewUnpaidFines);
        Button buttonPaySelected = findViewById(R.id.buttonPaySelected);

        // Dummy data for unpaid fines
        unpaidFines = new ArrayList<>();
        unpaidFines.add("Plate: BCD234\nType: Car\nFine: Speeding\nLocation: 11th St\nViolation: 2023-05-05 10:00 AM\nAmount: ₹6000");
        unpaidFines.add("Plate: EFG345\nType: Bike\nFine: Red Light\nLocation: 12th Ave\nViolation: 2023-04-25 06:00 PM\nAmount: ₹6500");
        unpaidFines.add("Plate: HIJ456\nType: Truck\nFine: No Parking\nLocation: 13th Blvd\nViolation: 2023-03-20 02:30 PM\nAmount: ₹7000");
        unpaidFines.add("Plate: KLM567\nType: Van\nFine: One Way\nLocation: 14th St\nViolation: 2023-02-15 11:00 AM\nAmount: ₹7500");
        unpaidFines.add("Plate: NOP678\nType: Car\nFine: Speeding\nLocation: 15th Ave\nViolation: 2023-01-10 08:30 PM\nAmount: ₹8000");
        unpaidFines.add("Plate: QRS789\nType: Bike\nFine: Red Light\nLocation: 16th St\nViolation: 2022-12-30 01:00 PM\nAmount: ₹8500");
        unpaidFines.add("Plate: TUV890\nType: Truck\nFine: No Parking\nLocation: 17th Blvd\nViolation: 2022-11-20 04:30 PM\nAmount: ₹9000");
        unpaidFines.add("Plate: WXY901\nType: Van\nFine: One Way\nLocation: 18th St\nViolation: 2022-10-25 12:00 PM\nAmount: ₹9500");
        unpaidFines.add("Plate: ZAB012\nType: Car\nFine: Speeding\nLocation: 19th Ave\nViolation: 2022-09-15 05:00 PM\nAmount: ₹10000");
        unpaidFines.add("Plate: CDE123\nType: Bike\nFine: Red Light\nLocation: 20th St\nViolation: 2022-08-10 09:30 AM\nAmount: ₹10500");

        // Set up the adapter
        unpaidFinesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, unpaidFines);
        listViewUnpaidFines.setAdapter(unpaidFinesAdapter);
        listViewUnpaidFines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Initialize selected fines list
        selectedFines = new ArrayList<>();

        buttonPaySelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paySelectedFines();
            }
        });

        // Request SMS permission if not already granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    private void paySelectedFines() {
        selectedFines.clear();
        for (int i = 0; i < listViewUnpaidFines.getCount(); i++) {
            if (listViewUnpaidFines.isItemChecked(i)) {
                selectedFines.add(unpaidFines.get(i));
            }
        }

        if (selectedFines.isEmpty()) {
            Toast.makeText(this, "No fines selected to pay", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate payment process
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();

        // Send SMS with payment details
        sendPaymentSMS();
    }

    private void sendPaymentSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            StringBuilder smsContent = new StringBuilder("Payment Details:\n");
            for (String fine : selectedFines) {
                smsContent.append(fine).append("\n\n");
            }

            SmsManager smsManager = SmsManager.getDefault();
            String phoneNumber = "1234567890"; // Replace with actual registered phone number
            smsManager.sendTextMessage(phoneNumber, null, smsContent.toString(), null, null);

            Toast.makeText(this, "Payment details sent via SMS", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SMS permission is required to send payment details", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
