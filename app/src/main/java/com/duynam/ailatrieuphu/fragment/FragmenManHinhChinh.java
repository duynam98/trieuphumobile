package com.duynam.ailatrieuphu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.squareup.picasso.Picasso;

public class FragmenManHinhChinh extends Fragment {

    ImageView img_light, img_choidon, img_thachdau, img_avatar;
    TextView tv_username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manhinhchinh, container, false);
        initView(view);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        img_light.startAnimation(animation);

        tv_username.setText(SaveLogin.getName(getContext()));
        if (SaveLogin.getPhoto(getContext()) == null){
            img_avatar.setBackgroundResource(R.drawable.user_choithu);
        } else {
            Picasso.get().load(SaveLogin.getPhoto(getContext())).into(img_avatar);
        }

        img_choidon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), ChoithuActivity.class));
            }
        });

        img_thachdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void checklevel(){

    }

    private void initView(View view){
        img_light = view.findViewById(R.id.img_light);
        img_choidon = view.findViewById(R.id.img_choidon);
        img_thachdau = view.findViewById(R.id.img_thachdau);
        img_avatar = view.findViewById(R.id.img_avatar);
        tv_username = view.findViewById(R.id.tv_linear_username);
    }

}
