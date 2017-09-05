package com.example.desenvolvimento.challengemarvel.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Character;
import com.example.desenvolvimento.challengemarvel.services.ImageLoadService;

// Classe responsável por exibir os detalhes de um Personagem (Character)

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

        ImageView imageViewCharacter = (ImageView) findViewById(R.id.imageViewCharacter);
        ImageLoadService imageLoadService = new ImageLoadService(imageViewCharacter);
        imageLoadService.execute(character.getImageUrl()+"/portrait_medium.jpg");
    }
}
