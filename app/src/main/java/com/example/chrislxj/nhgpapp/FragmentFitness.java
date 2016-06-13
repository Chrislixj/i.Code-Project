package com.example.chrislxj.nhgpapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FragmentFitness extends android.support.v4.app.Fragment {

    public FragmentFitness() {
        // Required empty public constructor
    }
    private int mode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout = null;
        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences(getString(R.string.number_of_graphs_file_key), Context.MODE_PRIVATE);
        int defaultNumberOfGraphs = 3;
        int numberOfGraphs = pref.getInt(getString(R.string.number_of_graphs_file_key), defaultNumberOfGraphs);
        switch (numberOfGraphs){
            case 3:
                layout = (LinearLayout) inflater.inflate(R.layout.fragment_fitness_three, container, false);
                break;
            case 4:
                layout = (LinearLayout) inflater.inflate(R.layout.fragment_fitness_four, container, false);
                break;
            case 5:
                layout = (LinearLayout) inflater.inflate(R.layout.fragment_fitness_four, container, false);
                break;
            case 6:
                layout = (LinearLayout) inflater.inflate(R.layout.fragment_fitness_five, container, false);
                break;
        }

        //Set up Graphs and TextViews
        TextView calories_tv = (TextView) layout.findViewById(R.id.calories_tv);
        TextView steps_tv = (TextView) layout.findViewById(R.id.steps_tv);
        TextView distance_tv = (TextView) layout.findViewById(R.id.distance_tv);
        TextView blood_tv = (TextView) layout.findViewById(R.id.blood_tv);
        TextView blood_label = (TextView) layout.findViewById(R.id.blood_label);

        DatabaseHandler db = new DatabaseHandler(getContext());
        List caloriesDatabaseOutput = db.getGraphingData("week", "CALORIES_EXPENDED");
        List stepsDatabaseOutput = db.getGraphingData("week", "STEPS");
        List distanceDatabaseOutput = db.getGraphingData("week", "DISTANCE");

        if (numberOfGraphs == 3) {
            //three fitness
            LineChart calories_chart = (LineChart) layout.findViewById(R.id.calories_chart);
            LineChart steps_chart = (LineChart) layout.findViewById(R.id.steps_chart);
            LineChart distance_chart = (LineChart) layout.findViewById(R.id.distance_chart);

            ArrayList<Entry> caloriesData = (ArrayList<Entry>) caloriesDatabaseOutput.get(0);
            ArrayList<Entry> stepsData = (ArrayList<Entry>) stepsDatabaseOutput.get(0);
            ArrayList<Entry> distanceData = (ArrayList<Entry>) distanceDatabaseOutput.get(0);

            AdapterGraphs.initializeGraph(calories_chart, 1, "Calories", caloriesData);
            AdapterGraphs.initializeGraph(steps_chart, 1, "Steps", stepsData);
            AdapterGraphs.initializeGraph(distance_chart, 1, "Distance", distanceData);

            calories_tv.setText(caloriesDatabaseOutput.get(1).toString());
            steps_tv.setText(stepsDatabaseOutput.get(1).toString());
            distance_tv.setText(distanceDatabaseOutput.get(1).toString());
        } else if (numberOfGraphs == 4) {
            //four fitness (glucose)
            LineChart glucose_chart = (LineChart) layout.findViewById(R.id.blood_chart);
            ArrayList<Entry> glucoseData = new ArrayList<>();
            glucoseData.add(new Entry(0, 0));
            AdapterGraphs.initializeGraph(glucose_chart, 2, "Glucose", glucoseData);
            calories_tv.setText("XXX");
            steps_tv.setText("XXX");
            distance_tv.setText("XXX");
            blood_tv.setText("XXX");
            blood_label.setText("Glucose");
        } else if (numberOfGraphs == 5){
            //four fitness (pressure)
            LineChart pressure_chart = (LineChart) layout.findViewById(R.id.blood_chart);
            ArrayList<Entry> pressureData = new ArrayList<>();
            pressureData.add(new Entry(0, 0));
            AdapterGraphs.initializeGraph(pressure_chart, 2, "Glucose", pressureData);
            calories_tv.setText("XXX");
            steps_tv.setText("XXX");
            distance_tv.setText("XXX");
            blood_tv.setText("XXX");
            blood_label.setText("Pressure");
        }
        return layout;
    }

}
