package com.duynam.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class BangxephangActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangxephang);
        initView();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BangxephangActivity.this, ManHinhChinhActivity.class));
                finish();
            }
        });

    }

    private void initView(){
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        img_back = findViewById(R.id.img_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
