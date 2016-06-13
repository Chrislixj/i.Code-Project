package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Pill extends Activity {
    private DatabaseHandler db;
    private ObjectMedicine medicineObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        Intent intent = getIntent();
        int medicineId = Integer.parseInt(intent.getStringExtra(Main.medicineIdTag));
        db = new DatabaseHandler(this);
        medicineObject = db.getMedicine(medicineId);
        List <Date> medicineTimes = medicineObject.getDatetimeTimes();
        ProgressBar pill_bar = (ProgressBar) findViewById(R.id.pill_bar);
        TextView pill_instructions_tv = (TextView) findViewById(R.id.pill_instructions_tv);
        TextView pills_left_tv = (TextView) findViewById(R.id.pills_left_tv);
        pill_bar.setProgress(medicineObject.getProgress());
        pill_instructions_tv.setText("Instructions\n" + medicineObject.getInstruction());
        pills_left_tv.setText("Pills Left: " + medicineObject.getQuantity());

        TimePicker alarm_timepicker = (TimePicker) findViewById(R.id.alarm_timepicker);
        alarm_timepicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= 23 ) {
            alarm_timepicker.setHour(medicineTimes.get(0).getHours());
            alarm_timepicker.setMinute(medicineTimes.get(0).getMinutes());
        }
        else {
            alarm_timepicker.setCurrentHour(medicineTimes.get(0).getHours());
            alarm_timepicker.setCurrentMinute(medicineTimes.get(0).getMinutes());
        }
        LinearLayout medicine_timings_linearlayout = (LinearLayout) findViewById(R.id.medicine_timings_linearlayout);
        medicine_timings_linearlayout.setWeightSum(medicineTimes.size());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("H:mm");

        if (medicineTimes.size() >= 1) {
            Button timingButtonOne = new Button(this);
            timingButtonOne.setText(dateFormatter.format(medicineTimes.get(0)));
            timingButtonOne.setTag(0);
            timingButtonOne.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            timingButtonOne.setOnClickListener(timesButtonClickListener(timingButtonOne));
            medicine_timings_linearlayout.addView(timingButtonOne);
        }
        if (medicineTimes.size() >= 2) {
            Button timingButtonTwo = new Button(this);
            timingButtonTwo.setText(dateFormatter.format(medicineTimes.get(1)));
            timingButtonTwo.setTag(1);
            timingButtonTwo.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            timingButtonTwo.setOnClickListener(timesButtonClickListener(timingButtonTwo));
            medicine_timings_linearlayout.addView(timingButtonTwo);
        }
        if (medicineTimes.size() >= 3) {
            Button timingButtonThree = new Button(this);
            timingButtonThree.setText(dateFormatter.format(medicineTimes.get(2)));
            timingButtonThree.setTag(2);
            timingButtonThree.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            timingButtonThree.setOnClickListener(timesButtonClickListener(timingButtonThree));
            medicine_timings_linearlayout.addView(timingButtonThree);
        }
        if (medicineTimes.size() >= 4) {
            Button timingButtonFour = new Button(this);
            timingButtonFour.setText(dateFormatter.format(medicineTimes.get(3)));
            timingButtonFour.setTag(3);
            timingButtonFour.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            timingButtonFour.setOnClickListener(timesButtonClickListener(timingButtonFour));
            medicine_timings_linearlayout.addView(timingButtonFour);
        }
    }


    View.OnClickListener timesButtonClickListener(final Button button)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                TimePicker alarm_timepicker = (TimePicker) findViewById(R.id.alarm_timepicker);
                button.setText(alarm_timepicker.getCurrentHour() + ":" + alarm_timepicker.getCurrentMinute());
                Date newTime = new Date();
                newTime.setTime(0);
                newTime.setHours(alarm_timepicker.getCurrentHour());
                newTime.setMinutes(alarm_timepicker.getCurrentMinute());
                List <Date> times = medicineObject.getDatetimeTimes();
                times.set((Integer) button.getTag(), newTime);
                medicineObject.setTimes(times);
                db.updateMedicine(medicineObject);
                button.getTag();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pill, menu);
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
