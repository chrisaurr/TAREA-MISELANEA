package com.example.factorial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText numero;
    Button factorial;
    TextView resultado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.txtNumero);
        factorial = findViewById(R.id.btnFactorial);
        resultado = findViewById(R.id.resultado);

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(calcularFactorial(Integer.parseInt(numero.getText().toString())));
            }
        });
    }

    public String calcularFactorial(int num){
        for(int i = num-1; i > 1; i--){ num = num * i; }
        if(num == 0){ num = 1; }
        return String.valueOf(num);
    }
}