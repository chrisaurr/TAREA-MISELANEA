package com.example.seriefibonacci;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText numero;
    TextView sucesion;
    Button mostrar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.txtNumero);
        sucesion = findViewById(R.id.resultado);
        mostrar = findViewById(R.id.btnSerie);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucesion.setText(fibonacci(Integer.parseInt(numero.getText().toString())));
            }
        });
    }

    public String fibonacci(int num){
        List<Integer> lista = new ArrayList<Integer>();
        int i = 0;

        int sucesion;
        String fibonacci = "";

        lista.add(1);
        lista.add(1);

        do{
            sucesion = lista.get(i) + lista.get(i+1);
            lista.add(sucesion);
            fibonacci = fibonacci + lista.get(i) + " ";

            if(lista.get(i) > num){
                return "No existe sucesion para ese número";
            }
            i++;
        }while(lista.get(i) != num);

        return fibonacci + "" + num;
    }
}