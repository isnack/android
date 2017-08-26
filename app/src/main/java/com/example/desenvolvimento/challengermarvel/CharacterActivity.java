package com.example.desenvolvimento.challengermarvel;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        new DownloadDados().execute();
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
            dialog = ProgressDialog.show(CharacterActivity.this, "Aguarde",
                    "Obterndo dados");
        }
        @Override
        protected void onPostExecute(String dados) {
           ListView listView = (ListView) findViewById(R.id.listViewCharacter);

            JSONObject dadosJson = null;
            JSONObject dadosJson2 = null;
            ArrayList<Character> listCharacters =null;

           try {
               dadosJson =  stringToJson(dados);
               dadosJson2=dadosJson;
            //   listCharacters = getCharacter(dadosJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

           // ListAdapterCharacter adapterCharacter =new ListAdapterCharacter(CharacterActivity.this,listCharacters);
           // listView.setAdapter(adapterCharacter);
            dialog.dismiss();

        }
        private JSONObject stringToJson(String string) throws JSONException {


            JSONObject  json = new JSONObject(string);

            return json;

        }
        private ArrayList<Character> getCharacter (JSONArray json) throws JSONException {
            ArrayList<Character> characters = new ArrayList<Character>();
            JSONObject characterJson;
            for (int i = 0; i < json.length(); i++) {
                characterJson = new JSONObject(json.getString(i));
                Log.i("Character ENCONTRADO: ",
                        "name=" + characterJson.getString("name"));

                Character objCharacter = new Character();
                objCharacter.setName(characterJson.getString("name"));
                objCharacter.setDescription(characterJson.getString("description"));
                characters.add(objCharacter);
            }
            return characters;
        }
    }
}
