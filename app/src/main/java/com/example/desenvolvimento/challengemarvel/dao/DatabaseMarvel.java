package com.example.desenvolvimento.challengemarvel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desenvolvimento on 29/08/2017.
 */

public class DatabaseMarvel extends SQLiteOpenHelper {

    private    static final int DATABASE_VERSION =    5;
    private    static final String    DATABASE_NAME = "marvel";
    private static final String COLUMN_ID = "_id";
    public DatabaseMarvel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHARACTER_TABLE = "CREATE TABLE character (" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, name text, description text, modified text, amountComics text, amountEvents text, imageUrl text )";
        String CREATE_COMICS_TABLE = "CREATE TABLE comics (" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, title text, description text, modified text, isbn text, pages text)";
        String CREATE_EVENTS_TABLE = "CREATE TABLE events (" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, title text, description text, modified text, startDate text, endDate text)";
        db.execSQL(CREATE_CHARACTER_TABLE);
        db.execSQL(CREATE_COMICS_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  character");
        db.execSQL("DROP TABLE IF EXISTS  comics");
        db.execSQL("DROP TABLE IF EXISTS  events");
        onCreate(db);
    }
}
