package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: Force refresh of Glucose Graph

public class BloodGlucose extends Activity {
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_glucose);

        db = new DatabaseHandler(this);
        List databaseOutput = db.getGraphingData("week", "GLUCOSE");
        LineChart glucose_chart = (LineChart) findViewById(R.id.glucose_chart);
        ArrayList<Entry> glucoseData = (ArrayList<Entry>) databaseOutput.get(0);
        AdapterGraphs.initializeGraph(glucose_chart, 1, "Glucose", glucoseData);

        TimePicker glucose_timepicker = (TimePicker) findViewById(R.id.glucose_timepicker);
        glucose_timepicker.setIs24HourView(true);
    }

    public void inputValue (View view){
        EditText glucose_input_textbox = (EditText) findViewById(R.id.glucose_input_textbox);
        TimePicker glucose_timepicker = (TimePicker) findViewById(R.id.glucose_timepicker);
        ObjectFitness fitnessObject = new ObjectFitness();
        int glucoseInput;
        try {
            glucoseInput = Integer.parseInt(glucose_input_textbox.getText().toString());
        } catch (NumberFormatException e){
            glucose_input_textbox.setText("Invalid!");
            return;
        }
        Date newTime = new Date();
        newTime.setHours(glucose_timepicker.getCurrentHour());
        newTime.setMinutes(glucose_timepicker.getCurrentMinute());
        fitnessObject.setTime(newTime);
        fitnessObject.setValue(glucoseInput);
        db.addFitness("GLUCOSE", fitnessObject);
        glucose_input_textbox.setText("");
    }

    public void setTimeframeDay (View view){

    }
    public void setTimeframeWeek (View view){

    }
    public void setTimeframeMonth (View view){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood_glucose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
