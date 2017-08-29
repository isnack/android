package com.example.desenvolvimento.challengemarvel.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Character;
import com.example.desenvolvimento.challengemarvel.services.CharacterService;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Classe responsável por exibir a lista de personagens (Characters)

public class CharacterActivity extends AppCompatActivity {
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ListView listView = (ListView) findViewById(R.id.listViewCharacter);

        CharacterService characterService = new CharacterService();
        try {
            ArrayList<Character> characters = characterService.listAllCharacters();
            ListAdapterCharacter adapterCharacter = new ListAdapterCharacter(CharacterActivity.this, characters);
            listView.setAdapter(adapterCharacter);

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Character character = (Character) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), CharacterDetailsActivity.class);
                intent.putExtra("CharacterObject", character);
                startActivity(intent);
            }
        });

    }


}
