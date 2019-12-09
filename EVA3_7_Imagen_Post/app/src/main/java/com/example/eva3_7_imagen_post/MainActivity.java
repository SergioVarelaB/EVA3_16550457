package com.example.eva3_7_imagen_post;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    ImageView iv;
    Bitmap imagen = null;
    Thread hilo  = new Thread(){
        @Override
        public void run() {
            super.run();
            //todoo lo que sea segundo plano va aqui
            //nada con lo grafico
            imagen = cargarImagen("https://televisa.brightspotcdn.com/dims4/default/bc14dd1/2147483647/strip/true/crop/1246x930+50+0/resize/824x615!/quality/90/?url=https%3A%2F%2Ftelevisa.brightspotcdn.com%2F79%2F7b%2F823e838d4a9baf2d42a1c858e1bd%2Fperrito.png");
            handler.post(rModificar);
        }
    };
    Runnable rModificar = new Runnable() {
        @Override
        public void run() {
            //aqui si se puede modificar la interfaz grafica
            iv.setImageBitmap(imagen);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.imageView);
        hilo.start();
    }

    private Bitmap cargarImagen(String url) {
        Bitmap imagen = null;
        try {
            InputStream input = (InputStream) new URL(url).getContent();
            imagen = BitmapFactory.decodeStream(input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return imagen;
    }
}
