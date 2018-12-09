package com.pkdevelopers.eva3_6_handler_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    Handler hManejador = new Handler();//en esta practica no vamos a trabajar con mensajes, vamos a enviar runneables
    TextView txtvwDatos;
    int iNum = 1;

    //RUNNABLE 1 --> TRABAJO PESADO EN SEGUNDO PLANO
    Runnable rSegundoPlano = new Runnable() {
        @Override
        public void run() {
            while (true){
                hManejador.post(rRunnableUI);
                try {
                    Thread.sleep(1000);
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
            txtvwDatos.append((iNum++) + " - ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtvwDatos = findViewById(R.id.txtvwDatos);

        //iniciar runnable a traves de un thread
        Thread tHilo = new Thread(rSegundoPlano);
        tHilo.start();

    }
}
