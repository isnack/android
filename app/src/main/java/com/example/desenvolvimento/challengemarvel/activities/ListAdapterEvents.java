package com.example.desenvolvimento.challengemarvel.activities;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Events;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 29/08/2017.
 */

// Classe responsável por realizar o Adapter da lista de eventos (Events) e exibir na tela

public class ListAdapterEvents extends ArrayAdapter<Events> {
    private Context context;
    private ArrayList<Events> events;

    public ListAdapterEvents(Context context, ArrayList<Events> events) {
        super(context,0, events);
        this.context = context;
        this.events =events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Events eventPosition = this.events.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_list_view,null);
        TextView txtViewName = (TextView) convertView.findViewById((R.id.txtViewName));
        txtViewName.setText((eventPosition.getTitle()));

        return convertView;
    }
}
