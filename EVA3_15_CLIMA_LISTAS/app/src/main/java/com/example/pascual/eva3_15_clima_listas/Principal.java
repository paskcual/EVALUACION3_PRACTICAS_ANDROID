package com.example.pascual.eva3_15_clima_listas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pascual.eva3_15_clima_listas.Clima.AdaptadorClima;
import com.example.pascual.eva3_15_clima_listas.Clima.Clima;
import com.example.pascual.eva3_15_clima_listas.Clima.DetallesDeClima;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity implements ListView.OnItemClickListener{

    //Declaramos variables
    ListView lstVwLista;
    Intent inDetalle;
    Clima[] clima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //vinculamos los componentes
        lstVwLista = findViewById(R.id.lstVwLista);

        //asignamos el evento a la lista
        lstVwLista.setOnItemClickListener(this);

        //lanzamos el intento
        inDetalle = new Intent(this,DetallesDeClima.class);

        //creamos una nueva conexion y la ejecutamos
        new Conexion().execute();


    }

    //metodo para cuando presione la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        inDetalle.putExtra("IMAGEN", clima[position].getImagen());
        inDetalle.putExtra("CIUDAD",clima[position].getNombreCiudad());
        inDetalle.putExtra("TEMP",clima[position].getTemperatura());
        inDetalle.putExtra("DESC", clima[position].getDescripcion());

        startActivity(inDetalle);

    }

    public int convertirToCentigrados(String sVal){

        double temp_init = Double.parseDouble(sVal);
        double centi = (temp_init - 32) / 1.8000;
        centi = Math.round(centi);
        int i = (int)centi;

        return i;

    }

    public void establecerAdaptador(){
        //asignamos el adaptador
        lstVwLista.setAdapter(new AdaptadorClima(this, R.layout.layout_clima, clima));

    }

    public int seleccionDeImagen(int idWeather){

        int variableRegreso = R.drawable.ic_launcher_background;

        if(idWeather == 800){
            variableRegreso = R.drawable.sunny;
        }else{

            if(idWeather >= 200 && idWeather < 300){
                variableRegreso = R.drawable.thunderstorm;

            }else if(idWeather >= 300 && idWeather < 500){
                variableRegreso = R.drawable.light_rain;

            }else if(idWeather >= 500 && idWeather < 600){
                variableRegreso = R.drawable.rainy;

            }else if(idWeather >= 600 && idWeather < 700){
                variableRegreso = R.drawable.snow;

            }else if(idWeather >= 700 && idWeather < 800){
                variableRegreso = R.drawable.atmospher;

            }else if(idWeather > 800){
                variableRegreso = R.drawable.cloudy;

            }

        }

        return variableRegreso;

    }

    class Conexion extends AsyncTask<Void, Void, String> {

        //link de donde obtenemos la informacion del clima
        final String sLink = "http://api.openweathermap.org/data/2.5/box/city?bbox=-108,25,-103,31,10&appid=5deaac0bd2f188f4b8ec04afb0af0c4d";

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

                    //arreglo en que guardaremos todos los datos
                    //asignmos el tamaño a nuestro arreglo de climas
                    clima = new Clima[jCiudades.length()-1];

                    for(int i = 0; i< jCiudades.length(); i++){

                        JSONObject jCiudad = jCiudades.getJSONObject(i);//seecciono la ciudad que se encuentra en i

                        JSONObject main = jCiudad.getJSONObject("main");//creo un objeto del atributo main, que esta dentro de la ciudad i

                        JSONArray arreglo = jCiudad.getJSONArray("weather");//creo un arreglo de tipo weather, que se encuentra dentro de ciudad i

                        JSONObject objeto = arreglo.getJSONObject(0);//creo un objeto entrando al weather, que me dara el id (para la imagen)



                        String temp = String.valueOf(main.getDouble("temp"));//accedemos al objeto main y obtenemos su atributo llamado temp

                        String descripcion = objeto.getString("description");//obtenemos la descripcion del weather de la ciudad seleccionada

                        String ciudad = jCiudad.getString("name");//obtenemos la ciudad que se encuentra actualmente en i

                        int idWeather = objeto.getInt("id");

                        int imagen = seleccionDeImagen(idWeather);

                        int tempCentigrados = convertirToCentigrados(temp);


                        //guardamos objetos de tipo Clima en el arreglo llamado clima
                        clima[i] = new Clima(ciudad,tempCentigrados,descripcion,imagen);

                        /*
                        txtVwPrueba.append("Ciudad: " + ciudad +" | ");
                        txtVwPrueba.append("Temperatura: " + temp +" | ");
                        txtVwPrueba.append("Descripcion: " + descripcion +" | ");
                        txtVwPrueba.append("idWeather: " + idWeather +" | ");

                        txtVwPrueba.append("#############");
                        */



                    }


                }catch (Exception e){
                    e.printStackTrace();

                }

            }
            establecerAdaptador();
        }
    }

}
