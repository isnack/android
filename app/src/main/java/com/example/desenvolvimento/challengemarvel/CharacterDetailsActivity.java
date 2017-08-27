package com.example.desenvolvimento.challengemarvel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CharacterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        Character character=(Character)getIntent().getSerializableExtra("CharacterObject");

        TextView txtDetailsCharName = (TextView) findViewById(R.id.txtDetailsCharName);
        txtDetailsCharName.setText(character.getName());

        TextView txtDetailsCharDescription = (TextView) findViewById(R.id.txtDetailsCharDescription);
        txtDetailsCharDescription.setText(character.getDescription());

        TextView txtAmountComics = (TextView) findViewById(R.id.txtAmountComics);
        txtAmountComics.setText(character.getAmountComics());
        
        TextView txtAmountEvents = (TextView) findViewById(R.id.txtAmountEvents);
        txtAmountEvents.setText(character.getAmountEvents());
    }
}
