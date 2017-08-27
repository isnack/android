package com.example.desenvolvimento.challengemarvel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 26/08/2017.
 */

public class ListAdapterCharacter extends ArrayAdapter<Character> {
    private Context context;
    private ArrayList<Character> lista;
    public ListAdapterCharacter(Context context, ArrayList<Character> lista) {
        super(context,0, lista);
        this.context = context;
        this.lista =lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Character characterPosition = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_list_view,null);

        TextView txtViewName = (TextView) convertView.findViewById(R.id.txtViewName);
        txtViewName.setText(characterPosition.getName());

        return convertView;
    }
}
