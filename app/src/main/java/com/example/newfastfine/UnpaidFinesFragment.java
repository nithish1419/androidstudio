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

public class UnpaidFinesFragment extends Fragment {

    private ListView listViewUnpaidFines;
    private ArrayAdapter<String> unpaidFinesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unpaid_fines, container, false);
        listViewUnpaidFines = view.findViewById(R.id.listViewUnpaidFines);

        // Detailed data for fines
        ArrayList<String> unpaidFines = new ArrayList<>();
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

        // Setup adapter
        unpaidFinesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, unpaidFines);
        listViewUnpaidFines.setAdapter(unpaidFinesAdapter);

        return view;
    }
}
