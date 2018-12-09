package com.pkdevelopers.eva3_1_threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Principal extends AppCompatActivity {

    //PARA QUE TENRER UNA CLASE THREAD Y UNA INTERFAZ RUNNABLE, SI ESTAMOS HERADANDO RUNNABLE, SI NO USAMOS THREAD

    //RUNNABLE
    Runnable rMiHiloRun = new Runnable() {
        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                Log.wtf("Runnable", i + "");

                //SIMULAR LA TAREA EN SEGUNDO PLANO
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //TENEMOS DOS CATEGORIAS, LOS RUNNABLE (INTERFAZ) Y THREAD (ES UNA CLASE)
        //RUNNABLE (no pueden correr solos, tienen que estar con un objeto thread)
        Thread tHilo = new Thread(rMiHiloRun);
        tHilo.run();//se ejecuta como una tarea independiente
        tHilo.start();//se ejecuta como hilo (tenemos que matar el hilo)

        //Declaramos otro hilo, si lo haceos asi no tiene funcionalidad, tenemos que imlementar el metodo run
        //Thread tHilo2 = new Thread();
        //tHilo2.start();

        //se hace asi
        miHilo tMiHilo = new miHilo();
        tMiHilo.start();


    }


    class miHilo extends Thread {
        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                Log.wtf("Thread", i + "");

                //SIMULAR LA TAREA EN SEGUNDO PLANO
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
