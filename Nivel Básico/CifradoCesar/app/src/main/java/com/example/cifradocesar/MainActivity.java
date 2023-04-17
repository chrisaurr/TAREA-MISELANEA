package com.example.cifradocesar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText cadena;
    Button cifrar, descifrar;
    TextView resultado;

    final String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    String codificado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadena = findViewById(R.id.txtCadena);
        cifrar = findViewById(R.id.btnCifrar);
        descifrar = findViewById(R.id.btnDescifrar);
        resultado = findViewById(R.id.resultado);

        cifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codificado = codificar(letras, cadena.getText().toString());
                resultado.setText(codificado);
            }
        });

        descifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(descodificar(letras, codificado));
            }
        });

    }

    public String codificar(String letras, String texto){
        String textoCodificado = "";

        texto = texto.toUpperCase();

        char caracter;
        for (int i = 0; i < texto.length(); i++) {
            caracter = texto.charAt(i);

            int pos = letras.indexOf(caracter);

            if(pos == -1){
                textoCodificado += caracter;
            }else{
                textoCodificado += letras.charAt( (pos + 3) % letras.length() );
            }

        }

        return textoCodificado;
    }

    public String descodificar(String letras, String texto){
        String textoDescodificado = "";

        texto = texto.toUpperCase();

        char caracter;
        for (int i = 0; i < texto.length(); i++) {
            caracter = texto.charAt(i);

            int pos = letras.indexOf(caracter);

            if(pos == -1){
                textoDescodificado += caracter;
            }else{
                if(pos - 3 < 0){
                    textoDescodificado += letras.charAt( letras.length() + (pos - 3) );
                }else{
                    textoDescodificado += letras.charAt( (pos - 3) % letras.length() );
                }
            }

        }

        return textoDescodificado;
    }
}