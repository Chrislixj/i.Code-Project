package com.example.chrislxj.nhgpapp;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//TODO: Sort out that whole food/exercise and adding shit
public class Main extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    private AdapterTabsPager tabsAdapter;
    private ActionBar actionBar;
    public final static String medicineIdTag = "NHGPAPP_MEDICINE_TYPE";
    private SharedPreferences graphsPref;
    private SharedPreferences pillsPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main_activity);
        graphsPref = this.getSharedPreferences(getString(R.string.number_of_graphs_file_key), Context.MODE_PRIVATE);
        pillsPref = this.getSharedPreferences(getString(R.string.number_of_pills_file_key), Context.MODE_PRIVATE);

        //-----REMOVE FOR DEPLOYMENT-----//
        DatabaseHandler db = new DatabaseHandler(this);
        ObjectMedicine medicineObject = new ObjectMedicine();
        medicineObject.setID(98778369);
        medicineObject.setName("Paracetemol");
        medicineObject.setInstruction("Take two tablets daily\nIngest with water\nDo not operate machinery");
        medicineObject.setQuantity(5);
        medicineObject.setTimes("1465728217675,1465728217675");
        medicineObject.setProgress(5);
        db.addMedicine(medicineObject);
        pillsPref.edit().putInt(getString(R.string.number_of_pills_file_key), 1).apply();
        //-------------------------------//

        //VIEW PAGER
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        if (actionBar==null){
            Log.d("TAG", "actionBar is null");
        }
        tabsAdapter = new AdapterTabsPager(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Medicine").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Fitness").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Appointments").setTabListener(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        viewPager.setCurrentItem(1);
    }
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    //OPTIONS MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.add_blood_glucose:
                graphsPref.edit().putInt(getString(R.string.number_of_graphs_file_key), 4).apply();
                finish();
                startActivity(getIntent());
                break;
            case R.id.add_blood_pressure:
                graphsPref.edit().putInt(getString(R.string.number_of_graphs_file_key), 5).apply();
                finish();
                startActivity(getIntent());
                break;
            case R.id.add_food_exercise:
                graphsPref.edit().putInt(getString(R.string.number_of_graphs_file_key), 6).apply();
                finish();
                startActivity(getIntent());
                break;
            case R.id.action_account:

                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //PRESS BUTTONS
    public void openCaloriesActivity (View view){
        Intent intent = new Intent(view.getContext(), Calories.class);
        startActivity(intent);
    }
    public void openStepsActivity (View view){
        Intent intent = new Intent(view.getContext(), Steps.class);
        startActivity(intent);
    }
    public void openDistanceActivity (View view){
        Intent intent = new Intent(view.getContext(), Distance.class);
        startActivity(intent);
    }

    public void openBloodActivity (View view) {
        int defaultNumberOfGraphs = 3;
        int numberOfGraphs = graphsPref.getInt(getString(R.string.number_of_graphs_file_key), defaultNumberOfGraphs);
        Intent intent = null;
        if (numberOfGraphs == 5){
            intent = new Intent(view.getContext(), BloodPressure.class);
        } else if (numberOfGraphs == 4){
            intent = new Intent(view.getContext(), BloodGlucose.class);
        }
        startActivity(intent);
    }

    public void openPillActivity (View view) {
        String medicineType = view.getTag().toString();
        Intent intent = new Intent(view.getContext(), Pill.class);
        intent.putExtra(medicineIdTag, medicineType);
        startActivity(intent);
    }
}
