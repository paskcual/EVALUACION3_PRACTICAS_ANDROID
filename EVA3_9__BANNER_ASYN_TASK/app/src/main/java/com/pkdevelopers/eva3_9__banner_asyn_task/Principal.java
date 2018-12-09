package com.pkdevelopers.eva3_9__banner_asyn_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Principal extends AppCompatActivity {

    ImageView imgVwBanner;

    int iCont = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        imgVwBanner = findViewById(R.id.imgVwBanner);

        MiClaseAsincrona maTareaAsin = new MiClaseAsincrona();
        maTareaAsin.execute(100);

    }

    class MiClaseAsincrona extends AsyncTask<Integer , Integer, Void> {

        String sCade;

        //lo que se realiza antes de ejecutarse
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        //este solo se ejecuta cuando el ciclo se termina
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        //se ejecuta en progreso
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

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

        //unico metodo que no se comunica con la interfaz grafica
        @Override
        protected Void doInBackground(Integer... integers) {

            //i = integers[0];

            //Aqui va nuestro ciclo
            while (true){
                //simulamos la tarea lenta
                try {
                    Thread.sleep(1000);
                    publishProgress();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }


}
