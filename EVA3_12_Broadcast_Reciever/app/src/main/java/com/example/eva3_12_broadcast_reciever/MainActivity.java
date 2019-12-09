package com.example.eva3_12_broadcast_reciever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Intent in;
    BroadcastReceiver brReceptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tvEti);
        in = new Intent(this,MyService.class);
        brReceptor = new miReceptorDifucion();
        IntentFilter filtro = new IntentFilter("mi_servicio");
        registerReceiver(brReceptor,filtro);

    }
    public void inicioServicio(View view){
        startService(in);
    }
    public void pararServicio(View view){
        stopService(in);
    }

    class miReceptorDifucion extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            tv.append(intent.getStringExtra("mensaje"));
        }
    }
}
