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
import com.example.desenvolvimento.challengemarvel.models.Comics;
import com.example.desenvolvimento.challengemarvel.services.ComicService;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Classe responsável por exibir a lista de quadrinhos (Comics)

public class ComicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        ListView listView = (ListView) findViewById(R.id.listViewComics);
        boolean isOnline = isOnline();

        ComicService comicService = new ComicService();
        try {
            ArrayList<Comics> comics = comicService.listAllComics(ComicsActivity.this,isOnline);
            ListAdapterComic adapterComic = new ListAdapterComic(ComicsActivity.this, comics);
            listView.setAdapter(adapterComic);

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Comics comic = (Comics) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), ComicsDetailsActivity.class);
                intent.putExtra("ComicObject", comic);
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
