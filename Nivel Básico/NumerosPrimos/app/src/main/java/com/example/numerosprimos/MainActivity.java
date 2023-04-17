package com.example.numerosprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numero;
    Button comprobar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.txtNumero);
        comprobar = findViewById(R.id.btnComprobar);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mostrar = (isNumeroPrimo(Integer.parseInt(numero.getText().toString())) ? "Es un número primo" : "No es un número primo");
                Toast.makeText(getApplicationContext(), mostrar, Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isNumeroPrimo(int num){
        for(int i=2;i<num;i++) {
            if(num%i==0)
                return false;
        }
        return true;
    }
}