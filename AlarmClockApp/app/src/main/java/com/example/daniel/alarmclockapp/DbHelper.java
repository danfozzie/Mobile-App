package com.example.daniel.alarmclockapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tino on 12/05/2016.
 */
public class DbHelper  extends SQLiteOpenHelper {

    public static int DB_VERSION = 1;
    public static final String DB_NAME;
    public static final String TABLE_NAME;
    public static final String ID;
    public static final String SCORE;


    static {
        DB_NAME = "score.db";
        TABLE_NAME = "score_tbl";
        ID = "_id";
        SCORE = "score";
    }

    public SQLiteDatabase myDb;

    //private final String createDb = "create table id not exits " + TABLE_NAME + "(" + ID + "integer primary autoincrement, " + SCORE + "text);";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
        myDb = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        myDb = db;

        String query = "CREATE TABLE IF NOT EXISTS SCORE_TABLE ( _ID PRIMARY KEY AUTOINCREMENT, SCORE TEXT NOT NULL );";
        db.execSQL(query);
        Log.i("SQLite","DbHelper onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SCORE_TABLE");
        onCreate(db);
    }
    public void clearTable(String table_name) {
        myDb.execSQL("DELETE FROM " +table_name);
    }
}

