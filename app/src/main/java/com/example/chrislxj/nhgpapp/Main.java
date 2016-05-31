package com.example.chrislxj.nhgpapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.util.Log;


public class Main extends FragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
    private AdapterTabsPager tabsAdapter;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main_activity);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        if (actionBar==null){
            Log.d("TAG", "actionBar is null");
        }
        tabsAdapter = new AdapterTabsPager(getSupportFragmentManager());

        viewPager.setAdapter(tabsAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        actionBar.addTab(actionBar.newTab().setText("Medicine").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Fitness").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Appointments").setTabListener(this));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void openBloodActivity (View view){
        SharedPreferences pref = view.getContext().getSharedPreferences(getString(R.string.number_of_graphs_file_key), Context.MODE_PRIVATE);
        int defaultNumberOfGraphs = 3;
        int numberOfGraphs = pref.getInt(getString(R.string.number_of_graphs_file_key), defaultNumberOfGraphs);
        Intent intent = null;
        if (numberOfGraphs == 5){
            intent = new Intent(view.getContext(), BloodPressure.class);
        } else if (numberOfGraphs == 4){
            intent = new Intent(view.getContext(), BloodGlucose.class);
        }
        startActivity(intent);
    }

    public void openPillActivity (View view) {
        String tag = "NHGPAPP_MEDICINE_TYPE";
        String medicineType = view.getTag().toString();
        Intent intent = new Intent(view.getContext(), Pill.class);
        intent.putExtra(tag, medicineType);
        startActivity(intent);
    }
}
