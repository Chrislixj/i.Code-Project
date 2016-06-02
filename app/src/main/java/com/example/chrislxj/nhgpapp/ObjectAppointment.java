package com.example.chrislxj.nhgpapp;

import java.util.Date;

public class ObjectAppointment {
    int _id;
    String _location;
    String _type;
    long _database_format_time;
    Date _datetime_format_time;

    // Empty constructor
    public ObjectAppointment(){

    }
    //Constructor 1
    public ObjectAppointment(int id, Date datetimeTime, String type, String location){
        this._id = id;
        this._type = type;
        this._location = location;
        this._database_format_time = datetimeTime.getTime();
        this._datetime_format_time = datetimeTime;
    }

    //Constructor 2
    public ObjectAppointment(int id, long databaseTime, String type, String location){
        this._id = id;
        this._type = type;
        this._location = location;
        this._database_format_time = databaseTime;
        this._datetime_format_time = new Date(databaseTime);
    }

    //ID
    public int getId(){
        return this._id;
    }
    public void setId(int id){
        this._id = id;
    }

    //Location
    public String getLocation(){
        return this._location;
    }
    public void setLocation(String location){
        this._location = location;
    }

    //Type
    public String getType(){
        return this._type;
    }
    public void setType(String type){
        this._type = type;
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
