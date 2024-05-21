package com.example.newfastfine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FinesPagerAdapter extends FragmentStateAdapter {

    public FinesPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new PaidFinesFragment();
        } else {
            return new UnpaidFinesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
