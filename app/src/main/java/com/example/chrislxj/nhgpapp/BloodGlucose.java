package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

//TODO: Handle input of BloodGlucose values
//TODO: Access data to set BloodGlucose graph

public class BloodGlucose extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_glucose);
    }

    public void inputValue (View view){
        EditText time_input_textbox = (EditText) findViewById(R.id.time_input_textbox);
        EditText glucose_input_textbox = (EditText) findViewById(R.id.glucose_input_textbox);
        String timeInput = time_input_textbox.getText().toString();
        String glucoseInput = glucose_input_textbox.getText().toString();
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
