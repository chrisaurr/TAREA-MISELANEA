package com.example.calculadorabasica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String input, output, newOutput;
    TextView resultTv, solutionTv;
    MaterialButton buttonC;
    MaterialButton buttonDivision, buttonMulti, buttonRest, buttonSuma, buttonIgual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.solution);

        colocarId(buttonC, R.id.button_c);
        colocarId(buttonDivision, R.id.button_division);
        colocarId(buttonMulti, R.id.button_multi);
        colocarId(buttonRest, R.id.button_rest);
        colocarId(buttonSuma, R.id.button_suma);
        colocarId(buttonIgual, R.id.button_igual);
        colocarId(button0, R.id.button_0);
        colocarId(button1, R.id.button_1);
        colocarId(button2, R.id.button_2);
        colocarId(button3, R.id.button_3);
        colocarId(button4, R.id.button_4);
        colocarId(button5, R.id.button_5);
        colocarId(button6, R.id.button_6);
        colocarId(button7, R.id.button_7);
        colocarId(button8, R.id.button_8);
        colocarId(button9, R.id.button_9);
        colocarId(buttonAc, R.id.button_ac);
        colocarId(buttonPoint, R.id.button_point);
    }

    //Agregamos id correspondiente y una escucha del evento click a cada botón
    void colocarId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    //Sobreescribimos método para ejcutar una acción determinada cada vez que se presiona cierto botón
    @Override
    public void onClick(View view) {
        //Hacemos de la vista un botón
        MaterialButton button = (MaterialButton) view;

        //Obtenemos el texto de cada botón que se presione
        String buttonText = button.getText().toString();

        //Switch que valua el texto de cada botón
        switch(buttonText){
            case "AC": //Seteamos valores cuando se presione el botón con el texto AC
                input = null;
                output = null;
                newOutput = null;
                resultTv.setText("");
                break;

            case "*": //Operamos y concatenamos el simbolo a la operación
                resolver();
                input += "*";
                break;

            case "=": //Operamos la expresion ingresada hasta el momento
                resolver();
                break;


            case "C": //Eliminar el ultimo digito introducido de presionar el botón con el texto C
                if(input.length() > 0){
                    input = input.substring(0, input.length() - 1);
                }
                break;

            //En cualquier otro caso no definido
            default:
                if(input == null){ //Si el input esta nulo
                    input = ""; //Lo igualamos a un String vacio
                }

                //En el caso que se presione el simbolo de suma, división o resta operamos
                if(buttonText.equals("+") || buttonText.equals("/") || buttonText.equals("-")){
                    resolver();
                }

                //Agregamos text de ese botón presionado
                input += buttonText;
        }
        solutionTv.setText(input); //Mostramos lo que se ha digitado
    }

    //Función que resulve cada expresion
    private void resolver(){
        //Cada simbolo π reemplazarlo por 3.1416

        //Si hay dos valores separados por un simbolo +
        if(input.split("\\+").length == 2){
            String[] nums = input.split("\\+"); //Dividimos la expresion en subcadenas donde se quiera sumar
            try {
                //Sumamos primer valor con el segundo
                double d = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                output = Double.toString(d); //Lo almacenamos en una varible
                newOutput = borrarDecimal(output); //Eliminamos decimales innecesarios de la expresión
                resultTv.setText(newOutput); //Lo mostramos en pantalla
                input = d +""; //Permite ir reduciendo la expresión hasta llegar al resultado
            }catch (Exception e){
                resultTv.setError(e.getMessage().toString());
            }
        }

        //Si hay dos valores separados por un simbolo *
        if(input.split("\\*").length == 2){
            String[] nums = input.split("\\*"); //Dividimos la expresion en subcadenas donde se quiera multiplicar
            try {
                //multiplicamos primer valor con el segundo
                double d = Double.parseDouble(nums[0]) * Double.parseDouble(nums[1]);
                output = Double.toString(d); //Lo almacenamos en una varible
                newOutput = borrarDecimal(output); //Eliminamos decimales innecesarios de la expresión
                resultTv.setText(newOutput); //Lo mostramos en pantalla
                input = d +""; //Permite ir reduciendo la expresión hasta llegar al resultado
            }catch (Exception e){
                resultTv.setError(e.getMessage().toString());
            }
        }


        //Si hay dos valores separados por un simbolo /
        if(input.split("\\/").length == 2){
            String[] nums = input.split("\\/"); //Dividimos la expresion en subcadenas donde se quiera dividir
            try {
                //Dividimos el primer valor sobre el segundo
                double d = Double.parseDouble(nums[0]) / Double.parseDouble(nums[1]);
                output = Double.toString(d); //Lo almacenamos en una varible
                newOutput = borrarDecimal(output); //Eliminamos decimales innecesarios de la expresión
                resultTv.setText(newOutput); //Lo mostramos en pantalla
                input = d +""; //Permite ir reduciendo la expresión hasta llegar al resultado
            }catch (Exception e){
                resultTv.setError(e.getMessage().toString());
            }
        }

        //Si hay dos valores separados por un simbolo -
        if(input.split("\\-").length == 2){
            String[] nums = input.split("\\-"); //Dividimos la expresion en subcadenas donde se quiera restar
            try {
                //Existen dos casos
                //Si el numero se le resta una cantidad mayor a el, la expresión será negativa
                if(Double.parseDouble(nums[0]) < Double.parseDouble(nums[1])){
                    //Al segundo valor le restamos el primero
                    double d = Double.parseDouble(nums[1]) - Double.parseDouble(nums[0]);
                    output = Double.toString(d); //Lo almacenamos en una varible
                    newOutput = borrarDecimal(output); //Eliminamos decimales innecesarios de la expresión
                    resultTv.setText("-" + newOutput); //Lo mostramos en pantalla
                    input = d +""; //Permite ir reduciendo la expresión hasta llegar al resultado
                }else{ //Del caso contrario la esxpresión será positiva
                    //Al primer valor le restamos el segundo
                    double d = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
                    output = Double.toString(d); //Lo almacenamos en una varible
                    newOutput = borrarDecimal(output); //Eliminamos decimales innecesarios de la expresión
                    resultTv.setText(newOutput); //Lo mostramos en pantalla
                    input = d +""; //Permite ir reduciendo la expresión hasta llegar al resultado
                }

            }catch (Exception e){ //Si la expresión no es valida mostrar error
                resultTv.setError(e.getMessage().toString());
            }
        }
    }

    //Función para borrar decimales
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