package com.example.newfastfine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PaidFinesFragment extends Fragment {

    private ListView listViewPaidFines;
    private ArrayAdapter<String> paidFinesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paid_fines, container, false);
        listViewPaidFines = view.findViewById(R.id.listViewPaidFines);

        // Detailed data for fines
        ArrayList<String> paidFines = new ArrayList<>();
        paidFines.add("Plate: ABC123\nType: Car\nFine: Speeding\nLocation: Main St\nViolation: 2023-05-01 10:30 AM\nPaid: 2023-05-02\nAmount: ₹1000");
        paidFines.add("Plate: XYZ456\nType: Bike\nFine: Red Light\nLocation: 2nd Ave\nViolation: 2023-04-20 05:45 PM\nPaid: 2023-04-21\nAmount: ₹1500");
        paidFines.add("Plate: DEF789\nType: Truck\nFine: No Parking\nLocation: 3rd Blvd\nViolation: 2023-03-15 01:00 PM\nPaid: 2023-03-16\nAmount: ₹2000");
        paidFines.add("Plate: GHI012\nType: Van\nFine: One Way\nLocation: 4th St\nViolation: 2023-02-10 09:30 AM\nPaid: 2023-02-11\nAmount: ₹2500");
        paidFines.add("Plate: JKL345\nType: Car\nFine: Speeding\nLocation: 5th Ave\nViolation: 2023-01-05 07:30 PM\nPaid: 2023-01-06\nAmount: ₹3000");
        paidFines.add("Plate: MNO678\nType: Bike\nFine: Red Light\nLocation: 6th St\nViolation: 2022-12-25 12:00 PM\nPaid: 2022-12-26\nAmount: ₹3500");
        paidFines.add("Plate: PQR901\nType: Truck\nFine: No Parking\nLocation: 7th Blvd\nViolation: 2022-11-15 03:30 PM\nPaid: 2022-11-16\nAmount: ₹4000");
        paidFines.add("Plate: STU234\nType: Van\nFine: One Way\nLocation: 8th St\nViolation: 2022-10-20 11:00 AM\nPaid: 2022-10-21\nAmount: ₹4500");
        paidFines.add("Plate: VWX567\nType: Car\nFine: Speeding\nLocation: 9th Ave\nViolation: 2022-09-10 04:45 PM\nPaid: 2022-09-11\nAmount: ₹5000");
        paidFines.add("Plate: YZA890\nType: Bike\nFine: Red Light\nLocation: 10th St\nViolation: 2022-08-05 08:30 AM\nPaid: 2022-08-06\nAmount: ₹5500");

        // Setup adapter
        paidFinesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, paidFines);
        listViewPaidFines.setAdapter(paidFinesAdapter);

        return view;
    }
}
