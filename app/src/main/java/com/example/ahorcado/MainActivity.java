package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String palabraOculta = "CETYS";
    int numeroDeFallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ventanaJuego, new VentanaAhorcadoFragment())
                    .commit();
        }

    }

    public void botonPulsado(View vista){
        Button boton = (Button) findViewById(vista.getId()); //leo el boton que ha sido pulsado
        //boton.setVisibility(View.INVISIBLE); //oculta el botón
        boton.setEnabled(false); //deshabilito el botón
        chequeaLetra(boton.getText().toString());

    }

    private void chequeaLetra(String letra){
        letra.toUpperCase();
        TextView textoGuiones = (TextView) findViewById(R.id.palabraConGuiones);
        String palabraConGuiones = textoGuiones.getText().toString();
        boolean acierto = false;
        for (int i=0; i<palabraOculta.length(); i++){
            if (palabraOculta.charAt(i) == letra.charAt(0)){
                palabraConGuiones = palabraConGuiones.substring(0, 2*i)
                        +letra
                        +palabraConGuiones.substring(2*i + 1);
                acierto = true;
            }
        }
        textoGuiones.setText(palabraConGuiones);

        //cambio la imagen
        if (!acierto){
            ImageView imagenAhorcado = (ImageView) findViewById(R.id.imagenAhorcado);
            numeroDeFallos++;
            switch (numeroDeFallos){
                case 0 : imagenAhorcado.setImageResource(R.drawable.ahorcado_0); break;
                case 1 : imagenAhorcado.setImageResource(R.drawable.ahorcado_1); break;
                case 2 : imagenAhorcado.setImageResource(R.drawable.ahorcado_2); break;
                case 3 : imagenAhorcado.setImageResource(R.drawable.ahorcado_3); break;
                case 4 : imagenAhorcado.setImageResource(R.drawable.ahorcado_4); break;
                case 5 : imagenAhorcado.setImageResource(R.drawable.ahorcado_5); break;
                default : imagenAhorcado.setImageResource(R.drawable.ahorcado_fin); break;
            }
        }
    }

}