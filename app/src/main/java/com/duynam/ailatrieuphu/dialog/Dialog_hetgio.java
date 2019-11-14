package com.duynam.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.duynam.ailatrieuphu.Media;
import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.KetquaActivity;

public class Dialog_hetgio extends Dialog implements View.OnClickListener{

    int socauhoi;
    Context context;
    Button btn_dong;
    MediaPlayer thanks;
    Media media;

    public Dialog_hetgio(@NonNull Context context, int socauhoi) {
        super(context);
        this.context = context;
        this.socauhoi = socauhoi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_hetgio);
        btn_dong = findViewById(R.id.btn_dong);
        btn_dong.setOnClickListener(this);
        media = new Media(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dong:
                thanks = MediaPlayer.create(context, R.raw.thanks);
                thanks.start();
                dismiss();
                Intent intent = new Intent(getContext(), KetquaActivity.class);
                intent.putExtra("socauhoi", socauhoi);
                getContext().startActivity(intent);
                ((Activity) context).finish();
                break;
        }
    }
}
