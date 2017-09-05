package com.example.desenvolvimento.challengemarvel.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desenvolvimento.challengemarvel.R;
import com.example.desenvolvimento.challengemarvel.models.Character;
import com.example.desenvolvimento.challengemarvel.services.ImageLoadService;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento on 26/08/2017.
 */

// Classe responsável por realizar o Adapter da lista de personagens (Characters) e exibir na tela

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
        ImageView imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
        ImageLoadService imageLoadService = new ImageLoadService(imageView2);
        imageLoadService.execute(characterPosition.getImageUrl()+"/portrait_small.jpg");

        return convertView;
    }
}
