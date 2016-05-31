package com.example.chrislxj.nhgpapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class FragmentMedicine extends android.support.v4.app.Fragment {

    public FragmentMedicine() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_two, container, false);

        ProgressBar bar1 = (ProgressBar) layout.findViewById(R.id.bar_1);
        bar1.setProgress(45);

        ProgressBar bar2 = (ProgressBar) layout.findViewById(R.id.bar_2);
        bar2.setProgress(70);

        return layout;
    }

}
