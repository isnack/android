package com.example.desenvolvimento.challengemarvel.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */
// Serviço responsável por conectar na Api da Marvel e obter os dados requisitados

public class ApiService extends AsyncTask<Void,Void,String> {

    private String urlString;

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String stringData = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            stringData = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringData;
    }


}
