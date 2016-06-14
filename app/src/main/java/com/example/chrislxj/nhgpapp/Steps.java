package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;


public class Steps extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        TextView typical_average_steps_tv = (TextView) findViewById(R.id.typical_average_steps_tv);
        TextView your_average_steps_tv = (TextView) findViewById(R.id.your_average_steps_tv);
        TextView target_steps_tv = (TextView) findViewById(R.id.target_steps_tv);
        TextView current_steps_tv = (TextView) findViewById(R.id.current_steps_tv);

        DatabaseHandler db = new DatabaseHandler(this);
        List databaseOutput = db.getGraphingData("week", "STEPS");

        typical_average_steps_tv.setText("5,117 steps");
        your_average_steps_tv.setText(databaseOutput.get(2).toString());
        target_steps_tv.setText(db.getAccount(DatabaseHandler.KEY_ACCOUNT_TARGET_STEPS)+" steps");
        current_steps_tv.setText(databaseOutput.get(1).toString());
        LineChart steps_chart = (LineChart) findViewById(R.id.steps_chart);
        ArrayList<Entry> stepsData = (ArrayList<Entry>) databaseOutput.get(0);
        AdapterGraphs.initializeGraph(steps_chart, 1, "Steps", stepsData);
    }

    public void setTimeframeDay (View view){

    }

    public void setTimeframeWeek (View view){

    }

    public void setTimeframeMonth (View view){

    }
}
