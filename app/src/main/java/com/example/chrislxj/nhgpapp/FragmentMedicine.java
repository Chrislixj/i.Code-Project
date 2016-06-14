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
import android.widget.TextView;

import java.util.List;

public class FragmentMedicine extends android.support.v4.app.Fragment {

    public FragmentMedicine() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RelativeLayout layout = null;
        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences(getString(R.string.number_of_pills_file_key), Context.MODE_PRIVATE);
        int defaultNumberOfPills = 0;
        int numberOfPills = pref.getInt(getString(R.string.number_of_pills_file_key), defaultNumberOfPills);
        switch (numberOfPills){
            case 0:
                layout = (RelativeLayout) inflater.inflate(R.layout.fragment_medicine_zero, container, false);
                break;
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
        DatabaseHandler db = new DatabaseHandler(getContext());
        List<ObjectMedicine> medicineList = db.getAllMedicine();
        if (medicineList.isEmpty()){
            return layout;
        }

        if (numberOfPills >= 1) {
            ProgressBar bar1 = (ProgressBar) layout.findViewById(R.id.bar_1);
            bar1.setProgress((medicineList.get(0).getQuantity()/medicineList.get(0).getProgress()*100));
            bar1.setTag(medicineList.get(0).getID());
            TextView tv1 = (TextView) layout.findViewById(R.id.bar_1_tv);
            tv1.setText(medicineList.get(0).getName());
        } if (numberOfPills >= 2) {
            ProgressBar bar2 = (ProgressBar) layout.findViewById(R.id.bar_2);
            bar2.setProgress((medicineList.get(1).getQuantity()/medicineList.get(1).getProgress()*100));
            bar2.setTag(medicineList.get(1).getID());
            TextView tv2 = (TextView) layout.findViewById(R.id.bar_2_tv);
            tv2.setText(medicineList.get(1).getName());
        } if (numberOfPills >= 3) {
            ProgressBar bar3 = (ProgressBar) layout.findViewById(R.id.bar_3);
            bar3.setProgress((medicineList.get(2).getQuantity()/medicineList.get(2).getProgress()*100));
            bar3.setTag(medicineList.get(2).getID());
            TextView tv3 = (TextView) layout.findViewById(R.id.bar_3_tv);
            tv3.setText(medicineList.get(2).getName());
        } if (numberOfPills >= 4) {
            ProgressBar bar4 = (ProgressBar) layout.findViewById(R.id.bar_4);
            bar4.setProgress((medicineList.get(3).getQuantity()/medicineList.get(3).getProgress()*100));
            bar4.setTag(medicineList.get(3).getID());
            TextView tv4 = (TextView) layout.findViewById(R.id.bar_4_tv);
            tv4.setText(medicineList.get(3).getName());
        } if (numberOfPills >= 5) {
            ProgressBar bar5 = (ProgressBar) layout.findViewById(R.id.bar_5);
            bar5.setProgress((medicineList.get(4).getQuantity()/medicineList.get(4).getProgress()*100));
            bar5.setTag(medicineList.get(4).getID());
            TextView tv5 = (TextView) layout.findViewById(R.id.bar_5_tv);
            tv5.setText(medicineList.get(4).getName());
        }
        return layout;
    }

}
