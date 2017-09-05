package com.example.desenvolvimento.challengemarvel.services;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.desenvolvimento.challengemarvel.dao.CharacterDAO;
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
        CharacterDAO characterDAO = new CharacterDAO(context);
        JSONObject dataJson;

        if(isOnline) {
            ApiService apiService = new ApiService();
            apiService.setUrlString("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
            String data = apiService.execute().get();
            if(characterDAO.listCharacters().size() !=0){
                characterDAO.deleteDataCharacter();
            }
            if (data != null) {
                dataJson = new JSONObject(data);
                JSONObject characterJson;
                JSONArray jsonResult = dataJson.getJSONObject("data").getJSONArray("results");
                for (int i = 0; i < jsonResult.length(); i++) {
                    characterJson = new JSONObject(jsonResult.getString(i));
                    Character objCharacter = new Character();
                    characters.add(validateCharacter(characterJson,characterDAO));
                }
            }
        }else{
             characters=characterDAO.listCharacters();
        }

        return characters;
    }

    // método responsável de tratar os dados obtidos na api
    public Character validateCharacter (JSONObject characterJson, CharacterDAO characterDAO ) throws JSONException {
        Character objCharacter = new Character();

        objCharacter.setName(characterJson.getString("name"));
        objCharacter.setDescription(characterJson.getString("description"));
        if (characterJson.getString("description") == "null" || characterJson.getString("description").isEmpty()) {
            objCharacter.setDescription("Description not found");
        } else {
            objCharacter.setDescription(characterJson.getString("description"));
        }
        objCharacter.setModified(characterJson.getString("modified"));
        objCharacter.setImageUrl(characterJson.optJSONObject("thumbnail").getString("path"));
        objCharacter.setAmountComics(characterJson.optJSONObject("comics").getString("returned"));
        objCharacter.setAmountEvents(characterJson.optJSONObject("events").getString("returned"));
        objCharacter.setImageUrl(characterJson.optJSONObject("thumbnail").getString("path"));
        characterDAO.addCharacter((objCharacter));

        return objCharacter;
    }


}
