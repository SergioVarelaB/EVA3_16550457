package com.example.eva3_12_broadcast_reciever;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Trace;

public class MyService extends Service {
    Thread tHilo;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent datos = new Intent("mi_servicio");
        datos.putExtra("mensaje", "onCreate");
        sendBroadcast(datos);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent datos = new Intent("mi_servicio");
        datos.putExtra("mensaje", "onStart");
        sendBroadcast(datos);
        tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(500);
                        Intent mensaje = new Intent("mi_servicio");
                        mensaje.putExtra("mensaje", "-");
                        sendBroadcast(mensaje);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent datos = new Intent("mi_servicio");
        datos.putExtra("mensaje", "onDestroy");
        sendBroadcast(datos);
        tHilo.stop();
    }
}
