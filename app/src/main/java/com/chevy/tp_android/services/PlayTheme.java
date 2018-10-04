package com.chevy.tp_android.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.chevy.tp_android.R;

public class PlayTheme extends Service {

    MediaPlayer theme;
    public PlayTheme() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        theme = MediaPlayer.create(this, R.raw.theme);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                theme.start();
                Log.d("test", "run: ");
            }
        });
        t.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        theme.stop();
    }
}
