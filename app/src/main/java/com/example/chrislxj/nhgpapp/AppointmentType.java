package com.example.chrislxj.nhgpapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;


public class AppointmentType extends Activity {
    public final static String appointmentTypeTag = "NHGPAPP_APPOINTMENT_TYPE";
    private String appointmentPolyclinic;
    private String appointmentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_type);

        Spinner appointment_polyclinic_spinner = (Spinner) findViewById(R.id.appointment_polyclinic_spinner);
        final Spinner appointment_service_spinner = (Spinner) findViewById(R.id.appointment_service_spinner);

        // Polyclinic Spinner elements
        final List<String> polyclinicOptions = new ArrayList<String>();
        polyclinicOptions.add("Ang Mo Kio Polyclinic");
        polyclinicOptions.add("Clementi Polyclinic");
        polyclinicOptions.add("Toa Payoh Polyclinic");
        polyclinicOptions.add("Bukit Batok Polyclinic");
        polyclinicOptions.add("Hougang Polyclinic");
        polyclinicOptions.add("Woodlands Polyclinic");
        polyclinicOptions.add("Choa Chu Kang Polyclinic");
        polyclinicOptions.add("Jurong Polyclinic");
        polyclinicOptions.add("Yishun Polyclinic");

        // Service Spinner elements
        final List<String> serviceOptions = new ArrayList<String>();
        serviceOptions.add("Consultation");
        serviceOptions.add("Physiotherapy");
        serviceOptions.add("Nursing");
        serviceOptions.add("Dental");

        // Creating and attaching adapter for polyclinic spinner
        final ArrayAdapter<String> polyclinicSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, polyclinicOptions);
        polyclinicSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointment_polyclinic_spinner.setAdapter(polyclinicSpinnerAdapter);

        // Creating and attaching adapter for service spinner
        final ArrayAdapter<String> serviceSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceOptions);
        serviceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointment_service_spinner.setAdapter(serviceSpinnerAdapter);

        appointment_polyclinic_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View childView, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    appointmentPolyclinic = item.toString();
                    String[] newOptions = null;
                    switch (appointmentPolyclinic){
                        case "Ang Mo Kio Polyclinic":
                            newOptions = new String[]{"Consultation","Nursing","Dental"};
                            break;
                        case "Woodlands Polyclinic":
                            newOptions = new String[]{"Consultation","Physiotherapy","Nursing","Dental"};
                            break;
                        case "Toa Payoh Polyclinic":
                            newOptions = new String[]{"Consultation","Physiotherapy","Nursing","Dental"};
                            break;
                        case "Clementi Polyclinic":
                            newOptions = new String[]{"Consultation"};
                            break;
                        case "Hougang Polyclinic":
                            newOptions = new String[]{"Consultation","Nursing","Dental"};
                            break;
                        case "Choa Chu Kang Polyclinic":
                            newOptions = new String[]{"Consultation","Nursing"};
                            break;
                        case "Bukit Batok Polyclinic":
                            newOptions = new String[]{"Consultation","Physiotherapy","Nursing",};
                            break;
                        case "Jurong Polyclinic":
                            newOptions = new String[]{"Consultation","Nursing","Dental"};
                            break;
                        case "Yishun Polyclinic":
                            newOptions = new String[]{"Consultation","Nursing",};
                            break;
                    }
                    serviceOptions.clear();
                    Log.d("debug",String.valueOf(newOptions.length));
                    for (int i=0;i<newOptions.length;i++){
                        Log.d("debug","Before adding newOptions");
                        serviceOptions.add(newOptions[i]);
                        Log.d("debug", newOptions[i]);
                    }
                    Log.d("debug","After adding newOptions");
                    serviceSpinnerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        appointment_service_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View childView, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item!=null) {
                    appointmentService = item.toString();
                    String[] newOptions = null;
                    switch (appointmentService){
                        case "Physiotherapy":
                            newOptions = new String[]{"Woodlands Polyclinic","Toa Payoh Polyclinic","Bukit Batok Polyclinic"};
                            break;
                        case "Dental":
                            newOptions = new String[]{"Ang Mo Kio Polyclinic","Toa Payoh Polyclinic","Hougang Polyclinic","Woodlands Polyclinic","Jurong Polyclinic"};
                            break;
                        case "Nursing":
                            newOptions = new String[]{"Ang Mo Kio Polyclinic","Toa Payoh Polyclinic","Bukit Batok Polyclinic","Hougang Polyclinic","Woodlands Polyclinic","Jurong Polyclinic","Jurong Polyclinic","Yishun Polyclinic"};
                            break;
                        case "Consultation":
                            newOptions = new String[]{"Ang Mo Kio Polyclinic","Clementi Polyclinic","Toa Payoh Polyclinic","Bukit Batok Polyclinic","Hougang Polyclinic","Woodlands Polyclinic","Jurong Polyclinic","Jurong Polyclinic","Yishun Polyclinic"};
                            break;
                    }
                    polyclinicOptions.clear();
                    for (int i=0;i<newOptions.length;i++){
                        polyclinicOptions.add(newOptions[i]);
                    }
                    polyclinicSpinnerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment_type, menu);
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

    public void submitAppointmentTypeToServer (View view){
        //TODO: Submit appointment type to server
        Intent intent = new Intent(view.getContext(), AppointmentTime.class);
        intent.putExtra(appointmentTypeTag, appointmentService);
        startActivity(intent);
    }
}
