package com.example.chrislxj.nhgpapp;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

//TODO: Figure out adding limit lines
//TODO: Configure graph styles
//TODO: Configure update graphs

public class AdapterGraphs{

    public static void initializeGraph (LineChart chart, int timeframe, String type, ArrayList<Entry> data){
        ArrayList<String> day = new ArrayList<String>();
        day.add("Monday");
        day.add("Tuesday");
        day.add("Wednesday");
        day.add("Thursday");
        day.add("Friday");
        day.add("Saturday");
        day.add("Sunday");

        ArrayList<String> week = new ArrayList<String>();
        week.add("Monday");
        week.add("Tuesday");
        week.add("Wednesday");
        week.add("Thursday");
        week.add("Friday");
        week.add("Saturday");
        week.add("Sunday");

        ArrayList<String> month = new ArrayList<String>();
        month.add("Monday");
        month.add("Tuesday");
        month.add("Wednesday");
        month.add("Thursday");
        month.add("Friday");
        month.add("Saturday");
        month.add("Sunday");

        LineData lineData = null;
        LineDataSet dataset = new LineDataSet(data, type);

        switch (timeframe) {
            case 0:
                lineData = new LineData (day, dataset);
            case 1:
                lineData = new LineData (week, dataset);
            case 2:
                lineData = new LineData (month, dataset);
        }
        chart.setData(lineData);
    }
    public void updateGraph (){

    }
    public void setStyle1 (LineChart chart){

    }

    public void setStyle2 (LineChart chart){

    }
}
