package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class AppointmentTime extends Activity {
    public String appointmentTimeTag = "NHGPAPP_APPOINTMENT_TIME";
    private RadioGroup radio_button_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_time);
        Intent intent = getIntent();
        String appointmentType = intent.getStringExtra(AppointmentType.appointmentTypeTag);
        TextView appointment_question_tv = (TextView) findViewById(R.id.appointment_question_tv);
        radio_button_group = (RadioGroup) findViewById(R.id.radio_button_group);
        RadioButton radioButtonOne;
        RadioButton radioButtonTwo;
        RadioButton radioButtonThree;
        switch (appointmentType){
            case "Consultation":
                appointment_question_tv.setText("Do you have a fever, flu, sore throat, or runnny nose?");
                break;
            case "Physiotherapy":
                appointment_question_tv.setText("Do you have a referral letter from a general practitioner?");
                break;
            case "Nursing":
                appointment_question_tv.setText("Have you confirmed your pregnancy?");
                break;
            case "Dental":
                appointment_question_tv.setText("What dental service do you require?");
                break;
        }
        Log.d("debug","appointmentType is "+appointmentType);
        if (appointmentType.equals("Dental")){
            Log.d("debug","Add radioBtn3 block");
            radioButtonOne = new RadioButton(this);
            radioButtonOne.setLayoutParams(new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButtonOne.setText("Scaling/Polishing");
            radioButtonTwo = new RadioButton(this);
            radioButtonTwo.setLayoutParams(new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButtonTwo.setText("Filling/Extract");
            radioButtonThree = new RadioButton(this);
            radioButtonThree.setLayoutParams(new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButtonThree.setText("Other Services");
            radio_button_group.setWeightSum(3);
            radio_button_group.addView(radioButtonOne);
            radio_button_group.addView(radioButtonTwo);
            radio_button_group.addView(radioButtonThree);
            Log.d("debug", String.valueOf(radio_button_group.getChildCount())+" radioButtons");
        } else {
            Log.d("debug","Add radioBtn2 block");
            radioButtonOne = new RadioButton(this);
            radioButtonOne.setLayoutParams(new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButtonOne.setText("Yes");
            radioButtonTwo = new RadioButton(this);
            radioButtonTwo.setLayoutParams(new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            radioButtonTwo.setText("No");
            radio_button_group.setWeightSum(2);
            radio_button_group.addView(radioButtonOne);
            radio_button_group.addView(radioButtonTwo);
            Log.d("debug", String.valueOf(radio_button_group.getChildCount()) + " radioButtons");
        }
        //TODO: Load available appointments from server
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment_time, menu);
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
    public void bookAppointment (View view){
        int id = radio_button_group.getCheckedRadioButtonId();
        Intent intent = new Intent(view.getContext(), AppointmentDetails.class);
        //intent.putExtra(appointmentTimeTag, );
        //intent.putExtra();
        startActivity(intent);
        //TODO: Submit appointment time to server
    }
    public void cancelBooking (View view){

    }
}
