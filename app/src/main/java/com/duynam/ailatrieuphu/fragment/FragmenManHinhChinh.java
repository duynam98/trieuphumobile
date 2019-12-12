package com.duynam.ailatrieuphu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ManHinhChinhActivity;
import com.duynam.ailatrieuphu.dialog.Dialogsansang;
import com.duynam.ailatrieuphu.interface_.Level;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmenManHinhChinh extends Fragment {

    ImageView img_light, img_choidon, img_thachdau, img_avatar;
    TextView tv_username, tv_diem;
    String name, photo, email;

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
                Dialogsansang dialogsansang = new Dialogsansang(getContext());
                dialogsansang.show();
            }
        });

        img_thachdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ManHinhChinhActivity) getActivity()).callFragmentThachdau();
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checklevel();
            }
        };
        handler.postDelayed(runnable, 4000);

        return view;
    }



    private void checklevel(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String email = SaveLogin.getEmail(getContext());
        String email_convert = email.replace(".", ",");
        Query homnay = database.getReference("Users").child(email_convert);
        homnay.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    long diem = user.getScore();
                    tv_diem.setText(Level.covert_score(diem));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView(View view){
        img_light = view.findViewById(R.id.img_light);
        img_choidon = view.findViewById(R.id.img_choidon);
        img_thachdau = view.findViewById(R.id.img_thachdau);
        img_avatar = view.findViewById(R.id.img_avatar);
        tv_username = view.findViewById(R.id.tv_linear_username);
        tv_diem = view.findViewById(R.id.tv_diem);
    }

}
