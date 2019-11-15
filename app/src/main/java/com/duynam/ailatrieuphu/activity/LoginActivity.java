package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.dialog.Dialog_sansang;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgLight;
    private ImageView imgDangnhap;
    private ImageView imgChoithu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        Animation rote = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imgLight.startAnimation(rote);

        imgChoithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_sansang  dialog_sansang = new Dialog_sansang(LoginActivity.this);
                dialog_sansang.show();
            }
        });

        imgDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ManHinhChinhActivity.class));
            }
        });

    }

    private void initView() {
        imgLight = findViewById(R.id.img_light);
        imgDangnhap = findViewById(R.id.img_dangnhap);
        imgChoithu = findViewById(R.id.img_choithu);
    }
}
