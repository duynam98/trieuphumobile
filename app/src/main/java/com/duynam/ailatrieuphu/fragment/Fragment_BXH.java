package com.duynam.ailatrieuphu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Fragment_BXH extends Fragment {

    private TabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ImageView img_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bangxephang, container, false);
        initView(view);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(Fragment_BXH.this).commit();
            }
        });

        return view;
    }

    private void initView(View view){
        tabs = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewpager);
        img_back = view.findViewById(R.id.img_back);
    }

    private void setupTabIcons() {
        int[] tabIcons = {R.drawable.today_bxh, R.drawable.month_bxh, R.drawable.score_bxh};
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
        tabs.getTabAt(2).setIcon(tabIcons[2]);
    }


}
