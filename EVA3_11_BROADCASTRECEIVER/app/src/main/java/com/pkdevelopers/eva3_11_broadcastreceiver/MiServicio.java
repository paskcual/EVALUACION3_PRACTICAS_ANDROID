package com.pkdevelopers.eva3_11_broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MiServicio extends Service {

    Thread tHilo;
    Intent inMensaje;

    public MiServicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inMensaje = new Intent("MI_SERVICIOTE");
        inMensaje.putExtra("MENSAJE", "HOLA MUNDO!");
        sendBroadcast(inMensaje);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Runnable rnHilo = new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep(1000);//hay que detener el hilo tambien
                        Log.wtf( "MiServicio","Hola Mundo!" );
                        inMensaje = new Intent("MI_SERVICIOTE");
                        inMensaje.putExtra("MENSAJE", "HOLA MUNDO!");
                        sendBroadcast(inMensaje);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;//break porque estoy capturando el interrupt exception
                    }

                }

            }
        };
        tHilo = new Thread(rnHilo);
        tHilo.start();//si queremos hacer algo en segundo plano tenemos que hacerlo a travez de un ciclo de ejecucion

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inMensaje = new Intent("MI_SERVICIOTE");
        inMensaje.putExtra("MENSAJE", "HOLA MUNDO!");
        sendBroadcast(inMensaje);
        tHilo.interrupt();

    }


}
