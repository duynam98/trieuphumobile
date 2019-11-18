package com.duynam.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;

public class Dialoggoidien extends Dialog {

    ChoithuActivity activity;
    LinearLayout bacsi, giaovien, kysu, phongvien, cautraloi, bg_help, bg_help1;
    Button btn_close;
    ImageView img_mainhelp;
    TextView tv_cautraloi;
    MediaPlayer help_call, chosse_call, nhacnen;

    public Dialoggoidien(ChoithuActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_goidien);
        bacsi = findViewById(R.id.bacsi_help);
        giaovien = findViewById(R.id.giaovien_help);
        kysu = findViewById(R.id.kysu_help);
        phongvien = findViewById(R.id.phongvien_help);
        bg_help = findViewById(R.id.bg_help);
        bg_help1 = findViewById(R.id.bg_help1);
        btn_close = findViewById(R.id.btn_close);
        cautraloi = findViewById(R.id.cautraloi);
        img_mainhelp = findViewById(R.id.img_mainhelp);
        help_call = MediaPlayer.create(getContext(), R.raw.help_call);
        tv_cautraloi = findViewById(R.id.tv_help_cautraloi);
        help_call.start();
        activity.pauseTimer();
        nhacnen = MediaPlayer.create(getContext(), R.raw.nhacnen_helpcall);
        nhacnen.start();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                activity.startTimer();
            }
        });

        bacsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.imgNguoithan.setClickable(false);
                activity.imgNguoithan.setBackgroundResource(R.drawable.app__ic_help_call_used);
                bg_help.setVisibility(View.INVISIBLE);
                bg_help1.setVisibility(View.INVISIBLE);
                cautraloi.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.VISIBLE);
                chosse_call = MediaPlayer.create(getContext(), R.raw.call);
                chosse_call.start();
                String dapDung = activity.daDung;
                tv_cautraloi.setText("Tôi nghĩ đáp án đúng là: " +dapDung);
                img_mainhelp.setBackgroundResource(R.drawable.app__ic_help_call_01_active);
            }
        });

        giaovien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.imgNguoithan.setClickable(false);
                activity.imgNguoithan.setBackgroundResource(R.drawable.app__ic_help_call_used);
                bg_help.setVisibility(View.INVISIBLE);
                bg_help1.setVisibility(View.INVISIBLE);
                cautraloi.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.VISIBLE);
                chosse_call = MediaPlayer.create(getContext(), R.raw.call);
                chosse_call.start();
                String dapDung = activity.daDung;
                tv_cautraloi.setText("Tôi nghĩ đáp án đúng là: " +dapDung);
                img_mainhelp.setBackgroundResource(R.drawable.app__ic_help_call_02_active);
            }
        });

        kysu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.imgNguoithan.setClickable(false);
                activity.imgNguoithan.setBackgroundResource(R.drawable.app__ic_help_call_used);
                bg_help.setVisibility(View.INVISIBLE);
                bg_help1.setVisibility(View.INVISIBLE);
                cautraloi.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.VISIBLE);
                chosse_call = MediaPlayer.create(getContext(), R.raw.call);
                chosse_call.start();
                String dapDung = activity.daDung;
                tv_cautraloi.setText("Tôi nghĩ đáp án đúng là: " +dapDung);
                img_mainhelp.setBackgroundResource(R.drawable.app__ic_help_call_03_active);
            }
        });

        phongvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.imgNguoithan.setClickable(false);
                activity.imgNguoithan.setBackgroundResource(R.drawable.app__ic_help_call_used);
                bg_help.setVisibility(View.INVISIBLE);
                bg_help1.setVisibility(View.INVISIBLE);
                cautraloi.setVisibility(View.VISIBLE);
                btn_close.setVisibility(View.VISIBLE);
                chosse_call = MediaPlayer.create(getContext(), R.raw.call);
                chosse_call.start();
                String dapDung = activity.daDung;
                tv_cautraloi.setText("Tôi nghĩ đáp án đúng là: " +dapDung);
                img_mainhelp.setBackgroundResource(R.drawable.app__ic_help_call_04_active);
            }
        });

    }
}
