package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jordanweaver on 3/11/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_FILE = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            DataContract.DATA_TABLE +" ("+
            DataContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DataContract.ITEM_NAME +" TEXT, " +
            DataContract.SECTION +" TEXT, " +
            DataContract.ITEM_QUANTITY + " INTEGER)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
