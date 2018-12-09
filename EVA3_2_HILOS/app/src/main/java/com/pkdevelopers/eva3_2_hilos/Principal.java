package com.pkdevelopers.eva3_2_hilos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView txtVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtVwDatos = findViewById(R.id.txtvwDatos);

        Runnable rHilo = new Runnable() {
            @Override
            public void run() {
                //hacemos un ciclo infinito
                while(true){

                    try {

                        txtVwDatos.setText("Hola");
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        //Arrancamos el runnable, solo el hilo original que creo una vista puede modificar las vistas
        Thread tHilo  = new Thread(rHilo);
        tHilo.start();

        //Ejemplo de hilos
        MiHilo mhPerpetuo = new MiHilo();
        //mhPerpetuo.run();//esto nos traba la app
        mhPerpetuo.start();//esto genera el trabajo en segundo plano

        //Usamos handlers para el trabajo de sincronizacion

    }
}
