package com.example.chrislxj.nhgpapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterTabsPager extends FragmentPagerAdapter {

    public AdapterTabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new FragmentMedicine();
            case 1:
                // Games fragment activity
                return new FragmentFitness();
            case 2:
                // Movies fragment activity
                return new FragmentAppointment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}