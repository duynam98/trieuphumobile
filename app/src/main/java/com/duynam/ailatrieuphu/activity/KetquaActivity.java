package com.duynam.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.duynam.ailatrieuphu.R;

public class KetquaActivity extends AppCompatActivity {

    TextView tv_socauhoi, tv_thuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        tv_socauhoi = findViewById(R.id.tv_socauhoi);
        tv_thuong = findViewById(R.id.tv_tienthuong);

        Intent intent = getIntent();
        int socauhoi = intent.getIntExtra("socauhoi", 0);

        tv_socauhoi.setText(socauhoi+"");

    }
}
