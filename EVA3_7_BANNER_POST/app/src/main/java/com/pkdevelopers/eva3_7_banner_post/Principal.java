package com.pkdevelopers.eva3_7_banner_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    ImageView imgVwBanner;
    SeekBar seekBarVelocidad;

    //no vamos a utilizar mensajes como lo es en la practica 4 y 5, en esta usamos runnables
    Handler hManejador = new Handler();
    int iCont = 1;
    int iVelocidad = 1000;

    //RUNNABLE 1 --> TRABAJO PESADO EN SEGUNDO PLANO
    Runnable rSegundoPlano = new Runnable() {
        @Override
        public void run() {
            while (true){
                hManejador.post(rRunnableUI);
                try {
                    Thread.sleep(iVelocidad);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //RUNNABLE 2 --> TRABAJO LIJERO Y TRABAJO EN LA UI
    Runnable rRunnableUI = new Runnable() {
        @Override
        public void run() {

            switch (iCont){
                case 1:
                    imgVwBanner.setImageResource(R.drawable.cloudy);
                    iCont++;
                    break;

                case 2:
                    imgVwBanner.setImageResource(R.drawable.light_rain);
                    iCont++;
                    break;

                case 3:
                    imgVwBanner.setImageResource(R.drawable.rainy);
                    iCont++;
                    break;

                default:
                    imgVwBanner.setImageResource(R.drawable.sunny);
                    iCont = 1;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //enlazamos recursos
        imgVwBanner = findViewById(R.id.imgVwBanner);
        seekBarVelocidad = findViewById(R.id.seekBarVelocidad);

        //inicializamos la secuencia de imagenes con el thread, pasando como parametro
        Thread tHilo = new Thread(rSegundoPlano);
        tHilo.start();

        seekBarVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                iVelocidad = seleccionVelocidad(i);
                Log.wtf("iVelocidad: ", iVelocidad + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public int seleccionVelocidad(int posicionSeekBar){

        int iVelocidadMetodo;

        switch (posicionSeekBar){
            case 1:
                iVelocidadMetodo = 5000;
                break;

            case 2:
                iVelocidadMetodo = 4000;
                break;

            case 3:
                iVelocidadMetodo = 3000;
                break;

            case 4:
                iVelocidadMetodo = 2000;
                break;

            case 5:
                iVelocidadMetodo = 1000;
                break;

            case 6:
                iVelocidadMetodo = 500;
                break;

            case 7:
                iVelocidadMetodo = 250;
                break;

            case 8:
                iVelocidadMetodo = 125;
                break;

            case 9:
                iVelocidadMetodo = 62;
                break;

            case 10:
                iVelocidadMetodo = 31;
                break;

            default:
                iVelocidadMetodo = 1000;
                break;

        }
        return  iVelocidadMetodo;

    }

}
