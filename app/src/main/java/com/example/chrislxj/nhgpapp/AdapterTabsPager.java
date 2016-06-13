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
                return new FragmentMedicine();
            case 1:
                return new FragmentFitness();
            case 2:
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