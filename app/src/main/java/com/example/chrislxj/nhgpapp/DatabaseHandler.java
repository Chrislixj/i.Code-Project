package com.example.chrislxj.nhgpapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//TODO:Change appointments id to time?

/*
- Fitness Format
Primary Key = Time
-----------
|TIME|VALUE|
-----------
|int |int  |
-----------
|xxx |xxx  |
-----------
*/

/*
- Medicine Format
Primary Key = Id
--------------------------------
|ID  |NAME |INSTR |QUANT |TIME |
--------------------------------
|int |text |text  |int   |text |
--------------------------------
|xxx |xxx  |xxx   |xxx   |xxx  |
--------------------------------
*/

/*
- Accounts Format
Primary Key = Name
???
*/

/*
- Appointments Format
Primary Key = Id
---------------------------
|ID  |TIME |TYPE |LOCATION|
---------------------------
|int |int  |text |text    |
---------------------------
|xxx |xxx  |xxx  |xxx     |
---------------------------
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Database";

    // Table Names
    private static final String TABLE_GLUCOSE = "GLUCOSE";
    private static final String TABLE_PRESSURE = "PRESSURE";
    private static final String TABLE_CALORIES_EXPENDED = "CALORIES_EXPENDED";
    private static final String TABLE_CALORIES_CONSUMED = "CALORIES_CONSUMED";
    private static final String TABLE_STEPS = "STEPS";
    private static final String TABLE_DISTANCE = "DISTANCE";
    private static final String TABLE_MEDICINE = "MEDICINE";
    private static final String TABLE_ACCOUNT = "ACCOUNT";
    private static final String TABLE_PAST_APPOINTMENTS = "PAST_APPOINTMENTS";
    private static final String TABLE_CURRENT_APPOINTMENTS = "CURRENT_APPOINTMENTS";

    //Fitness Table Columns Names
    private static final String KEY_FITNESS_TIME = "fitness_time";
    private static final String KEY_FITNESS_VALUE = "fitness_value";

    // Medicine Table Columns Names
    private static final String KEY_MEDICINE_ID = "medicine_identification";
    private static final String KEY_MEDICINE_NAME = "medicine_name";
    private static final String KEY_MEDICINE_INSTR = "medicine_instructions";
    private static final String KEY_MEDICINE_QUANT = "medicine_quantities";
    private static final String KEY_MEDICINE_TIMES = "medicine_times";
    private static final String KEY_MEDICINE_PROGRESS = "medicine_progress";

    //Account Table Column Names
    private static final String KEY_ACCOUNT_ID = "account_identification";

    //Appointments Table Column Names
    private static final String KEY_APPOINTMENTS_ID = "appointments_identification";
    private static final String KEY_APPOINTMENTS_TIME = "appointments_time";
    private static final String KEY_APPOINTMENTS_TYPE = "appointments_type";
    private static final String KEY_APPOINTMENTS_LOCATION = "appointments_location";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GLUCOSE_TABLE = "CREATE TABLE " + TABLE_GLUCOSE + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_PRESSURE_TABLE = "CREATE TABLE " + TABLE_PRESSURE + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_CALORIES_EXPENDED_TABLE = "CREATE TABLE " + TABLE_CALORIES_EXPENDED + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_CALORIES_CONSUMED_TABLE = "CREATE TABLE " + TABLE_CALORIES_CONSUMED + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_STEPS_TABLE = "CREATE TABLE " + TABLE_STEPS + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_DISTANCE_TABLE = "CREATE TABLE " + TABLE_DISTANCE + "("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_MEDICINE_TABLE = "CREATE TABLE " + TABLE_MEDICINE + "("
                + KEY_MEDICINE_ID + " INTEGER PRIMARY KEY,"
                + KEY_MEDICINE_NAME + " TEXT, "
                + KEY_MEDICINE_INSTR + " TEXT, "
                + KEY_MEDICINE_QUANT + " INTEGER, "
                + KEY_MEDICINE_TIMES + " TEXT, "
                + KEY_MEDICINE_PROGRESS + " INTEGER" + ")";

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + "(" + KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY" + ")";

        String CREATE_PAST_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_PAST_APPOINTMENTS + "("
                + KEY_APPOINTMENTS_ID + " INTEGER PRIMARY KEY, "
                + KEY_APPOINTMENTS_TIME + " INTEGER, "
                + KEY_APPOINTMENTS_TYPE + " TEXT, "
                + KEY_APPOINTMENTS_LOCATION + " TEXT" +  ")";

        String CREATE_CURRENT_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_CURRENT_APPOINTMENTS + "("
                + KEY_APPOINTMENTS_ID + " INTEGER PRIMARY KEY, "
                + KEY_APPOINTMENTS_TIME + " INTEGER, "
                + KEY_APPOINTMENTS_TYPE + " TEXT, "
                + KEY_APPOINTMENTS_LOCATION + " TEXT" +  ")";

        db.execSQL(CREATE_GLUCOSE_TABLE);
        db.execSQL(CREATE_PRESSURE_TABLE);
        db.execSQL(CREATE_CALORIES_EXPENDED_TABLE);
        db.execSQL(CREATE_CALORIES_CONSUMED_TABLE);
        db.execSQL(CREATE_STEPS_TABLE);
        db.execSQL(CREATE_DISTANCE_TABLE);
        db.execSQL(CREATE_MEDICINE_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_PAST_APPOINTMENTS_TABLE);
        db.execSQL(CREATE_CURRENT_APPOINTMENTS_TABLE);

        //TODO: Delete outdated fitness records (older than 1 month)
        //TODO: Delete outdated medicine records (quantity is zero)

    }

    // Count Tables
    public int getFitnessCount(String table) {
        String countQuery = "SELECT  * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    //----------FITNESS CRUD FUNCTIONS----------//
    // Create A Fitness
    public void addFitness(String table, ObjectFitness fitnessObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FITNESS_TIME, fitnessObject.getDatabaseTime());
        values.put(KEY_FITNESS_VALUE, fitnessObject.getValue());
        db.insert(table, null, values);
        db.close();
    }
    // Read A Fitness
    public ObjectFitness getFitness(String table, Date time) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table, new String[]
                        {KEY_FITNESS_TIME,
                        KEY_FITNESS_VALUE},
                        KEY_FITNESS_TIME + " = ?",
                        new String[] { String.valueOf(time.getTime()) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ObjectFitness fitnessObject = new ObjectFitness();
        fitnessObject.setTime(cursor.getLong(0));
        fitnessObject.setValue(cursor.getInt(1));
        return fitnessObject;
    }
    // Update A Fitness
    public void updateFitness(String table, ObjectFitness fitnessObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FITNESS_TIME, fitnessObject.getDatabaseTime());
        values.put(KEY_FITNESS_VALUE, fitnessObject.getValue());
        db.update(table, values, KEY_FITNESS_TIME + " = ?", new String[]{String.valueOf(fitnessObject.getDatabaseTime())});
    }
    // Delete A Fitness
    public void deleteFitness(String table, Date time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, KEY_FITNESS_TIME + " = ?", new String[]{String.valueOf(time.getTime())});
        db.close();
    }

    //----------MEDICINE CRUD FUNCTIONS----------//
    // Create A Medicine
    public void addMedicine(ObjectMedicine medicineObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEDICINE_ID, medicineObject.getID());
        values.put(KEY_MEDICINE_NAME, medicineObject.getName());
        values.put(KEY_MEDICINE_INSTR, medicineObject.getInstruction());
        values.put(KEY_MEDICINE_QUANT, medicineObject.getQuantity());
        values.put(KEY_MEDICINE_TIMES, medicineObject.getDatabaseTimes());
        values.put(KEY_MEDICINE_PROGRESS, medicineObject.getProgress());
        db.insert(TABLE_MEDICINE, null, values);
        db.close();
    }
    // Read A Medicine
    public ObjectMedicine getMedicine(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEDICINE, new String[]
                        {KEY_MEDICINE_ID,
                        KEY_MEDICINE_NAME,
                        KEY_MEDICINE_INSTR,
                        KEY_MEDICINE_QUANT,
                        KEY_MEDICINE_TIMES,
                        KEY_MEDICINE_PROGRESS},
                        KEY_MEDICINE_ID + " = ?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ObjectMedicine medicineObject = new ObjectMedicine();
        medicineObject.setID(cursor.getInt(0));
        medicineObject.setName(cursor.getString(1));
        medicineObject.setInstruction(cursor.getString(2));
        medicineObject.setQuantity(cursor.getInt(3));
        medicineObject.setTimes(cursor.getString(4));
        medicineObject.setProgress(cursor.getInt(5));
        return medicineObject;
    }
    // Update A Medicine
    public void updateMedicine(ObjectMedicine medicineObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEDICINE_ID, medicineObject.getID());
        values.put(KEY_MEDICINE_NAME, medicineObject.getName());
        values.put(KEY_MEDICINE_INSTR, medicineObject.getInstruction());
        values.put(KEY_MEDICINE_QUANT, medicineObject.getQuantity());
        values.put(KEY_MEDICINE_TIMES, medicineObject.getDatabaseTimes());
        values.put(KEY_MEDICINE_PROGRESS, medicineObject.getProgress());
        db.update(TABLE_MEDICINE, values, KEY_MEDICINE_ID + " = ?", new String[]{String.valueOf(medicineObject.getID())});
    }
    // Delete A Medicine
    public void deleteMedicine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINE, KEY_MEDICINE_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //---------- ACCOUNT CRUD FUNCTIONS----------//
    //TODO: Add account table and crud


    //----------APPOINTMENTS CRUD FUNCTIONS----------//
    // Create A Appointment
    public void addAppointment(String table, ObjectAppointment appointmentObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_APPOINTMENTS_ID, appointmentObject.getId());
        values.put(KEY_APPOINTMENTS_TIME, appointmentObject.getDatabaseTime());
        values.put(KEY_APPOINTMENTS_TYPE, appointmentObject.getType());
        values.put(KEY_APPOINTMENTS_LOCATION, appointmentObject.getLocation());
        db.insert(table, null, values);
        db.close();
    }
    // Read A Appointment
    public ObjectAppointment getAppointment(String table, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table, new String[]
                        {KEY_APPOINTMENTS_ID,
                        KEY_APPOINTMENTS_TIME,
                        KEY_APPOINTMENTS_TYPE,
                        KEY_APPOINTMENTS_LOCATION},
                        KEY_APPOINTMENTS_ID + " = ?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ObjectAppointment appointmentObject = new ObjectAppointment();
        appointmentObject.setId(cursor.getInt(0));
        appointmentObject.setTime(cursor.getLong(1));
        appointmentObject.setType(cursor.getString(2));
        appointmentObject.setLocation(cursor.getString(3));
        return appointmentObject;
    }
    // Update A Appointment
    public void updateAppointment(String table, ObjectAppointment appointmentObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_APPOINTMENTS_ID, appointmentObject.getId());
        values.put(KEY_APPOINTMENTS_TIME, appointmentObject.getDatabaseTime());
        values.put(KEY_APPOINTMENTS_TYPE, appointmentObject.getType());
        values.put(KEY_APPOINTMENTS_LOCATION, appointmentObject.getLocation());
        db.update(table, values, KEY_APPOINTMENTS_ID + " = ?", new String[]{String.valueOf(appointmentObject.getId())});
    }
    // Delete A Appointment
    public void deleteAppointment(String table, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, KEY_APPOINTMENTS_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //---------- DUMP FUNCTIONS ----------//
    //Get All Fitness
    public List<ObjectFitness> getAllFitness(String table) {
        List<ObjectFitness> fitnessObjectList = new ArrayList<ObjectFitness>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ObjectFitness fitnessObject = new ObjectFitness();
                fitnessObject.setTime(Long.parseLong(cursor.getString(0)));
                fitnessObject.setValue(Integer.parseInt(cursor.getString(1)));
                fitnessObjectList.add(fitnessObject);
            } while (cursor.moveToNext());
        }
        return fitnessObjectList;
    }
    public List<ObjectMedicine> getAllMedicine() {
        List<ObjectMedicine> medicineObjectList = new ArrayList<ObjectMedicine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ObjectMedicine medicineObject = new ObjectMedicine();
                medicineObject.setID(cursor.getInt(0));
                medicineObject.setName(cursor.getString(1));
                medicineObject.setInstruction(cursor.getString(2));
                medicineObject.setQuantity(cursor.getInt(3));
                medicineObject.setTimes(cursor.getString(4));
                medicineObject.setProgress(cursor.getInt(5));
                medicineObjectList.add(medicineObject);
            } while (cursor.moveToNext());
        }
        return medicineObjectList;
    }
    public List<ObjectAppointment> getAllAppointments(String table) {
        List<ObjectAppointment> appointmentObjectList = new ArrayList<ObjectAppointment>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ObjectAppointment appointmentObject = new ObjectAppointment();
                appointmentObject.setId(cursor.getInt(0));
                appointmentObject.setTime(cursor.getLong(1));
                appointmentObject.setType(cursor.getString(2));
                appointmentObject.setLocation(cursor.getString(3));
                appointmentObjectList.add(appointmentObject);
            } while (cursor.moveToNext());
        }
        return appointmentObjectList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}