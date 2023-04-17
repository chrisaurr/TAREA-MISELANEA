package com.example.juegopiedrapapelotijera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class ModoDeJuego1 extends AppCompatActivity {

    ImageView elecJ1, elecCpu, piedra, papel, tijera;
    int[] imgsCpu;
    PPT juego;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_de_juego1);

        elecJ1 = findViewById(R.id.eleccion);
        elecCpu = findViewById(R.id.eleccionCpu);

        piedra = findViewById(R.id.piedra);
        papel = findViewById(R.id.papel);
        tijera = findViewById(R.id.tijera);

        imgsCpu = new int[3];
        imgsCpu[0] = R.drawable.piedra;
        imgsCpu[1] = R.drawable.papel;
        imgsCpu[2] = R.drawable.tijera;

        piedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarPartidad(0);
            }
        });

        papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarPartidad(1);
            }
        });

        tijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarPartidad(2);
            }
        });

    }

    public Mano eleccion(int n, ImageView elec){
        Mano j1;

        if(n == 0){
            j1 = Mano.PIEDRA;
        } else if (n == 1) {
            j1 = Mano.PAPEL;
        }else{
            j1 = Mano.TIJERA;
        }

        elec.setImageResource(imgsCpu[n]);

        return j1;
    }

    public int random(){
        Random aleatorio = new Random();
        return aleatorio.nextInt(3);
    }

    public void iniciarPartidad(int n){
        juego = new PPT();
        int noGanador = juego.ganador(eleccion(n, elecJ1), eleccion(random(), elecCpu));

        switch (noGanador){
            case 0:
                Log.v("Test", "Empate");
                ventanaEmergente(0);
                break;

            case 1:
                Log.v("Test", "Jugador1 ha ganado");
                ventanaEmergente(1);
                break;

            case -1:
                Log.v("Test", "Computadora ha ganado");
                ventanaEmergente(-1);
                break;

        }
    }

    public void ventanaEmergente(int n){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title;
        String msj;

        if(n == 0){
            title = "Empate";
            msj = "El resultado \n\n ha sido \n\n un empate";
        } else if (n == 1) {
            title = "J1 has ganado";
            msj = "El resultado \n\n ha dado \n\n como ganador \n\n a jugador 1";
        }else{
            title = "Computadora has ganado";
            msj = "El resultado \n\n ha dado \n\n como ganador \n\n a la computadora";
        }

        builder.setTitle(msj);
        builder.setMessage(msj);
        builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ModoDeJuego1.this.obtenerPalabraRandom();
                ModoDeJuego1.this.recreate();

            }
        });

        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModoDeJuego1.this.finish();
            }
        });
        builder.show();
    }
}