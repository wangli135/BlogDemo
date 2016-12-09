package com.xks.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * 播放音乐服务
 */
public class PlayMusicService extends Service {
    public PlayMusicService() {
    }

    public class PlayMusicBinder extends Binder {

        /**
         * 播放音乐
         */
        public void play() {

            playMusic();

        }

        /**
         * 暂停播放
         */
        public void pause() {
            pauseMusic();
        }

        /**
         * 暂停播放
         */
        public void stop() {

            stopMusic();

        }

    }

    private PlayMusicBinder mBinder = new PlayMusicBinder();

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.diamonds);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)
            mediaPlayer.release();
    }

    private void playMusic() {
        mediaPlayer.start();
    }

    private void pauseMusic() {
        mediaPlayer.pause();
    }

    private void stopMusic() {
        mediaPlayer.stop();
    }
}
