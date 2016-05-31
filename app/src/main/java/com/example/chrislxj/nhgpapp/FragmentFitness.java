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

public class FragmentFitness extends android.support.v4.app.Fragment {

    public FragmentFitness() {
        // Required empty public constructor
    }
    private int mode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO: Extract fitness data to initialize Graphs

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
        //-

        //Set up Graphs and TextViews
        TextView calories_tv = (TextView) layout.findViewById(R.id.calories_tv);
        TextView steps_tv = (TextView) layout.findViewById(R.id.steps_tv);
        TextView distance_tv = (TextView) layout.findViewById(R.id.distance_tv);
        TextView blood_tv = (TextView) layout.findViewById(R.id.blood_tv);
        TextView blood_label = (TextView) layout.findViewById(R.id.blood_label);
        if (numberOfGraphs == 3) {
            //three fitness
            LineChart calories_chart = (LineChart) layout.findViewById(R.id.calories_chart);
            LineChart steps_chart = (LineChart) layout.findViewById(R.id.steps_chart);
            LineChart distance_chart = (LineChart) layout.findViewById(R.id.distance_chart);

            ArrayList<Entry> caloriesData = new ArrayList<>();
            caloriesData.add(new Entry(0, 0));
            ArrayList<Entry> stepsData = new ArrayList<>();
            stepsData.add(new Entry(0, 0));
            ArrayList<Entry> distanceData = new ArrayList<>();
            distanceData.add(new Entry(0, 0));

            AdapterGraphs.initializeGraph(calories_chart, 2, "Calories", caloriesData);
            AdapterGraphs.initializeGraph(steps_chart, 2, "Steps", stepsData);
            AdapterGraphs.initializeGraph(distance_chart, 2, "Distance", distanceData);

            calories_tv.setText("XXX");
            steps_tv.setText("XXX");
            distance_tv.setText("XXX");
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
        /*
        //Retrieve charts
        LineChart calories_chart = (LineChart) layout.findViewById(R.id.calories_chart);
        LineChart steps_chart = (LineChart) layout.findViewById(R.id.steps_chart);
        LineChart distance_chart = (LineChart) layout.findViewById(R.id.distance_chart);

        //Graph labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Monday");
        labels.add("Tuesday");
        labels.add("Wednesday");
        labels.add("Thursday");
        labels.add("Friday");
        labels.add("Saturday");
        labels.add("Sunday");

        //Extract and graph data (calories)
        ArrayList<Entry> caloriesData = new ArrayList<>();
        caloriesData.add(new Entry(0, 0));
        //-

        //Extract and graph data (steps)
        ArrayList<Entry> stepsData = new ArrayList<>();
        stepsData.add(new Entry(0, 0));
        //-

        //Extract and graph data (distance)
        ArrayList<Entry> distanceData = new ArrayList<>();
        distanceData.add(new Entry(0, 0));
        //-

        //draw chart
        LineDataSet caloriesDataset = new LineDataSet(caloriesData, "Calories");
        LineData caloriesLineData = new LineData (labels, caloriesDataset);
        calories_chart.setData(caloriesLineData);

        LineDataSet stepsDataset = new LineDataSet(stepsData, "Steps");
        LineData stepsLineData = new LineData (labels, stepsDataset);
        steps_chart.setData(stepsLineData);

        LineDataSet distanceDataset = new LineDataSet(distanceData, "Distance");
        LineData distanceLineData = new LineData (labels, distanceDataset);
        distance_chart.setData(distanceLineData);

        //use invalidate() and update() if updating
        */
        return layout;
    }

}
