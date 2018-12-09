package com.example.pascual.eva3_14_clima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity {

    TextView txtVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtVwDatos = findViewById(R.id.txtVwDatos);

    }


    public void clickBoton(View view){
        new Conexion().execute();
    }


    class Conexion extends AsyncTask<Void, Void, String> {

        final String sLink = "https://samples.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=b6907d289e10d714a6e88b30761fae22";


        @Override
        protected String doInBackground(Void... voids) {

            String sResu = "";

            try {
                URL url = new URL(sLink);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    //es como leer un archivo de texto
                    BufferedReader brDatos = new BufferedReader(new BufferedReader(new InputStreamReader(httpCon.getInputStream())));
                    sResu = brDatos.readLine();
                }

            }catch (Exception e){
                e.printStackTrace();

            }

            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //lectura de los datos
            JSONArray jCiudades = null;
            if(!s.equals("")){
                try {
                    //creamos el objeto del texto recibido
                    JSONObject jsDatos = new JSONObject(s);
                    jCiudades = jsDatos.getJSONArray("list");

                    for(int i = 0; i< jCiudades.length(); i++){
                        JSONObject jCiudad = jCiudades.getJSONObject(i);
                        txtVwDatos.append("Ciudad: " + jCiudad.getString("name"));

                    }


                }catch (Exception e){
                    e.printStackTrace();

                }

            }
        }
    }


}
