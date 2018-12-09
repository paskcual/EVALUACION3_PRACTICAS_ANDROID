package com.pkdevelopers.eva3_11_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    Intent inServicio;
    BroadcastReceiver miBroadCast;
    TextView txtMensa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inServicio = new Intent(this, MiServicio.class);
        miBroadCast = new MiBroadCast();
        IntentFilter ifFiltrarServicios = new IntentFilter("MI_SERVICIOTE");
        registerReceiver(miBroadCast, ifFiltrarServicios);

        txtMensa = findViewById(R.id.txtMensa);

    }

    public void clickIni(View v){
        startService(inServicio);

    }

    public void clickFin(View v){
        stopService(inServicio);

    }

    class MiBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //aqui vamos a leer el mensaje
            String datos = intent.getStringExtra("MENSAJE");
            txtMensa.append(datos);
        }
    }


}
