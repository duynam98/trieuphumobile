package com.duynam.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;

public class Dialog5050 extends Dialog {

    ChoithuActivity activity;
    Button btn_co, btn_khong;
    MediaPlayer mediaPlayer;
    Handler handler;

    public Dialog5050(ChoithuActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_5050);

        handler = new Handler();
        btn_co = findViewById(R.id.btn_co);
        btn_khong = findViewById(R.id.btn_cancel);

        btn_co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.imgHelp50.setImageResource(R.drawable.app__ic_help_5050_used);
                activity.imgHelp50.setClickable(false);
                dismiss();
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound5050);
                mediaPlayer.start();
                activity.pauseTimer();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        activity.startTimer();
                        activity.help50();
                    }
                };
                handler.postDelayed(runnable, 3000);
            }
        });

        btn_khong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
