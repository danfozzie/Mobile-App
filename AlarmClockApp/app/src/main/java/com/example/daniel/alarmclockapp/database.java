package com.example.daniel.alarmclockapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tino on 12/05/2016.
 */
public class database extends SQLiteOpenHelper {

    public static final String DB_NAME = "score.db";
    public static final String TABLE_NAME = "score";
    public static final String ID = "_id";
    public static final String SCORE = "score";
    public static final int VERSION = 1;

    private final String createDB = "create table if not exists " +TABLE_NAME+ "("
            +ID+ "integer primary key autoincrement"
            +SCORE+ "text";

    public database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table" +TABLE_NAME);
    }
}
