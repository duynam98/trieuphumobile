package com.duynam.ailatrieuphu.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.duynam.ailatrieuphu.R;

public class NhacnenService extends Service {

    MediaPlayer nhacnen;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        nhacnen = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        nhacnen.setLooping(true);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        nhacnen.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nhacnen.stop();
        nhacnen.release();
    }
}
