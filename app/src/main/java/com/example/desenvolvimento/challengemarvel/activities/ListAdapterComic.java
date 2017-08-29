package com.example.desenvolvimento.challengemarvel.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Comics;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 28/08/2017.
 */

// Classe responsável por realizar o Adapter da lista de quadrinhos (Comics) e exibir na tela

public class ListAdapterComic extends ArrayAdapter<Comics> {
    private Context context;
    private ArrayList<Comics> comics;

    public ListAdapterComic(Context context, ArrayList<Comics> comics) {
        super(context,0, comics);
        this.context = context;
        this.comics =comics;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Comics comicPosition = this.comics.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_list_view,null);
        TextView txtViewName = (TextView) convertView.findViewById(R.id.txtViewName);
        txtViewName.setText(comicPosition.getTitle());

        return convertView;
    }
}
