package com.pkdevelopers.eva3_5_cuenta_caracteres;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    //declaramos las variables utilizadas
    TextView txtvwConteo;
    EditText etxtIngreso;

    String sTexto;

    //manejador de los hilos
    Handler hManejador = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            sTexto = etxtIngreso.getText().toString();//obtenemos el texto del edittext
            int iTexto = sTexto.length();

            Log.wtf( "cuandto vale txt ",iTexto + "" );

            if(iTexto == 1){//en caso de que no se hayan escrito caracteres
                txtvwConteo.setText(iTexto + " Caracter");

            }else{
                txtvwConteo.setText(iTexto + " Caracteres");

            }



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //enlazamos
        txtvwConteo = findViewById(R.id.txtwvConteo);
        etxtIngreso = findViewById(R.id.etxtIngreso);

        CuentaCaracteres miCuenta = new CuentaCaracteres();
        miCuenta.start();

    }



    class CuentaCaracteres extends Thread{
        @Override
        public void run() {
            super.run();

            while (true){
                try {
                    Thread.sleep(100);//cuanto tiempo dormimos el hilo
                    Message msg = hManejador.obtainMessage();
                    hManejador.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
