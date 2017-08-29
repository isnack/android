package com.example.desenvolvimento.challengemarvel.services;

import com.example.desenvolvimento.challengemarvel.models.Events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */

// Serviço responsável por buscar na ApiService e  montar uma lista de Events e retornar na View(Activity)

public class EventService {

    public ArrayList<Events> listAllEvents() throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Events> events = new ArrayList<Events>();
        ApiService apiService = new ApiService();
        apiService.setUrlString("http://gateway.marvel.com/v1/public/events?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
        String data = apiService.execute().get();
        JSONObject dataJson;



        if (data != null) {
            dataJson = new JSONObject(data);
            JSONObject eventJson;
            JSONArray jsonResult = dataJson.getJSONObject("data").getJSONArray("results");
            for (int i = 0; i < jsonResult.length(); i++) {
                eventJson = new JSONObject(jsonResult.getString(i));

                Events objEvent = new Events();
                objEvent.setTitle(eventJson.getString("title"));
                if (eventJson.getString("description") == "null") {
                    objEvent.setDescription("Description not found");
                } else {
                    objEvent.setDescription(eventJson.getString("description"));
                }
                objEvent.setModified(eventJson.getString("modified"));
                objEvent.setStartDate(eventJson.getString("start"));
                objEvent.setEndDate(eventJson.getString("end"));


                events.add(objEvent);
            }

        }
        return events;

    }
}
