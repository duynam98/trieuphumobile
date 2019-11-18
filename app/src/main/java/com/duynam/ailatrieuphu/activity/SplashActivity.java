package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;

public class SplashActivity extends AppCompatActivity {

    CountDownTimer mCountDownTimer;
    int i = 0;
    private ImageView imageView, light;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        timeprogress();

        Animation rote = AnimationUtils.loadAnimation(this, R.anim.rotate);
        light.startAnimation(rote);

    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        light = findViewById(R.id.img_light);
    }

    private void timeprogress(){
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setProgress(i);
        mCountDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                progressBar.setProgress(i*100/(5000/1000));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(100);
                if (SaveLogin.getEmail(SplashActivity.this) != null){
                    startActivity(new Intent(SplashActivity.this, ManHinhChinhActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }.start();
    }
}
