package com.example.desenvolvimento.challengemarvel.services;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.desenvolvimento.challengemarvel.CharacterDAO;
import com.example.desenvolvimento.challengemarvel.models.Character;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */
    // Serviço responsável por buscar na ApiService e  montar uma lista de Character e retornar na View(Activity)

public class CharacterService {

    public ArrayList<Character> listAllCharacters(Context context, boolean isOnline) throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Character> characters = new ArrayList<Character>();
        ApiService apiService = new ApiService();
        CharacterDAO characterDAO = new CharacterDAO(context);
        apiService.setUrlString("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
        String data = apiService.execute().get();
        JSONObject dataJson;
        if(isOnline) {
            if (data != null) {
                dataJson = new JSONObject(data);
                JSONObject characterJson;
                JSONArray jsonResult = dataJson.getJSONObject("data").getJSONArray("results");
                for (int i = 0; i < jsonResult.length(); i++) {
                    characterJson = new JSONObject(jsonResult.getString(i));

                    Character objCharacter = new Character();
                    objCharacter.setName(characterJson.getString("name"));
                    objCharacter.setDescription(characterJson.getString("description"));
                    objCharacter.setModified(characterJson.getString("modified"));
                    objCharacter.setImageUrl(characterJson.optJSONObject("thumbnail").getString("path"));
                    objCharacter.setAmountComics(characterJson.optJSONObject("comics").getString("returned"));
                    objCharacter.setAmountEvents(characterJson.optJSONObject("events").getString("returned"));
                    characterDAO.addCharacter((objCharacter));
                    characters.add(objCharacter);
                }

            }

        }else{
            ArrayList<Character> charactersDAO=characterDAO.listCharacters();
        }

        return characterDAO.listCharacters();
    }


}
