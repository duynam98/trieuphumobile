package com.duynam.ailatrieuphu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.interface_.Level;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class KetquaActivity extends AppCompatActivity {

    TextView tv_socauhoi, tv_thuong, tv_name;
    String email;
    CircleImageView img_avatar;
    MediaPlayer mediaPlayer;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        tv_socauhoi = findViewById(R.id.tv_socauhoi);
        tv_thuong = findViewById(R.id.tv_tienthuong);
        img_avatar = findViewById(R.id.img_avatar);
        tv_name = findViewById(R.id.tv_name);
        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        if (SaveLogin.getEmail(this) != null){
            if (SaveLogin.getPhoto(this) != null){
                Picasso.get().load(SaveLogin.getPhoto(this)).into(img_avatar);
            }else {

            }
            tv_name.setText(SaveLogin.getName(this));
        }else {

        }

        Intent intent = getIntent();
        int socauhoi = intent.getIntExtra("socauhoi", 0);
        tv_socauhoi.setText(socauhoi+"");

        if (socauhoi == 15){
            mediaPlayer = MediaPlayer.create(this, R.raw.best_player);
            mediaPlayer.start();
        }else {
            mediaPlayer = MediaPlayer.create(this, R.raw.thanks);
            mediaPlayer.start();
        }

        long diem = intent.getLongExtra("diem", 0);
        if (diem >= 1000000){
            String tien = String.format("%.2f triệu", diem/ 1000000.0);
            tv_thuong.setText(tien);
        }else {
            tv_thuong.setText(diem+"");
        }

        update();

    }

    private void update(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        final String stamp = new SimpleDateFormat("dd,MM,yyyy").format(timestamp);
        if (SaveLogin.getEmail(KetquaActivity.this) != null){
            Intent intent = getIntent();
            final long diem = intent.getLongExtra("diem", 0);
            email = SaveLogin.getEmail(KetquaActivity.this);
            String email_convert = email.replace(".", ",");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference users = database.getReference("Users").child(email_convert);
            users.child("score").setValue(diem);
            users.child("level").setValue(Level.level(diem));
            users.child("timestamp").setValue(stamp);

        } else {

        }
    }

}
