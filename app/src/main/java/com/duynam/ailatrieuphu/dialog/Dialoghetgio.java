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
import com.duynam.ailatrieuphu.activity.ChoithuActivity;
import com.duynam.ailatrieuphu.activity.KetquaActivity;

public class Dialoghetgio extends Dialog implements View.OnClickListener{

    int socauhoi;
    long sodiem;
    Context context;
    Button btn_dong;
    Media media;
    ChoithuActivity activity;

    public Dialoghetgio(@NonNull Context context, int socauhoi, long sodiem, ChoithuActivity activity) {
        super(context);
        this.context = context;
        this.socauhoi = socauhoi;
        this.sodiem = sodiem;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_hetgio);
        btn_dong = findViewById(R.id.btn_dong);
        btn_dong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dong:
                dismiss();
                Intent intent = new Intent(getContext(), activity.aClass);
                intent.putExtra("socauhoi", socauhoi);
                intent.putExtra("diem", sodiem);
                intent.putExtra("diemdoithu", activity.diemdoithu);
                getContext().startActivity(intent);
                ((Activity) context).finish();
                break;
        }
    }
}
