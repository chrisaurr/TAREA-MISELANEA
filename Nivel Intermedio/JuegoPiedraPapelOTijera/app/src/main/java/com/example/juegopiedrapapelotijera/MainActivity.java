package com.example.juegopiedrapapelotijera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button modoJ1, modoJ2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modoJ1 = findViewById(R.id.btnModoDeJuego1);
        modoJ2 = findViewById(R.id.btnModoDeJuego2);

        modoJ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModoDeJuego1.class);
                startActivity(intent);
            }
        });

        modoJ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModoDeJuego2.class);
                startActivity(intent);
            }
        });
    }
}