package com.duynam.ailatrieuphu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrangcanhanActivity extends AppCompatActivity {

    private BarChart barChart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    List<User> userList;
    private TextView rank1, rank2, rank3, rankme, tv_username;
    ImageView img_avatar, img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangcanhan);


        BarEntryLabels = new ArrayList<>();
        BARENTRY = new ArrayList<>();
        userList = new ArrayList<>();
        barChart = findViewById(R.id.thongkediem);
        rank1 = findViewById(R.id.tv_rank1);
        rank2 = findViewById(R.id.tv_rank2);
        rank3 = findViewById(R.id.tv_rank3);
        rankme = findViewById(R.id.tv_rankme);
        img_avatar = findViewById(R.id.img_avatar);
        img_back = findViewById(R.id.img_back);
        tv_username = findViewById(R.id.tv_username);

        YAxis rightYAxis = barChart.getAxisRight();
        YAxis leftYAxis = barChart.getAxisLeft();
        rightYAxis.setEnabled(false);
        leftYAxis.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setDescription("");

        tv_username.setText(SaveLogin.getName(this));
        if (SaveLogin.getPhoto(TrangcanhanActivity.this) != null){
            Picasso.get().load(SaveLogin.getPhoto(TrangcanhanActivity.this)).into(img_avatar);
        }else {

        }

        addLabel();


        final ArrayList<User> rank_score = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query homnay = database.getReference("Users");
        String email = SaveLogin.getEmail(TrangcanhanActivity.this);
        String emai_convert = email.replace(".", ",");
        final Query score_me = database.getReference("Users").child(emai_convert);

        score_me.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rankme.setText(dataSnapshot.child("score").getValue() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        homnay.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rank_score.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    long diem = (long) snapshot.child("score").getValue();
                    rank_score.add(new User(diem));
                    Collections.sort(rank_score, new Comparator<User>() {
                        @Override
                        public int compare(User sv1, User sv2) {
                            if (sv1.getScore() < sv2.getScore()) {
                                return 1;
                            } else {
                                if (sv1.getScore() == sv2.getScore()) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    });
                }
                rank1.setText(rank_score.get(0).getScore() + "");
                rank2.setText(rank_score.get(1).getScore() + "");
                rank3.setText(rank_score.get(2).getScore() + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (rank1.getText().toString().equals("")) {
                    BARENTRY.add(new BarEntry(0, 0));
                    BARENTRY.add(new BarEntry(0, 1));
                    BARENTRY.add(new BarEntry(0, 2));
                    BARENTRY.add(new BarEntry(0, 3));
                }
                BARENTRY.add(new BarEntry(Integer.parseInt(rank1.getText().toString()), 0));
                BARENTRY.add(new BarEntry(Integer.parseInt(rank2.getText().toString()), 1));
                BARENTRY.add(new BarEntry(Integer.parseInt(rank3.getText().toString()), 2));
                BARENTRY.add(new BarEntry(Integer.parseInt(rankme.getText().toString()), 3));
                Bardataset = new BarDataSet(BARENTRY, "");
                BARDATA = new BarData(BarEntryLabels, Bardataset);
                Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                barChart.setData(BARDATA);
                barChart.animateY(3000);
            }
        };
        handler.postDelayed(runnable, 3000);


    }

    public void addLabel() {
        BarEntryLabels.add("Rank 1");
        BarEntryLabels.add("Rank 2");
        BarEntryLabels.add("Rank 3");
        BarEntryLabels.add("Me");
    }

}
