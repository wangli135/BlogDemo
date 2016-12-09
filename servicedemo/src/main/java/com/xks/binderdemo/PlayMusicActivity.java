package com.xks.binderdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayMusicActivity extends AppCompatActivity {

    private PlayMusicService.PlayMusicBinder musicBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (PlayMusicService.PlayMusicBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, PlayMusicService.class), connection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void playMusic(View view) {
        if (musicBinder != null)
            musicBinder.play();
    }

    public void pauseMusic(View view) {
        if (musicBinder != null)
            musicBinder.pause();
    }

    public void stopMusic(View view) {
        if (musicBinder != null)
            musicBinder.stop();
    }
}
