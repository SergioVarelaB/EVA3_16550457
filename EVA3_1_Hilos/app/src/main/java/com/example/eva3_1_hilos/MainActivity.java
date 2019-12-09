package com.example.eva3_1_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    Thread  thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tvName);

        Thread  thread = new Thread(){
            @Override
            public void run() {
                super.run();
                //for
                for(int i = 0 ; i < 10 ; i++  ) {
                    try {
                        Thread.sleep(1000);
                        Log.wtf("hilo", "i = " +i);
                        //tvName.append("Hilo i= "+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                        Log.wtf("error", "valio quesadilla");
                        break;//termina la ejecucion
                    }
                }
            }
        };
        thread.start();//no poner run por que si no es como no poner un hilo

        Runnable runamble = new Runnable() {
            @Override
            public void run() {
                //for
                for(int i = 0 ; i < 10 ; i++  ) {
                    try {
                        Thread.sleep(1000);
                        Log.wtf("hilo", "i = " +i);
                        //tvName.append("Runnable i= "+i); esto no se puede
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilitoRun = new Thread(runamble);
        hilitoRun.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thread.interrupt();
    }
}
