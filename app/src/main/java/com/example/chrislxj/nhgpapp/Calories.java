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


public class Calories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);

        //TODO: Calculate calories stuff
        setContentView(R.layout.activity_calories);
        TextView calories_expended_tv = (TextView) findViewById(R.id.calories_expended_tv);
        TextView calories_consumed_tv = (TextView) findViewById(R.id.calories_consumed_tv);
        TextView net_calories_tv = (TextView) findViewById(R.id.net_calories_tv);
        TextView target_calories_tv = (TextView) findViewById(R.id.target_calories_tv);

        //TODO: Access data to set Calories values
        calories_expended_tv.setText("XXX");
        calories_consumed_tv.setText("XXX");
        net_calories_tv.setText("XXX");
        target_calories_tv.setText(db.getAccount(DatabaseHandler.KEY_ACCOUNT_TARGET_DISTANCE));

        //TODO: Access data to set Calories graph
        LineChart calories_chart = (LineChart) findViewById(R.id.calories_chart);
        ArrayList<Entry> caloriesData = new ArrayList<>();
        caloriesData.add(new Entry(0, 0));
        AdapterGraphs.initializeGraph(calories_chart, 2, "Calories", caloriesData);
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
        getMenuInflater().inflate(R.menu.menu_calories, menu);
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
