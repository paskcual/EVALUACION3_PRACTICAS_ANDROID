package com.pkdevelopers.eva3_4_banner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Principal extends AppCompatActivity  {

    ImageView imgVwBanner;
    SeekBar barraVelocidad;//agregado por mi
    TextView txtValorSeekBar;//agregado por mi

    int iCont = 1;

    int valor = 500;
    int max = 100;



    //manejador de los hilos
    Handler hManejador = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //cada que reciba un mensaje, hay que cambiar la imagen, recorremos todas las imagenes y volvemos a empezar
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

        //enlazamos
        barraVelocidad = findViewById(R.id.barraVelocidad);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        txtValorSeekBar = findViewById(R.id.txtVal);

        //creamos un nuevo banner que extiende de la clase thread
        Banner bMiBanner = new Banner();
        bMiBanner.start();


        //cuando modificamos el valor por medio de la seekbar
        barraVelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progreso, boolean b) {

                valor = max - progreso+1;
                txtValorSeekBar.setText(String.valueOf(valor));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    class Banner extends Thread{
        @Override
        public void run() {
            super.run();

            while (true){
                try {

                    Thread.sleep(valor);
                    //Message
                    //Notificar el cambio de imagen
                    Message msg = hManejador.obtainMessage();
                    hManejador.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
