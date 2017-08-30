package com.example.desenvolvimento.challengemarvel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.desenvolvimento.challengemarvel.models.Character;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 29/08/2017.
 */

public class CharacterDAO {
    private SQLiteDatabase db;
    private static final String TABLE_NAME = "character";

    public CharacterDAO(Context context) {
        DatabaseMarvel auxDb = new DatabaseMarvel(context);
        db = auxDb.getWritableDatabase();
    }

    public void addCharacter(Character character){
        ContentValues values = new ContentValues();
        values.put("name", character.getName());
        values.put("description", character.getDescription());
        values.put("modified", character.getModified());
        values.put("amountComics", character.getAmountComics());
        values.put("amountEvents", character.getAmountEvents());
        values.put("imageUrl", character.getImageUrl());

        db.insert("insert into "+TABLE_NAME, null, values);
    }

    public ArrayList<Character> listCharacters(){
            String sql = "select * FROM "+TABLE_NAME;

           ArrayList<Character> characters = new ArrayList<>();
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
                do{
                    Character character = new Character();
                    character.setId(Integer.parseInt(cursor.getString(0)));
                    character.setName(cursor.getString(1));
                    character.setDescription(cursor.getString(2));
                    character.setModified(cursor.getString(3));
                    character.setAmountComics(cursor.getString(4));
                    character.setAmountEvents(cursor.getString(5));
                    character.setImageUrl(cursor.getString(6));
                    characters.add(character);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return characters;
    }

}
