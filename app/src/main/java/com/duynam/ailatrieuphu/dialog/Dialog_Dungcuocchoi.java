package com.duynam.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;
import com.duynam.ailatrieuphu.activity.KetquaActivity;

public class Dialog_Dungcuocchoi extends Dialog {

    Activity activity;
    Button btn_yes, btn_no;

    public Dialog_Dungcuocchoi(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_dungcuocchoi);

        btn_yes = findViewById(R.id.btn_dungcuocchoi);
        btn_no = findViewById(R.id.btn_cancel);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int socauhoi = ((ChoithuActivity) activity).vitrihientai - 1;
                Intent intent = new Intent(getContext(), KetquaActivity.class);
                intent.putExtra("socauhoi", socauhoi);
                getContext().startActivity(intent);
                activity.finish();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
