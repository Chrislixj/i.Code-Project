package com.example.chrislxj.nhgpapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;

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
----------------------------------------
|ID  |NAME |INSTR |QUANT |TIME |PROGRESS|
----------------------------------------
|int |text |text  |int   |text |int     |
----------------------------------------
|xxx |xxx  |xxx   |xxx   |xxx  |xxx     |
----------------------------------------
*/

/*
- Accounts Format
Primary Key = Name
------------------------------------------------------------------------------------------------------------------------------------------------------
|ID  |EMAIL_ADDRESS |MAILING_ADDRESS |PATIENT_NAME |PATIENT_CONTACT |CAREGIVER_NAME |CAREGIVER_CONTACT |TARGET_STEPS |TARGET_DISTANCE |TARGET_CALORIES|
------------------------------------------------------------------------------------------------------------------------------------------------------
|int |text          |text            |text         |int             |text           |int               |int          |int             |int            |
------------------------------------------------------------------------------------------------------------------------------------------------------
|xxx |xxx           |xxx             |xxx          |xxx             |xxx            |xxx               |xxx          |xxx             |xxx            |
------------------------------------------------------------------------------------------------------------------------------------------------------
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
    public static final String KEY_ACCOUNT_ID = "account_identification";
    public static final String KEY_ACCOUNT_EMAIL = "account_email_address";
    public static final String KEY_ACCOUNT_MAILING = "account_mailing_address";
    public static final String KEY_ACCOUNT_PATIENT_CONTACT = "account_patient_contact";
    public static final String KEY_ACCOUNT_PATIENT_NAME = "account_patient_name";
    public static final String KEY_ACCOUNT_CAREGIVER_CONTACT = "account_caregiver_contact";
    public static final String KEY_ACCOUNT_CAREGIVER_NAME = "account_caregiver_name";
    public static final String KEY_ACCOUNT_TARGET_STEPS = "account_target_steps";
    public static final String KEY_ACCOUNT_TARGET_DISTANCE = "account_target_distance";
    public static final String KEY_ACCOUNT_TARGET_CALORIES = "account_target_calories";

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
        String CREATE_GLUCOSE_TABLE = "CREATE TABLE " + TABLE_GLUCOSE + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_PRESSURE_TABLE = "CREATE TABLE " + TABLE_PRESSURE + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_CALORIES_EXPENDED_TABLE = "CREATE TABLE " + TABLE_CALORIES_EXPENDED + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_CALORIES_CONSUMED_TABLE = "CREATE TABLE " + TABLE_CALORIES_CONSUMED + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_STEPS_TABLE = "CREATE TABLE " + TABLE_STEPS + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_DISTANCE_TABLE = "CREATE TABLE " + TABLE_DISTANCE + " ("
                + KEY_FITNESS_TIME + " INTEGER PRIMARY KEY, "
                + KEY_FITNESS_VALUE + " INTEGER" + ")";

        String CREATE_MEDICINE_TABLE = "CREATE TABLE " + TABLE_MEDICINE + " ("
                + KEY_MEDICINE_ID + " INTEGER PRIMARY KEY, "
                + KEY_MEDICINE_NAME + " TEXT, "
                + KEY_MEDICINE_INSTR + " TEXT, "
                + KEY_MEDICINE_QUANT + " INTEGER, "
                + KEY_MEDICINE_TIMES + " TEXT, "
                + KEY_MEDICINE_PROGRESS + " INTEGER" + ")";

        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + " ("
                + KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY, "
                + KEY_ACCOUNT_EMAIL + " TEXT, "
                + KEY_ACCOUNT_MAILING + " TEXT, "
                + KEY_ACCOUNT_PATIENT_CONTACT + " INTEGER, "
                + KEY_ACCOUNT_PATIENT_NAME + " TEXT, "
                + KEY_ACCOUNT_CAREGIVER_CONTACT + " INTEGER, "
                + KEY_ACCOUNT_CAREGIVER_NAME + " TEXT, "
                + KEY_ACCOUNT_TARGET_STEPS + " INTEGER, "
                + KEY_ACCOUNT_TARGET_DISTANCE + " INTEGER, "
                + KEY_ACCOUNT_TARGET_CALORIES + " INTEGER"+ ")";

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
        String monthAgo = String.valueOf(System.currentTimeMillis() - 2892720600L);
        String sql = "DELETE FROM "+table+" WHERE fitness_time <= "+monthAgo;
        db.execSQL(sql);
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
                new String[]{String.valueOf(time.getTime())}, null, null, null, null);
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
        if (medicineObject.getQuantity()==0){
            return;
        }
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
                new String[]{String.valueOf(id)}, null, null, null, null);
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
        if (medicineObject.getQuantity()==0){
            deleteMedicine(medicineObject.getID());
            return;
        }
        values.put(KEY_MEDICINE_ID, medicineObject.getID());
        values.put(KEY_MEDICINE_NAME, medicineObject.getName());
        values.put(KEY_MEDICINE_INSTR, medicineObject.getInstruction());
        values.put(KEY_MEDICINE_QUANT, medicineObject.getQuantity());
        values.put(KEY_MEDICINE_TIMES, medicineObject.getDatabaseTimes());
        values.put(KEY_MEDICINE_PROGRESS, medicineObject.getProgress());
        db.update(TABLE_MEDICINE, values, KEY_MEDICINE_ID + " = ?", new String[]{String.valueOf(medicineObject.getID())});
    }
    // Delete A Medicine
    public void deleteAccount(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNT, KEY_MEDICINE_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //---------- ACCOUNT CRUD FUNCTIONS----------//
    // Create Account
    public void addAccount(int id, String email, String mailing, int contact1, String name1, int contact2, String name2, int steps, int distance, int calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNT_ID, id);
        values.put(KEY_ACCOUNT_EMAIL, email);
        values.put(KEY_ACCOUNT_MAILING, mailing);
        values.put(KEY_ACCOUNT_PATIENT_CONTACT, contact1);
        values.put(KEY_ACCOUNT_PATIENT_NAME, name1);
        values.put(KEY_ACCOUNT_CAREGIVER_CONTACT, contact2);
        values.put(KEY_ACCOUNT_CAREGIVER_NAME, name2);
        values.put(KEY_ACCOUNT_TARGET_STEPS, steps);
        values.put(KEY_ACCOUNT_TARGET_DISTANCE, distance);
        values.put(KEY_ACCOUNT_TARGET_CALORIES, calories);
        db.insert(TABLE_ACCOUNT, null, values);
        db.close();
    }
    // Read Account
    public String getAccount(String detail) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor dbCursor = db.query(TABLE_ACCOUNT, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        for (int x=0;x<columnNames.length;x++){
            Log.d("debug",columnNames[x]);
        }

        Cursor cursor = db.query(TABLE_ACCOUNT, new String[]{detail,}, null, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Log.d("debug","Returning string");
        return cursor.getString(0);
    }
    // Update Account
    public void updateMedicine(String detail, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(detail, value);
        db.update(TABLE_ACCOUNT, values, KEY_ACCOUNT_PATIENT_CONTACT + " = ?", new String[]{});
    }
    // Delete Account
    public void deleteMedicine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNT, KEY_ACCOUNT_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


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

    public List getGraphingData(String timeframe, String table) {
        Calendar cal = Calendar.getInstance();
        int var1 = 0;
        int var2 = 0;
        int var3 = 0;
        int var4 = 0;
        switch (timeframe){
            case "day":
                var1=Calendar.HOUR_OF_DAY;
                var2=Calendar.DATE;
                var3=Calendar.DATE;
                var4=Calendar.DATE;
                break;
            case "week":
                var1=Calendar.DAY_OF_WEEK;
                var2=Calendar.HOUR_OF_DAY;
                var3=0;
                var4=24;
                break;
            case "month":
                var1=Calendar.DAY_OF_MONTH;
                var2=Calendar.HOUR_OF_DAY;
                var3=0;
                var4=24;
                break;
        }
        int i = 0;
        int j = 0;
        int portionCount = 0;
        int totalCount = 0;
        Date dayMin = null;
        Date dayMax = null;
        ArrayList<Entry> data = new ArrayList<>();
        List<ObjectFitness> databaseOutput = this.getAllFitness(table);
        cal.set(var1, j);
        cal.set(var2, var3);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dayMin = cal.getTime();
        cal.set(var1, j);
        cal.set(var2, var4);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 1000);
        dayMax = cal.getTime();
        for (i = 0; i < databaseOutput.size(); i++) {
            Date dateObject = databaseOutput.get(i).getDatetimeTime();

            if (dateObject.before(dayMax) && dateObject.after(dayMin) || dateObject.equals(dayMin)) {
                portionCount += databaseOutput.get(i).getValue();
                totalCount += databaseOutput.get(i).getValue();
            } else if (dateObject.after(dayMax) || dateObject.equals(dayMax)) {
                Entry datapoint = new Entry(portionCount, j);
                data.add(datapoint);
                portionCount = 0;
                j++;
                cal.set(var1, j);
                cal.set(var2, var3);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.MILLISECOND, 0);
                dayMin = cal.getTime();
                cal.set(var1, j);
                cal.set(var2, var4);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.MILLISECOND, 1000);
                dayMax = cal.getTime();
            } else {
                Log.e("debug", "Database not ordered");
            }
        }
        List returnList = new ArrayList<>();
        returnList.add(data);
        returnList.add(totalCount);
        returnList.add(totalCount/cal.getActualMaximum(var1));
        return returnList;
    }
}