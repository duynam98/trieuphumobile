package com.duynam.ailatrieuphu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.Bangxephangadapter;
import com.google.android.material.tabs.TabLayout;

public class BangxephangActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private Bangxephangadapter bangxephangadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangxephang);
        initView();

        bangxephangadapter = new Bangxephangadapter(getSupportFragmentManager());
        viewPager.setAdapter(bangxephangadapter);
        tabs.setupWithViewPager(viewPager);

    }

    private void initView(){
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
    }

}
