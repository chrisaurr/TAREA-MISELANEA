package com.example.ordenamientoarreglo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText valores;
    TextView ordenado;
    Button ordenar;

    public String[] miArreglo;
    public Float[] datos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valores = findViewById(R.id.txtValores);
        ordenado = findViewById(R.id.resultado);
        ordenar = findViewById(R.id.btnOrdenar);

        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ordenado.setText(ordenarArreglo(miArreglo));
            }
        });

    }

    public String ordenarArreglo(String[] arr){

        miArreglo = valores.getText().toString().split(" ");
        datos = new Float[miArreglo.length];

        for(int i = 0; i < miArreglo.length; i++){
            datos[i] = Float.parseFloat(miArreglo[i]);
            //sum = sum + datos[i];
        }

        Arrays.sort(datos);

        return Arrays.toString(datos);
    }
}