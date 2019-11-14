package com.duynam.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class Dialog_sansang extends Dialog {

    Context context;
    private Button btn_sansang, btn_cancel;
    MediaPlayer mediaPlayer, mediaPlayer1;
    boolean start = true, isStart = false;

    public Dialog_sansang(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_start);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_sansang = findViewById(R.id.btn_sansang);

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.ready);
        mediaPlayer.start();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mediaPlayer.stop();
            }
        });

        btn_sansang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mediaPlayer.stop();
                mediaPlayer1 = MediaPlayer.create(getContext(), R.raw.start);
                mediaPlayer1.start();
                delay();
            }
        });

    }

    private void delay(){
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getContext().startActivity(new Intent(getContext(), ChoithuActivity.class));
                dismiss();
                ((Activity) context).finish();
            }
        };
        handler.postDelayed(runnable, 4000);
    }

}
