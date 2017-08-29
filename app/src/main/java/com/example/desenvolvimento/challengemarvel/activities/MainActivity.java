package com.example.desenvolvimento.challengemarvel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.activities.CharacterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnOpenCharacterActivity (View view){
        Intent intent = new Intent(this,CharacterActivity.class);
        startActivity(intent);
    }
    public void btnOpenComicsActivity (View view){
        Intent intent = new Intent(this,ComicsActivity.class);
        startActivity(intent);
    }
    public void btnOpenEventActivity (View view){
        Intent intent = new Intent(this,EventActivity.class);
        startActivity(intent);
    }
}
