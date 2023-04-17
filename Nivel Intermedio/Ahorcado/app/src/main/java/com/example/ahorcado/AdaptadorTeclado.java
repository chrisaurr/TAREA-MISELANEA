package com.example.ahorcado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class AdaptadorTeclado extends BaseAdapter {
    String[] letrasTeclado;
    LayoutInflater tecladoInflater;

    public AdaptadorTeclado(Context context){
        letrasTeclado = new String[26];
        for(int i = 0; i < letrasTeclado.length; i++){
            letrasTeclado[i] = ""+(char)(i+'a');
        }
        tecladoInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return letrasTeclado.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btnLetraTeclado;
        if(convertView == null){
            btnLetraTeclado = (Button) tecladoInflater.inflate(R.layout.btn_letra, parent, false);
        }else{
            btnLetraTeclado = (Button) convertView;
        }

        btnLetraTeclado.setText(letrasTeclado[position]);
        return btnLetraTeclado;
    }
}
