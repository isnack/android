package com.example.desenvolvimento.challengemarvel.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Comics;

// Classe responsável por exibir os detalhes de um Quadrinho (Comic)

public class ComicsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_details);

        Comics comic=(Comics)getIntent().getSerializableExtra("ComicObject");
        TextView txtDetailsComicTitle = (TextView) findViewById(R.id.txtDetailsComicTitle);
        txtDetailsComicTitle.setText(comic.getTitle());
        TextView txtDetailsComicDescription = (TextView) findViewById(R.id.txtDetailsComicDescription);
        txtDetailsComicDescription.setText(comic.getDescription());
        TextView txtIsbnNumber = (TextView) findViewById(R.id.txtIsbnNumber);
        txtIsbnNumber.setText(comic.getIsbn());
        TextView txtPagesComic = (TextView) findViewById(R.id.txtPagesComic);
        txtPagesComic.setText(comic.getPages());

    }
}
