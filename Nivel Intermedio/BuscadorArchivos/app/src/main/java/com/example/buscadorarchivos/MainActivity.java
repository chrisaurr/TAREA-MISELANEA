package com.example.buscadorarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<String> nombres;
    List<String> rutas;
    ArrayAdapter<String> adapter;
    String raiz;
    TextView rutaActual;
    ListView listasFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rutaActual = findViewById(R.id.ruta);
        listasFiles = findViewById(R.id.lvDirectorios);

        raiz = Environment.getExternalStorageDirectory().getPath();

        listasFiles.setOnItemClickListener(this);
        entrarDirectorio(raiz);
    }

    public void entrarDirectorio(String ruta){
        nombres = new ArrayList<String>();
        rutas = new ArrayList<String>();
        int i = 0;

        File directorio = new File(ruta);
        File[] lista = directorio.listFiles();

        if(!ruta.equals(raiz)){
            nombres.add("../");
            rutas.add(directorio.getParent());
            i = 1;
        }

        for(File file : lista){
            rutas.add(file.getPath());
        }

        Collections.sort(rutas, String.CASE_INSENSITIVE_ORDER);

        for(int x = i; x < rutas.size(); x++){
            File archivo = new File(rutas.get(x));

            if(archivo.isFile()){
                nombres.add(archivo.getName());
            }else{
                nombres.add("/" + archivo.getName());
            }
        }

        if(lista.length < 1){
            nombres.add("No hay ningún archivo");
            rutas.add(ruta);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.lv_directorios, nombres);
        listasFiles.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            File archivo = new File(rutas.get(position));


            if(archivo.isFile()){
                Toast.makeText(this, "Archivo seleccionado: " + archivo.getName(), Toast.LENGTH_LONG).show();
            }else{
                entrarDirectorio(rutas.get(position));
            }
        }catch (NullPointerException | IndexOutOfBoundsException e){
            e.printStackTrace();
            File archivo = new File(rutas.get(0));


            if(archivo.isFile()){
                Toast.makeText(this, "Archivo seleccionado: " + archivo.getName(), Toast.LENGTH_LONG).show();
            }else{
                entrarDirectorio(rutas.get(0));
            }
        }


    }
}