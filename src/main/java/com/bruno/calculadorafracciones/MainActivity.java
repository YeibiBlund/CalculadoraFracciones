package com.bruno.calculadorafracciones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String operacionSeleccionada = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rgOperaciones = findViewById(R.id.rgOperaciones);
        final Button btEjecutar = findViewById(R.id.bEjecutar);
        final Button btReset = findViewById(R.id.bReset);


        final EditText f1Numerador = findViewById(R.id.f1Numerador);
        final EditText f1Denominador = findViewById(R.id.f1Denominador);
        final EditText f2Numerador = findViewById(R.id.f2Numerador);
        final EditText f2Denominador = findViewById(R.id.f2Denominador);
        final TextView f3Numerador = findViewById(R.id.f3Numerador);
        final TextView f3Denominador = findViewById(R.id.f3Denominador);


        rgOperaciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                final RadioButton rbSuma = findViewById(R.id.rbSuma);

                if (checkedId == R.id.rbSuma) {
                    operacionSeleccionada = "suma";
                } else if (checkedId == R.id.rbResta) {
                    operacionSeleccionada = "resta";
                } else if (checkedId == R.id.rbMultiplicacion) {
                    operacionSeleccionada = "multiplicacion";
                } else if (checkedId == R.id.rbDivision) {
                    operacionSeleccionada = "division";
                } else {
                    operacionSeleccionada = ""; // Ninguna operación seleccionada
                }
                // Cuando se selecciona un RadioButton, comprobar si hay cajas vacías
                String f1NumeradorStr = f1Numerador.getText().toString();
                String f1DenominadorStr = f1Denominador.getText().toString();
                String f2NumeradorStr = f2Numerador.getText().toString();
                String f2DenominadorStr = f2Denominador.getText().toString();

                if (comprobarCajasVacias(f1NumeradorStr, f1DenominadorStr, f2NumeradorStr, f2DenominadorStr)) {
                    return; // Si hay cajas vacías, no ejecutar el resto del código
                }

                // Cuando se selecciona un RadioButton, calcular el resultado
                calcularResultado();
            }
        });

        btEjecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Comprobar si se ha seleccionado una operación
                if (operacionSeleccionada.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, seleccione una operación.", Toast.LENGTH_SHORT).show();
                    return; // No continuar si no se ha seleccionado una operación
                }

                // Comprobar si las cajas de entrada están vacías
                String f1NumeradorStr = f1Numerador.getText().toString();
                String f1DenominadorStr = f1Denominador.getText().toString();
                String f2NumeradorStr = f2Numerador.getText().toString();
                String f2DenominadorStr = f2Denominador.getText().toString();

                if (comprobarCajasVacias(f1NumeradorStr, f1DenominadorStr, f2NumeradorStr, f2DenominadorStr)) {
                    return; // Si hay cajas vacías, no ejecutar el resto del método
                }

                // Realizar la operación según la operacionSeleccionada
                int numeradorRes, denominadorRes;

                if (operacionSeleccionada.equals("suma")) {
                    // SUMA
                    numeradorRes = (Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr)) + (Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr));
                    denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
                } else if (operacionSeleccionada.equals("resta")) {
                    // RESTA
                    numeradorRes = (Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr)) - (Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr));
                    denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
                } else if (operacionSeleccionada.equals("multiplicacion")) {
                    // MULTIPLICACION
                    numeradorRes = Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2NumeradorStr);
                    denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
                } else if (operacionSeleccionada.equals("division")) {
                    // DIVISION
                    numeradorRes = Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr);
                    denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr);
                } else {
                    numeradorRes = 0;
                    denominadorRes = 0;
                }

                // Mostrar el resultado
                mostrarResultado(numeradorRes, denominadorRes, f3Numerador, f3Denominador);
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1Numerador.setText("");
                f1Denominador.setText("");
                f2Numerador.setText("");
                f2Denominador.setText("");
                f3Numerador.setText("");
                f3Denominador.setText("");
                rgOperaciones.clearCheck();


            }
        });
    }

    private void mostrarResultado(int numerador, int denominador, TextView numeradorTextView, TextView denominadorTextView) {
        int mcd = calcularMcd(numerador, denominador);
        int numeradorSimplificado = numerador / mcd;
        int denominadorSimplificado = denominador / mcd;

        numeradorTextView.setText(String.valueOf(numeradorSimplificado));
        denominadorTextView.setText(String.valueOf(denominadorSimplificado));
    }

    private int calcularMcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private void calcularResultado() {
        // Esta función calcula el resultado basado en la operación seleccionada
        EditText f1Numerador = findViewById(R.id.f1Numerador);
        EditText f1Denominador = findViewById(R.id.f1Denominador);
        EditText f2Numerador = findViewById(R.id.f2Numerador);
        EditText f2Denominador = findViewById(R.id.f2Denominador);

        TextView f3Numerador = findViewById(R.id.f3Numerador);
        TextView f3Denominador = findViewById(R.id.f3Denominador);

        String f1NumeradorStr = f1Numerador.getText().toString();
        String f1DenominadorStr = f1Denominador.getText().toString();
        String f2NumeradorStr = f2Numerador.getText().toString();
        String f2DenominadorStr = f2Denominador.getText().toString();

        int numeradorRes, denominadorRes;

        if (operacionSeleccionada.equals("suma")) {
            numeradorRes = (Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr)) + (Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr));
            denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
        } else if (operacionSeleccionada.equals("resta")) {
            numeradorRes = (Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr)) - (Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr));
            denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
        } else if (operacionSeleccionada.equals("multiplicacion")) {
            numeradorRes = Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2NumeradorStr);
            denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2DenominadorStr);
        } else if (operacionSeleccionada.equals("division")) {
            numeradorRes = Integer.parseInt(f1NumeradorStr) * Integer.parseInt(f2DenominadorStr);
            denominadorRes = Integer.parseInt(f1DenominadorStr) * Integer.parseInt(f2NumeradorStr);
        } else {
            numeradorRes = 0;
            denominadorRes = 0;
        }

        mostrarResultado(numeradorRes, denominadorRes, f3Numerador, f3Denominador);
    }
    private boolean comprobarCajasVacias(String f1NumeradorStr, String f1DenominadorStr, String f2NumeradorStr, String f2DenominadorStr) {
        if (f1NumeradorStr.isEmpty() || f1DenominadorStr.isEmpty() || f2NumeradorStr.isEmpty() || f2DenominadorStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, asegúrese de no dejar ninguna caja vacía.", Toast.LENGTH_SHORT).show();
            return true; // Devuelve true si hay cajas vacías
        }

            int denominador1 = Integer.parseInt(f1DenominadorStr);
            int denominador2 = Integer.parseInt(f2DenominadorStr);
            if (denominador1 == 0 || denominador2 == 0) {
                Toast.makeText(MainActivity.this, "División entre 0 no permitida.", Toast.LENGTH_SHORT).show();
                return true; // Devuelve true si hay división entre 0
            }


        return false; // Devuelve false si todas las cajas están llenas y no hay división entre 0
    }

}