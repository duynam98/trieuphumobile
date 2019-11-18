package com.duynam.ailatrieuphu.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.duynam.ailatrieuphu.fragment.FragmentDiemcao;
import com.duynam.ailatrieuphu.fragment.FragmentHomnay;
import com.duynam.ailatrieuphu.fragment.FragmentThang;

public class Bangxephangadapter extends FragmentPagerAdapter {

    public Bangxephangadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentHomnay();
        } else if (position == 1) {
            return new FragmentThang();
        } else if (position == 2) {
            return new FragmentDiemcao();
        }
        return new FragmentHomnay();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Hôm nay";
            case 1:
                return "Tháng";
            case 2:
                return "Điểm cao";
            default:
                return null;
        }
    }
}
