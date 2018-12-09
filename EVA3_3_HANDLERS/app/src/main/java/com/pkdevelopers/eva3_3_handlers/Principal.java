package com.pkdevelopers.eva3_3_handlers;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    //sobreescribimos el metodo hander message
    Handler hManejador = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //aqui si pueden interactuar con la interfaz grafica
            //aqui no hay que poner nada de trabajo pesado
            if(msg.what == 1){

                int i = (int)msg.obj;
                txtVwDatos.append(i + " - ");

            }

        }
    };

    TextView txtVwDatos;
    Thread tHilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtVwDatos = findViewById(R.id.txtVwDatos);

        miHilo mhHilo = new miHilo();
        tHilo = new Thread(mhHilo);
        tHilo.start();

    }

    class miHilo implements Runnable{

        @Override
        public void run() {

            int i = 0;

            while(true){

                i++;

                try {

                    //aqui vamos a modificar el textview
                    Message msg = hManejador.obtainMessage(1, i);
                    hManejador.sendMessage(msg);
                    Log.wtf("Hilo", i +"");
                    //txtVwDatos.setText(i + "");
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tHilo.interrupt();
    }

}
