package com.example.conversorunidades;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UnidadesDistancia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnidadesDistancia extends Fragment{

    Spinner sp1, sp2;
    Button btn;
    TextView resultado;

    EditText medida;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UnidadesDistancia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UnidadesDistancia.
     */
    // TODO: Rename and change types and number of parameters
    public static UnidadesDistancia newInstance(String param1, String param2) {
        UnidadesDistancia fragment = new UnidadesDistancia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unidades_distancia, container, false);

        sp1 = view.findViewById(R.id.spinner1);
        sp2 = view.findViewById(R.id.spinner2);
        resultado = view.findViewById(R.id.resultado);
        btn = view.findViewById(R.id.btnConvertir);
        medida = view.findViewById(R.id.editext);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.unidades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText(converir(Float.parseFloat(medida.getText().toString())));
            }
        });

        String texto = (String) sp1.getSelectedItem();
        Log.v("Prueba", texto);

        return view;
    }

    public String converir(float medida){
        float conversion = 0;

        if(sp1.getSelectedItem().equals("Kilometro") && sp2.getSelectedItem().equals("Kilometro")){
            conversion = medida;
        }

        if(sp1.getSelectedItem().equals("Kilometro") && sp2.getSelectedItem().equals("Metro")){
            conversion = medida * 1000;
        }

        if(sp1.getSelectedItem().equals("Kilometro") && sp2.getSelectedItem().equals("Centimetro")){
            conversion = medida * 100000;
        }


        if(sp1.getSelectedItem().equals("Metro") && sp2.getSelectedItem().equals("Kilometro")){
            conversion = medida / 1000;
        }

        if(sp1.getSelectedItem().equals("Metro") && sp2.getSelectedItem().equals("Metro")){
            conversion = medida;
        }

        if(sp1.getSelectedItem().equals("Metro") && sp2.getSelectedItem().equals("Centimetro")){
            conversion = medida * 100;
        }


        if(sp1.getSelectedItem().equals("Centimetro") && sp2.getSelectedItem().equals("Kilometro")){
            conversion = medida / 100000;
        }

        if(sp1.getSelectedItem().equals("Centimetro") && sp2.getSelectedItem().equals("Metro")){
            conversion = medida / 100;
        }

        if(sp1.getSelectedItem().equals("Centimetro") && sp2.getSelectedItem().equals("Centimetro")){
            conversion = medida;
        }

       // String resultado = String.valueOf(conversion);
        String resultado = String.format("%.2f", conversion);
        return borrarDecimal(resultado);
    }

    private String borrarDecimal(String numero){
        String[] n = numero.split("\\."); //Separamos el punto de la expresión

        //Si tiene un tamaño mayor a uno
        if(n.length > 1){
            if(n[1].equals("0")){ //Y su valor despues del punto es 0
                numero = n[0]; //Omitimos ese valor después del decimal
            }
        }
        return numero;
    }

}