package com.example.desenvolvimento.challengemarvel.services;

import android.content.Context;
import com.example.desenvolvimento.challengemarvel.dao.EventDAO;
import com.example.desenvolvimento.challengemarvel.models.Events;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */

// Serviço responsável por buscar na ApiService e  montar uma lista de Events e retornar na View(Activity)

public class EventService {

    public ArrayList<Events> listAllEvents(Context context, boolean isOnline) throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Events> events = new ArrayList<Events>();
        EventDAO eventDAO = new EventDAO(context);
        JSONObject dataJson;
        if (isOnline) {
            ApiService apiService = new ApiService();
            apiService.setUrlString("http://gateway.marvel.com/v1/public/events?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
            String data = apiService.execute().get();
            if (eventDAO.listEvents().size() != 0) {
                eventDAO.deleteDataEvents();
            }
            if (data != null) {
                dataJson = new JSONObject(data);
                JSONObject eventJson;
                JSONArray jsonResult = dataJson.getJSONObject("data").getJSONArray("results");
                for (int i = 0; i < jsonResult.length(); i++) {
                    eventJson = new JSONObject(jsonResult.getString(i));
                    events.add(validateEvent(eventJson, eventDAO));
                }
            }
        }else{
            events=eventDAO.listEvents();
        }
            return events;
        }

    // método responsável de tratar os dados obtidos na api
    public Events validateEvent (JSONObject eventJson, EventDAO eventDAO) throws JSONException {
        Events objEvent = new Events();

        objEvent.setTitle(eventJson.getString("title"));
        if (eventJson.getString("description") == "null" || eventJson.getString("description").isEmpty()) {
            objEvent.setDescription("Description not found");
        } else {
            objEvent.setDescription(eventJson.getString("description"));
        }
        if (eventJson.getString("start") == "null" || eventJson.getString("start").isEmpty()) {
            objEvent.setDescription("Start date  not found");
        } else {
            objEvent.setDescription(eventJson.getString("end"));
        }
        if (eventJson.getString("end") == "null" || eventJson.getString("end").isEmpty()) {
            objEvent.setDescription("End date not found");
        } else {
            objEvent.setDescription(eventJson.getString("end"));
        }
        objEvent.setModified(eventJson.getString("modified"));
        eventDAO.addEvent(objEvent);

        return objEvent;
    }
}
