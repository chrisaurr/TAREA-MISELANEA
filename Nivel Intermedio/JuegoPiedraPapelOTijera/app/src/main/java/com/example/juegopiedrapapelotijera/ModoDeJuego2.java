package com.example.juegopiedrapapelotijera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ModoDeJuego2 extends AppCompatActivity {

    ImageView elecJ1, elecCpu, piedra, papel, tijera;
    int[] imgsCpu;
    PPT juego;

    int ptsJ1, ptsCpu;
    TextView contadorJ1, contadorCpu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_de_juego2);

        elecJ1 = findViewById(R.id.eleccionMDJ2);
        elecCpu = findViewById(R.id.eleccionCpuMDJ2);

        piedra = findViewById(R.id.piedraMDJ2);
        papel = findViewById(R.id.papelMDJ2);
        tijera = findViewById(R.id.tijeraMDJ2);

        imgsCpu = new int[3];
        imgsCpu[0] = R.drawable.piedra;
        imgsCpu[1] = R.drawable.papel;
        imgsCpu[2] = R.drawable.tijera;

        contadorJ1 = findViewById(R.id.contadorJ1);
        contadorCpu = findViewById(R.id.contadorCpu);

        ptsJ1 = 0;
        ptsCpu = 0;

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
              //  Log.v("Test", "Empate");
               // ventanaEmergente(0);
                break;

            case 1:
                //Log.v("Test", "Jugador1 ha ganado");
                //ventanaEmergente(1);
                ptsJ1++;
                contadorJ1.setText("Pts: " + ptsJ1);
                break;

            case -1:
               // Log.v("Test", "Computadora ha ganado");
                //ventanaEmergente(-1);
                ptsCpu++;
                contadorCpu.setText("Pts: " + ptsCpu);
                break;

        }

        if((ptsJ1 + ptsCpu) == 3 || (ptsJ1 == 2 || ptsCpu == 2)){
            if(ptsJ1 > ptsCpu){
                ventanaEmergente(1);
            }else{
                ventanaEmergente(-1);
            }
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
                ModoDeJuego2.this.recreate();

            }
        });

        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModoDeJuego2.this.finish();
            }
        });
        builder.show();
    }
}