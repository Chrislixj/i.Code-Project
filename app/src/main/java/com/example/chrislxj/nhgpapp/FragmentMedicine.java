package com.example.chrislxj.nhgpapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class FragmentMedicine extends android.support.v4.app.Fragment {

    public FragmentMedicine() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO: Extract medicine data to initialize Pills

        RelativeLayout layout = null;
        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences(getString(R.string.number_of_pills_file_key), Context.MODE_PRIVATE);
        int defaultNumberOfPills = 3;
        int numberOfPills = pref.getInt(getString(R.string.number_of_pills_file_key), defaultNumberOfPills);
        switch (numberOfPills){
            case 1:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_one, container, false);
                break;
            case 2:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_two, container, false);
                break;
            case 3:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_three, container, false);
                break;
            case 4:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_four, container, false);
                break;
            case 5:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_five, container, false);
        }

        //Set up Pills and TextViews
        if (numberOfPills >= 1) {
            ProgressBar bar1 = (ProgressBar) layout.findViewById(R.id.bar_1);
            bar1.setProgress(45);
        } if (numberOfPills >= 2) {
            ProgressBar bar2 = (ProgressBar) layout.findViewById(R.id.bar_2);
            bar2.setProgress(70);
        } if (numberOfPills >= 3) {
            ProgressBar bar3 = (ProgressBar) layout.findViewById(R.id.bar_3);
            bar3.setProgress(40);
        } if (numberOfPills >= 4) {
            ProgressBar bar4 = (ProgressBar) layout.findViewById(R.id.bar_4);
            bar4.setProgress(70);
        } if (numberOfPills >= 5) {
            ProgressBar bar5 = (ProgressBar) layout.findViewById(R.id.bar_5);
            bar5.setProgress(70);
        }
        return layout;
    }

}
