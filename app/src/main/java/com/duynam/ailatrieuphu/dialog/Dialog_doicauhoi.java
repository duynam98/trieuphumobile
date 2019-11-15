package com.duynam.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;

public class Dialog_doicauhoi extends Dialog {

    Activity activity;
    Button btn_co, btn_khong;

    public Dialog_doicauhoi(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_doicauhoi);
        btn_co = findViewById(R.id.btn_doicauhoi);
        btn_khong = findViewById(R.id.btn_cancel);

        btn_co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ChoithuActivity) activity).data_Load();
                dismiss();
                ((ChoithuActivity) activity).imgdoicauhoi.setBackgroundResource(R.drawable.app__ic_help_change_question_used);
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
