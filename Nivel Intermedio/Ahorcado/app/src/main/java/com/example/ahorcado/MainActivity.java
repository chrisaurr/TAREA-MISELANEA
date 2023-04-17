package com.example.ahorcado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] palabras;
    Random posArr;
    String ultimaPalabra;
    TextView[] letrasPalabra;
    LinearLayout contenedorLetrasPalabra;
    AdaptadorTeclado adaptador;
    ImageView[] imgsAhorcado;
    int noImg;
    GridView teclado;
    Button btnLetras;

    int numeroAciertos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        palabras = getResources().getStringArray(R.array.listaPalabras);
        posArr = new Random();
        contenedorLetrasPalabra = findViewById(R.id.contenedorPalabra);
        teclado = findViewById(R.id.teclado);
        //btnLetras = findViewById(R.id.letras);
        imgsAhorcado = new ImageView[6];
        imgsAhorcado[0] = findViewById(R.id.cabeza);
        imgsAhorcado[1] = findViewById(R.id.cuerpo);
        imgsAhorcado[2] = findViewById(R.id.brazoIzq);
        imgsAhorcado[3] = findViewById(R.id.brazoDer);
        imgsAhorcado[4] = findViewById(R.id.piernaIzq);
        imgsAhorcado[5] = findViewById(R.id.piernaDer);


        obtenerPalabraRandom();

       /* btnLetras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerInfoButton(v);
            }
        });*/

    }

    public void obtenerPalabraRandom(){
        String palabra = palabras[posArr.nextInt(palabras.length)];

        while(palabra.equals(ultimaPalabra)){
            palabra = palabras[posArr.nextInt(palabras.length)];
        }

        ultimaPalabra = palabra;
        letrasPalabra = new TextView[ultimaPalabra.length()];
        contenedorLetrasPalabra.removeAllViews();
        for(int i = 0; i < ultimaPalabra.length(); i++){
            letrasPalabra[i] = new TextView(this);
            letrasPalabra[i].setText(""+ultimaPalabra.charAt(i));
            letrasPalabra[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            letrasPalabra[i].setGravity(Gravity.CENTER);
            letrasPalabra[i].setTextColor(Color.WHITE);
            letrasPalabra[i].setBackgroundResource(R.drawable.letter_bg);
            contenedorLetrasPalabra.addView(letrasPalabra[i]);
        }

        letrasPalabra[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Prueba", "Click");
            }
        });

        adaptador = new AdaptadorTeclado(this);
        teclado.setAdapter(adaptador);
        numeroAciertos = 0;
        noImg = 0;

        for(int i = 0; i < 6; i++){
            imgsAhorcado[i].setVisibility(View.INVISIBLE);
        }
    }

    public void obtenerInfoButton(View v){
        String letra = ((TextView) v).getText().toString();
        char caracterBtn = letra.charAt(0);

        v.setEnabled(false);
        boolean palabraContieneLetra = false;

        for(int i = 0; i < ultimaPalabra.length(); i++){
            if(ultimaPalabra.charAt(i)==caracterBtn){
                palabraContieneLetra = true;
                numeroAciertos++;
                letrasPalabra[i].setTextColor(Color.BLACK);
            }
        }

        if(palabraContieneLetra){
            if(numeroAciertos == ultimaPalabra.length()){
                deshabilitarBotones();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Has ganado");
                builder.setMessage("Has logrado \n\n adivinar la palabra \n\n con los intentos permitidos");
                builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.obtenerPalabraRandom();
                    }
                });

                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                builder.show();
            }
        } else if (noImg < 6) {
            imgsAhorcado[noImg].setVisibility(View.VISIBLE);
            noImg++;
        }else{
            deshabilitarBotones();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Has perdido");
            builder.setMessage("Superaste el numero \n\n el numero de intentos \n\n para adivinar la palabra");
            builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.obtenerPalabraRandom();
                }
            });

            builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });builder.show();
        }
    }

    public void deshabilitarBotones(){
        for(int i = 0; i < teclado.getChildCount(); i++){
            teclado.getChildAt(i).setEnabled(false);
        }
    }
}