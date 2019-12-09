package com.example.eva3_6_banner_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    SeekBar sbar;
    int barra = 1500;
    int n = 0;
    Handler handler = new Handler();


    Thread hilo  = new Thread(){
        @Override
        public void run() {
            super.run();
            //todoo lo que sea segundo plano va aqui
            //nada con lo grafico
            while (true){
                try {
                    Thread.sleep(barra);
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

            switch (n){
                case(0):
                    img.setImageResource(R.drawable.f1);
                    n++;
                    break;
                case(1):
                    img.setImageResource(R.drawable.f2);
                    n++;
                    break;
                case(2):
                    img.setImageResource(R.drawable.f3);
                    n=0;
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.ivBanner);
        hilo.start();
        final int max = 1500;
        sbar = findViewById(R.id.seekBar);
        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                barra = max - progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),""+barra,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
