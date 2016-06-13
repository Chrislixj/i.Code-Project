package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: Force refresh of Pressure Graph

public class BloodPressure extends Activity {
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        db = new DatabaseHandler(this);
        List databaseOutput = db.getGraphingData("week", "PRESSURE");
        LineChart pressure_chart = (LineChart) findViewById(R.id.pressure_chart);
        ArrayList<Entry> pressureData = (ArrayList<Entry>) databaseOutput.get(0);
        AdapterGraphs.initializeGraph(pressure_chart, 1, "Pressure", pressureData);

        TimePicker glucose_timepicker = (TimePicker) findViewById(R.id.pressure_timepicker);
        glucose_timepicker.setIs24HourView(true);
    }

    public void inputValue (View view){
        EditText pressure_input_textbox = (EditText) findViewById(R.id.pressure_input_textbox);
        TimePicker pressure_timepicker = (TimePicker) findViewById(R.id.pressure_timepicker);
        ObjectFitness fitnessObject = new ObjectFitness();
        int pressureInput;
        try {
            pressureInput = Integer.parseInt(pressure_input_textbox.getText().toString());
        } catch (NumberFormatException e){
            pressure_input_textbox.setText("Invalid!");
            return;
        }
        Date newTime = new Date();
        newTime.setHours(pressure_timepicker.getCurrentHour());
        newTime.setMinutes(pressure_timepicker.getCurrentMinute());
        fitnessObject.setTime(newTime);
        fitnessObject.setValue(pressureInput);
        db.addFitness("GLUCOSE", fitnessObject);
        pressure_input_textbox.setText("");
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
        getMenuInflater().inflate(R.menu.menu_blood_pressure, menu);
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
