package com.pkdevelopers.eva3_11_broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView txtVwMostrar;
    BroadcastReceiver miBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtVwMostrar = findViewById(R.id.txtVwMostrar);


        miBroadCast = new MiBroadCast();
        IntentFilter ifFiltrarServicios = new IntentFilter("MI_SERVICIOTE");
        registerReceiver(miBroadCast, ifFiltrarServicios);

    }


    class MiBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //aqui vamos a leer el mensaje
            String datos = intent.getStringExtra("MENSAJE");
            txtVwMostrar.append(datos);
        }
    }

}
