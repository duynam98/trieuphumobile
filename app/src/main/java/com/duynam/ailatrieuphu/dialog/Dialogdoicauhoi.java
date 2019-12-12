package com.duynam.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;

public class Dialogdoicauhoi extends Dialog {

    Activity activity;
    Button btn_co, btn_khong;

    public Dialogdoicauhoi(Activity activity) {
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
                ((ChoithuActivity) activity).loadcauhoi();
                dismiss();
                ((ChoithuActivity) activity).imgdoicauhoi.setImageResource(R.drawable.app__ic_help_change_question_used);
                ((ChoithuActivity) activity).imgdoicauhoi.setClickable(false);
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
