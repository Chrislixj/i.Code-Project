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
import java.util.List;


public class Distance extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        TextView typical_average_distance_tv = (TextView) findViewById(R.id.typical_average_distance_tv);
        TextView your_average_distance_tv = (TextView) findViewById(R.id.your_average_distance_tv);
        TextView target_distance_tv = (TextView) findViewById(R.id.target_distance_tv);
        TextView current_distance_tv = (TextView) findViewById(R.id.current_distance_tv);

        DatabaseHandler db = new DatabaseHandler(this);
        List databaseOutput = db.getGraphingData("week", "DISTANCE");

        //TODO: Access data to set Target Distance
        typical_average_distance_tv.setText("3.2 km");
        your_average_distance_tv.setText(databaseOutput.get(2).toString());
        target_distance_tv.setText("XXX");
        current_distance_tv.setText(databaseOutput.get(1).toString());

        LineChart distance_chart = (LineChart) findViewById(R.id.distance_chart);
        ArrayList<Entry> distanceData = (ArrayList<Entry>) databaseOutput.get(0);
        AdapterGraphs.initializeGraph(distance_chart, 1, "Distance", distanceData);
    }

    public void setTimeframeDay (View view){

    }
    public void setTimeframeWeek (View view){

    }
    public void setTimeframeMonth (View view){

    }
}
