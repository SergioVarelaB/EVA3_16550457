package com.example.eva3_5_handler_post;

import androidx.appcompat.app.AppCompatActivity;

import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView idTv;

    Handler handler = new Handler();

    Thread hilo  = new Thread(){
        @Override
        public void run() {
            super.run();
            //todoo lo que sea segundo plano va aqui
            //nada con lo grafico
            while (true){
                try {
                    Thread.sleep(500);
                    handler.post(rModificar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };
    Runnable rModificar = new Runnable() {
        @Override
        public void run() {
            //aqui si se puede modificar la interfaz grafica
            idTv.append("hola mundo \n");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idTv = findViewById(R.id.idTextView);
        hilo.start();

    }
}
