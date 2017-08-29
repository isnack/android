package com.example.desenvolvimento.challengemarvel.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Events;

// Classe responsável por exibir os detalhes de um Evento (Event)

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Events event=(Events)getIntent().getSerializableExtra("EventObject");
        TextView txtDetailsEventTitle = (TextView) findViewById(R.id.txtEventDetailsTitle);
        txtDetailsEventTitle.setText(event.getTitle());
        TextView txtDetailsEventDescription = (TextView) findViewById(R.id.txtEventDetailsDescription);
        txtDetailsEventDescription.setText(event.getDescription());
        TextView txtStartDateEvent = (TextView) findViewById(R.id.txtEventStartDate);
        txtStartDateEvent.setText(event.getStartDate());
        TextView txtEndDateEvent = (TextView) findViewById(R.id.txtEventEndDate);
        txtEndDateEvent.setText(event.getEndDate());
    }
}
