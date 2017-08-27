package com.example.desenvolvimento.challengemarvel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ListView listView = (ListView) findViewById(R.id.listViewCharacter);

        new DownloadDados().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Character character = (Character) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(),CharacterDetailsActivity.class);
                intent.putExtra("CharacterObject",  character);
                startActivity(intent);
            }
        });

    }


    class DownloadDados extends AsyncTask<Void, Void, String> {
        ProgressDialog dialog;
        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=5093e174b0cd1c8e382cc820228cdfbc&hash=f650a4000b2f109477e669ae78f54938");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String linha;
                StringBuffer buffer = new StringBuffer();
                while((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");
                }

                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            return null;
        }
        //Exibe pop-up indicando que está sendo feito o download do JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(CharacterActivity.this, "Wait",
                    "Getting data");
        }
        @Override
        protected void onPostExecute(String dados) {
           ListView listView = (ListView) findViewById(R.id.listViewCharacter);

            JSONObject dadosJson = null;
            ArrayList<Character> listCharacters =null;

           try {
               dadosJson =  stringToJson(dados);
               listCharacters = getCharacter(dadosJson);

            } catch (JSONException e) {
                e.printStackTrace();
            }

           ListAdapterCharacter adapterCharacter =new ListAdapterCharacter(CharacterActivity.this,listCharacters);
           listView.setAdapter(adapterCharacter);
            dialog.dismiss();

        }
        private JSONObject stringToJson(String string) throws JSONException {


            JSONObject  json = new JSONObject(string);

            return json;

        }
        private ArrayList<Character> getCharacter (JSONObject json) throws JSONException {
            ArrayList<Character> characters = new ArrayList<Character>();
            JSONObject characterJson;
            JSONObject jsonObjectData = json.optJSONObject("data");
            JSONArray jsonResult = jsonObjectData.getJSONArray("results");
            for (int i = 0; i < jsonResult.length(); i++) {
                characterJson = new JSONObject(jsonResult.getString(i));
                Log.i("Character ENCONTRADO: ",
                        "name=" + characterJson.getString("name"));

                Character objCharacter = new Character();
                objCharacter.setName(characterJson.getString("name"));
                objCharacter.setDescription(characterJson.getString("description"));
                objCharacter.setModified(characterJson.getString("modified"));
                objCharacter.setImageUrl(characterJson.optJSONObject("thumbnail").getString("path"));
                objCharacter.setAmountComics(characterJson.optJSONObject("comics").getString("returned"));
                objCharacter.setAmountEvents(characterJson.optJSONObject("events").getString("returned"));
                characters.add(objCharacter);
            }
            return characters;
        }
    }
}
