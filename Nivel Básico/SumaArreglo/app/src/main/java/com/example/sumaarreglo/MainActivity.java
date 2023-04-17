package com.example.sumaarreglo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText valores;
    TextView sumatoria;
    Button sumar;

    public String[] miArreglo;
    public Float[] datos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valores = findViewById(R.id.txtValores);
        sumatoria = findViewById(R.id.resultado);
        sumar = findViewById(R.id.btnSumar);

        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumatoria.setText(sumaArreglo(miArreglo));
            }
        });

    }

   public String sumaArreglo(String[] arr){
        float sum = 0;

       miArreglo = valores.getText().toString().split(" ");
       datos = new Float[miArreglo.length];

       for(int i = 0; i < miArreglo.length; i++){
           datos[i] = Float.parseFloat(miArreglo[i]);
           sum = sum + datos[i];
       }

        return borrarDecimal(String.valueOf(sum));
   }

   public String borrarDecimal(String numero){
        String[] n = numero.split("\\.");

        if(n.length > 1){
            if(n[1].equals("0")){
                numero = n[0];
            }
        }

        return numero;
   }
}