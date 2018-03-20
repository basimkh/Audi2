package com.example.hp1.audi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hp1 on 13/02/2018.
 */

public class DBHandling extends SQLiteOpenHelper {
    private static DBHandling sInstance;
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "audi.db";
    public static final String TABLE_SERVICE = "serive";

    public static final String COL_ID = "id";
    public static final String COL_LOCATION = "location";
    public static final String COL_CARNUM = "carnum";
    public static final String COL_TIME = "time";
    public static final String COL_DATE = "date";



    public DBHandling(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    public static synchronized DBHandling getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHandling(context.getApplicationContext());

        }
        return sInstance;
    }

    //create table users this method is called when application is loaded
    @Override
    public void onCreate(SQLiteDatabase db) {
        //in case table already exist need to drop then rebuild
        //save query to creat table in database according to requirements in a string variable
        String query = " CREATE TABLE " + TABLE_SERVICE
                + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LOCATION + " TEXT, "
                + COL_CARNUM + " TEXT, "
                + COL_TIME + " TEXT, "
                + COL_DATE + " TEXT "
                + ");";
        Log.d("QUERY", query);

        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
            db.execSQL(query);
        } catch (Exception e) {
            Log.d("Couldn't add table", e.getMessage());
        }
    }

    //this method must be implemented as part of implementing the interface
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        onCreate(db);
    }

    //add user to the database method
    public void insertData(BookService bookService) {
        //create ContentValue containning values to be inserted/ updated in database in this table
        ContentValues values = new ContentValues();

        values.put(COL_LOCATION, bookService.getLocation());
        values.put(COL_CARNUM, bookService.getCarnum());
        values.put(COL_TIME, bookService.getTime());
        values.put(COL_DATE, bookService.getDate());
        // create SQLiteDatabase object to enable writing/reading in database
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_SERVICE, null, values);
        bookService.setId(id);//update the user ID according to the auto increment..

        //logging for debugging purposes
        Log.d("ID ", "Question id: " + id + " added to DB");

        //close connection to database
        db.close();

    }

    //search data from DB
    public ArrayList<BookService> getData() {
        String[] r = new String[5];
        int[] col = new int[5];
        String query = "SELECT * FROM " + TABLE_SERVICE;

        ArrayList<BookService> alc = new ArrayList<BookService>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        col[0] = c.getColumnIndex(COL_ID);
        col[1] = c.getColumnIndex(COL_LOCATION);
        col[2] = c.getColumnIndex(COL_CARNUM);
        col[3] = c.getColumnIndex(COL_TIME);
        col[4] = c.getColumnIndex(COL_DATE);

        while (!c.isAfterLast()) {
            for (int i = 0; i < col.length; i++) {
                r[i] = c.getString(col[i]);

            }
            alc.add(new BookService(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4] ));

            c.moveToNext();
        }


        return alc;

    }
}



