package com.example.chrislxj.nhgpapp;

import java.util.Date;
import java.util.List;

public class ObjectMedicine {

    //private variables
    int _id;
    int _progress;
    String _name;
    String _instruction;
    int _quantity;
    List<Date> _datetime_times;
    String _database_times;

    // Empty constructor
    public ObjectMedicine(){

    }

    //Constructor 1
    public ObjectMedicine(int id, String name, String instruction, int quantity, List <Date> times, int progress){
        this._id = id;
        this._name = name;
        this._instruction = instruction;
        this._quantity = quantity;
        this._datetime_times = times;
        this._progress = progress;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<times.size(); i++){
            stringBuilder.append(times.get(i).getTime());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        this._database_times = stringBuilder.toString();
    }

    //Constructor 2
    public ObjectMedicine(int id, String name, String instruction, int quantity, String times, int progress){
        this._id = id;
        this._name = name;
        this._instruction = instruction;
        this._quantity = quantity;
        this._database_times = times;
        this._progress = progress;
        String[] splitTimes = times.split(",");
        for (int i=0; i<splitTimes.length; i++){
            Date datetime = new Date(Integer.parseInt(splitTimes[i]));
            this._datetime_times.add(datetime);
        }
    }

    //ID
    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    //Name
    public String getName(){
        return this._name;
    }
    public void setName(String name){
        this._name = name;
    }

    //Instruction
    public String getInstruction(){
        return this._instruction;
    }
    public void setInstruction(String instruction){
        this._instruction = instruction;
    }

    //Quantity
    public int getQuantity(){
        return this._quantity;
    }
    public void setQuantity(int quantity){
        this._quantity = quantity;
    }

    //Progress
    public int getProgress(){
        return this._quantity;
    }
    public void setProgress(int progress){
        this._progress = progress;
    }

    //Times
    public List getDatetimeTimes(){
        return this._datetime_times;
    }
    public String getDatabaseTimes(){
        return this._database_times;
    }

    public void setTimes(String times){
        this._database_times = times;
        String[] splitTimes = times.split(",");
        for (int i=0; i<splitTimes.length; i++){
            Date datetime = new Date(Integer.parseInt(splitTimes[i]));
            this._datetime_times.add(datetime);
        }
    }
    public void setTimes(List<Date> times){
        this._datetime_times = times;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<times.size(); i++){
            stringBuilder.append(times.get(i).getTime());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        this._database_times = stringBuilder.toString();
    }
}
