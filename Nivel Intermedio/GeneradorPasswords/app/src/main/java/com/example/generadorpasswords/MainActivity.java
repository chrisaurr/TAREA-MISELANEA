package com.example.generadorpasswords;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText passwordSize;
    Button generar;
    Switch addMayusculas, addNumeros, addSignos;
    TextView resultado;

    ImageView copiar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordSize = findViewById(R.id.txtSize);
        generar = findViewById(R.id.btnGenerar);
        addMayusculas = findViewById(R.id.mayusculas);
        addNumeros = findViewById(R.id.numeros);
        addSignos = findViewById(R.id.signos);
        resultado = findViewById(R.id.password);
        copiar = findViewById(R.id.copyImg);

        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(randomPassword(passwordSize, addMayusculas, addNumeros, addSignos));
            }
        });

        copiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy", resultado.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "Contraseña copiada", Toast.LENGTH_LONG).show();
            }
        });
    }

    public String randomPassword(EditText tam, Switch mayus, Switch nums, Switch signos){
        String cadena = "abcdefghijklmnñopqrstuvwxyz";
        int size = 0;
        try{
            size = Integer.parseInt(tam.getText().toString());
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        if(size < 6){
            return "El tamaño debe de ser minimo de 6";
        }

        if(mayus.isChecked()){
            cadena += "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        }

        if(nums.isChecked()){
            cadena += "1234567890";
        }

        if(signos.isChecked()){
            cadena += "|°!#$%&/()=¿?-.,;:{}[]@+*¡";
        }

        char[] caracteres = cadena.toCharArray();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size; i++){
            char c = caracteres[random.nextInt(caracteres.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}