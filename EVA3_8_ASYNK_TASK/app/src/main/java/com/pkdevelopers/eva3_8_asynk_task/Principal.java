package com.pkdevelopers.eva3_8_asynk_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView txtvwDatos;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtvwDatos = findViewById(R.id.txtvwDatos);

        //objeto de clase asincrona
        MiClaseAsincrona maTareaAsin = new MiClaseAsincrona();
        maTareaAsin.execute(100);//en execute van los parametros de entrada, si no enviamos un valor va a tronar en el if

    }

    //clases asincronas no necesitan handler, en todos menos en doignbakground se puede interactuar con la interfaz
    class MiClaseAsincrona extends AsyncTask<Integer , Integer, Void>{

        String sCade;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtvwDatos.setText("Iniciando la tarea asincrona");
        }

        //solo se ejecuta cuando el ciclo se termina
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtvwDatos.append(i + " - ");
        }

        //unico metodo que no se comunica con la interfaz grafica
        @Override
        protected Void doInBackground(Integer... integers) {

          i = integers[0];

            //Aqui va nuestro ciclo
            while (true){
                //simulamos la tarea lenta
                try {
                    Thread.sleep(1000);
                    publishProgress(i++);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

}
