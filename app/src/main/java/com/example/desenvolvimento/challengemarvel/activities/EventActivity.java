package com.example.desenvolvimento.challengemarvel.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Events;
import com.example.desenvolvimento.challengemarvel.services.EventService;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Classe responsável por exibir a lista de Eventos (Events)

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ListView listView = (ListView) findViewById(R.id.listViewEvents);
        EventService eventService = new EventService();
        boolean isOnline = isOnline();
        try {
            ArrayList<Events> events = eventService.listAllEvents(EventActivity.this,isOnline());
            ListAdapterEvents adapterEvents = new ListAdapterEvents(EventActivity.this, events);
            listView.setAdapter(adapterEvents);

        } catch (ExecutionException | InterruptedException | JSONException  e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Events event = (Events) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), EventDetailsActivity.class);
                intent.putExtra("EventObject", event);
                startActivity(intent);
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            return true;
        } else {
            // not connected to the internet
            return false;
        }
    }
}
