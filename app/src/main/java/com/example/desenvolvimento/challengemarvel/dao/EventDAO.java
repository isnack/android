package com.example.desenvolvimento.challengemarvel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.desenvolvimento.challengemarvel.models.Events;
import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 31/08/2017.
 */

public class EventDAO {
    private SQLiteDatabase db;
    private static final String TABLE_NAME = "events";

    public EventDAO(Context context) {
        DatabaseMarvel auxDb = new DatabaseMarvel(context);
        db = auxDb.getWritableDatabase();
    }


    public void addEvent(Events event){
        ContentValues values = new ContentValues();
        values.put("title", event.getTitle());
        values.put("description", event.getDescription());
        values.put("modified", event.getModified());
        values.put("startDate", event.getStartDate());
        values.put("endDate", event.getEndDate());


        long insertedId= db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Events> listEvents(){
        String sql = "select * FROM "+TABLE_NAME;

        ArrayList<Events> events = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Events event = new Events();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setTitle(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setModified(cursor.getString(3));
                event.setStartDate(cursor.getString(4));
                event.setEndDate(cursor.getString(5));
                events.add(event);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public void deleteDataEvents(){
        db.execSQL("delete from events");

    }
}
