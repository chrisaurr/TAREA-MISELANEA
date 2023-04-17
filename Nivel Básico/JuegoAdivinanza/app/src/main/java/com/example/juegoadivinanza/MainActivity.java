package com.example.juegoadivinanza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText num;
    Button adivinar;
    TextView resultado;

    int numeroAleatorio;

    public static MainActivity myContext;

    public MainActivity(){
        myContext = this;
    }

    public static MainActivity getInstance(){
        return myContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.txtNumero);
        adivinar = findViewById(R.id.btnAdivinar);
        resultado = findViewById(R.id.resultado);

        generarNumero();
        Log.v("Test", String.valueOf(numeroAleatorio));

        adivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor = Integer.parseInt(num.getText().toString());
                if(valor > numeroAleatorio){
                    Toast.makeText(getApplicationContext(), "Intente con un numero menor", Toast.LENGTH_LONG).show();
                }else if(valor < numeroAleatorio){
                    Toast.makeText(getApplicationContext(), "Intente con un numero mayor", Toast.LENGTH_LONG).show();
                }else{
                    //resultado.setText("Numero adivinado, fin del juego!!");
                  /*  try {
                        Thread.sleep(7000);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/
                  /*  try {
                        TimeUnit.SECONDS.sleep(2);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getInstance());
                    builder.setTitle("Has ganado");
                    builder.setMessage("Numero adivinado \n\n fin del juego!!");
                    builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            num.setText("");
                            MainActivity.this.recreate();
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
            }
        });

    }

    public void generarNumero(){
        Random generadorAleatorios = new Random();
        numeroAleatorio = 1+generadorAleatorios.nextInt(100);
    }
}