package com.duynam.ailatrieuphu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.interface_.Level;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

public class KetquaActivity extends AppCompatActivity {

    TextView tv_socauhoi, tv_thuong;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        tv_socauhoi = findViewById(R.id.tv_socauhoi);
        tv_thuong = findViewById(R.id.tv_tienthuong);

        Intent intent = getIntent();
        int socauhoi = intent.getIntExtra("socauhoi", 0);
        tv_socauhoi.setText(socauhoi+"");
        int diem = intent.getIntExtra("diem", 0);
        if (diem >= 1000000){
            String tien = String.format("%.2f triệu", diem/ 1000000.0);
            tv_thuong.setText(tien);
        }else {
            tv_thuong.setText(diem+"");
        }

        update();

    }

    private void update(){
        Intent intent = getIntent();
        long diem = intent.getIntExtra("diem", 0);
        email = SaveLogin.getEmail(KetquaActivity.this);
        String email_convert = email.replace(".", ",");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users").child(email_convert);
        users.child("diem").setValue(diem);
        users.child("level").setValue(Level.level(diem));
    }

}
