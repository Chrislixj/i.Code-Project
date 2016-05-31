package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;


public class Distance extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        TextView typical_average_distance_tv = (TextView) findViewById(R.id.typical_average_distance_tv);
        TextView your_average_distance_tv = (TextView) findViewById(R.id.your_average_distance_tv);
        TextView target_distance_tv = (TextView) findViewById(R.id.target_distance_tv);
        TextView current_distance_tv = (TextView) findViewById(R.id.current_distance_tv);

        //TODO: Access data to set Distance values
        typical_average_distance_tv.setText("XXX");
        your_average_distance_tv.setText("XXX");
        target_distance_tv.setText("XXX");
        current_distance_tv.setText("XXX");

        //TODO: Access data to set Distance graph
        LineChart distance_chart = (LineChart) findViewById(R.id.distance_chart);
        ArrayList<Entry> distanceData = new ArrayList<>();
        distanceData.add(new Entry(0, 0));
        AdapterGraphs.initializeGraph(distance_chart, 2, "Distance", distanceData);
    }

    public void setTimeframeDay (View view){

    }
    public void setTimeframeWeek (View view){

    }
    public void setTimeframeMonth (View view){

    }
}
