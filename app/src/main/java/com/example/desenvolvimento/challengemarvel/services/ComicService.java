package com.example.desenvolvimento.challengemarvel.services;

import android.content.Context;

import com.example.desenvolvimento.challengemarvel.dao.ComicDAO;
import com.example.desenvolvimento.challengemarvel.models.Comics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */

// Serviço responsável por buscar na ApiService e  montar uma lista de Comics e retornar na View(Activity)

public class ComicService {

    public ArrayList<Comics> listAllComics(Context context, boolean isOnline) throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Comics> comics = new ArrayList<Comics>();
        ComicDAO comicDAO = new ComicDAO(context);
        JSONObject dataJson;
        if (isOnline) {
            ApiService apiService = new ApiService();
            apiService.setUrlString("http://gateway.marvel.com/v1/public/comics?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
            String data = apiService.execute().get();
            if (comicDAO.listComics().size() != 0) {
                comicDAO.deleteDataComics();
            }

            if (data != null) {
                dataJson = new JSONObject(data);
                JSONObject comicJson;
                JSONArray jsonResult = dataJson.getJSONObject("data").getJSONArray("results");
                for (int i = 0; i < jsonResult.length(); i++) {
                    comicJson = new JSONObject(jsonResult.getString(i));
                    comics.add(validateComic(comicJson, comicDAO));
                }
            }
        } else {
            comics = comicDAO.listComics();
        }

        return comics;
    }
    // método responsável de tratar os dados obtidos na api
    public Comics validateComic(JSONObject comicJson, ComicDAO comicDAO) throws JSONException {
        Comics objComic = new Comics();
        objComic.setTitle(comicJson.getString("title"));
        if (comicJson.getString("description") == "null" || comicJson.getString("description").isEmpty()) {
            objComic.setDescription("Description not found");
        } else {
            objComic.setDescription(comicJson.getString("description"));
        }
        if (comicJson.getString("isbn") == "null" || comicJson.getString("isbn").isEmpty()) {
            objComic.setIsbn("Isbn not found");
        } else {
            objComic.setIsbn(comicJson.getString("isbn"));
        }
        objComic.setModified(comicJson.getString("modified"));
        objComic.setPages(comicJson.getString("pageCount"));
        comicDAO.addComic(objComic);

        return objComic;
    }
}
