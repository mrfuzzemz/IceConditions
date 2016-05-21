package com.mrfuzzemz.neclimbsiceconditions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iceLocations.db";
    private static final String TABLE_NAME = "iceLocations_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "CONDITION";
    private static final String COL_4 = "VERDICT";
    private static final String COL_5 = "DATE";
    private static final String COL_6 = "LAT";
    private static final String COL_7 = "LONG";
    private static final String COL_8 = "PIC";  // Probably best to have a path rather than
                                                // store as a BLOB. We'll see.
    // private static final String COL_9 = "ICON"; // Icon can probably be determined by Verdict
    // If Verdict isn't recognized, can just be a default/neutral image
    /* I'll also want Lat/Long, Pic, Date?, Icon, etc*/


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                   + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + COL_2 + " TEXT, "
                   + COL_3 + " TEXT, "
                   + COL_4 + " TEXT, "
                   + COL_5 + " DATE, "
                   + COL_6 + " REAL, "
                   + COL_7 + " REAL, "
                   + COL_8 + " TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
