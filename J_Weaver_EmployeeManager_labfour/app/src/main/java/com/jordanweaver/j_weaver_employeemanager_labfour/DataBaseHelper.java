package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_FILENAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "employees";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRST = "first_name";
    private static final String COLUMN_LAST = "last_name";
    private static final String COLUMN_EMPLOYEE_NUM = "employee_num";
    private static final String COLUMN_HIRE_DATE = "hire_date";
    private static final String COLUMN_STATUS = "employee_status";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " ("+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FIRST + " TEXT, " +
            COLUMN_LAST + " TEXT, " +
            COLUMN_EMPLOYEE_NUM + " INTEGER, " +
            COLUMN_HIRE_DATE + " DATETIME, " +
            COLUMN_STATUS + " TEXT)";

    public static DataBaseHelper INSTANCE = null;

    public static DataBaseHelper getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = new DataBaseHelper(context);
        }
        return INSTANCE;
    }

    private SQLiteDatabase mDatabase;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);

        mDatabase = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addEmployee(String first, String last, int employNum, String hireDate, String status){
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRST, first);
        values.put(COLUMN_LAST, last);
        values.put(COLUMN_EMPLOYEE_NUM, employNum);
        values.put(COLUMN_HIRE_DATE, hireDate);
        values.put(COLUMN_STATUS, status);

        mDatabase.insert(TABLE_NAME, null, values);

    }

    public Cursor getEmployees(){
        Cursor cursor = mDatabase.query(TABLE_NAME, null, null, null, null, null, null);

        Log.e("the cursor", cursor.toString()+"");

        return cursor;
    }

    public void deleteEmployee(String employee){
        String deleteClause = COLUMN_FIRST + " = " + "'"+employee+"'";

        mDatabase.delete(TABLE_NAME, deleteClause, null);
    }


}
