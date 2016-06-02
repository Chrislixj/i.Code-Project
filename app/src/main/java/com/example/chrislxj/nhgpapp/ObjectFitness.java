package com.example.chrislxj.nhgpapp;

import java.util.Date;

public class ObjectFitness {

    int _value;
    long _database_format_time;
    Date _datetime_format_time;

    // Empty constructor
    public ObjectFitness(){

    }
    //Constructor 1
    public ObjectFitness(int value, Date datetimeTime){
        this._value = value;
        this._database_format_time = datetimeTime.getTime();
        this._datetime_format_time = datetimeTime;
    }

    //Constructor 2
    public ObjectFitness(int value, long databaseTime){
        this._value = value;
        this._database_format_time = databaseTime;
        this._datetime_format_time = new Date(databaseTime);
    }

    //Value
    public int getValue(){
        return this._value;
    }
    public void setValue(int value){
        this._value = value;
    }

    //DatabaseFormat Time
    public long getDatabaseTime(){
        return this._database_format_time;
    }
    public void setTime(long time){
        this._database_format_time = time;
        this._datetime_format_time = new Date(time);
    }

    //DatetimeFormat Time
    public Date getDatetimeTime(){
        return this._datetime_format_time;
    }
    public void setTime(Date time){
        this._database_format_time = time.getTime();
        this._datetime_format_time = time;
    }
}
