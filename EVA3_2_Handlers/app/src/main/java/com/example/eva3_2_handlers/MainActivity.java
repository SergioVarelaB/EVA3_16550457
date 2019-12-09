package com.example.eva3_2_handlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Thread tHilo;
    //Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //aqui ya se puede interactuar con la interfaz grafica
            //estamos en el hilo principal prros
            String mensaje = (String) msg.obj;
            tv.append(mensaje);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tvHola);
        tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (true){
                    try {
                        Thread.sleep(500);
                        String cade = "i" + i;
                        Log.wtf("Hilo" , cade);
                        Message msg = handler.obtainMessage(1,cade);
                        //handler.dispatchMessage(msg);
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    i++;
                }
            }
        };
        tHilo.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tHilo.interrupt();
    }
}
