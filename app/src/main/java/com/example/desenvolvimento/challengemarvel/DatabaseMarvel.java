package com.example.desenvolvimento.challengemarvel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Desenvolvimento on 29/08/2017.
 */

public class DatabaseMarvel extends SQLiteOpenHelper {

    private    static final int DATABASE_VERSION =    1;
    private    static final String    DATABASE_NAME = "marvel";
    private static final String COLUMN_ID = "_id";
    public DatabaseMarvel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CHARACTER_TABLE = "CREATE    TABLE character (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, modified TEXT, amountComics TEXT, amountEvents TEXT, imageUrl TEXT )";
        db.execSQL(CREATE_CHARACTER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE character");
        onCreate(db);
    }
}
