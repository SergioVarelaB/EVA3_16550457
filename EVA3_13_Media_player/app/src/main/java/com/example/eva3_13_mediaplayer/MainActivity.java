package com.example.eva3_13_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = MediaPlayer.create(this,R.raw.merluzzo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer != null){
            mPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*if (mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }*/
    }
}
