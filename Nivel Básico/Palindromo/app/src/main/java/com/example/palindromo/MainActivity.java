package com.example.palindromo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText palabra;
    Button comprobar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palabra = findViewById(R.id.txtPalabra);
        comprobar = findViewById(R.id.btnComprobar);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = (isPalindromo(palabra.getText().toString())) ? "Es palindromo" : "No es palindromo";
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isPalindromo(String palabra){
        StringBuffer buffer = new StringBuffer(palabra);
        String reverse = String.valueOf(buffer.reverse());

        return palabra.equals(reverse);
    }
}