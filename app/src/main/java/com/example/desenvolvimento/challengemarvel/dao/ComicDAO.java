package com.example.desenvolvimento.challengemarvel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.desenvolvimento.challengemarvel.models.Comics;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 31/08/2017.
 */

public class ComicDAO {

    private SQLiteDatabase db;
    private static final String TABLE_NAME = "comics";

    public ComicDAO(Context context) {
        DatabaseMarvel auxDb = new DatabaseMarvel(context);
        db = auxDb.getWritableDatabase();
    }

    public void addComic(Comics comic){
        ContentValues values = new ContentValues();
        values.put("title", comic.getTitle());
        values.put("description", comic.getDescription());
        values.put("modified", comic.getModified());
        values.put("isbn", comic.getIsbn());
        values.put("pages", comic.getPages());


        long insertedId= db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Comics> listComics(){
        String sql = "select * FROM "+TABLE_NAME;

        ArrayList<Comics> comics = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Comics comic = new Comics();
                comic.setId(Integer.parseInt(cursor.getString(0)));
                comic.setTitle(cursor.getString(1));
                comic.setDescription(cursor.getString(2));
                comic.setModified(cursor.getString(3));
                comic.setIsbn(cursor.getString(4));
                comic.setPages(cursor.getString(5));
                comics.add(comic);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return comics;
    }

    public void deleteDataComics(){
        db.execSQL("delete from comics");

    }

}
