package com.duynam.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class Dialoghoiykienkhangia extends Dialog {

    ChoithuActivity activity;
    BarChart bieudohelp;
    Button btn_dong;

    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    Random random;
    int a =0, b = 0, c = 0, d = 0, vitridapan;
    MediaPlayer hoiykien;

    public Dialoghoiykienkhangia(ChoithuActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_hoiykienkhangia);

        hoiykien = MediaPlayer.create(getContext(), R.raw.khan_gia);
        hoiykien.start();
        bieudohelp = findViewById(R.id.bieudotrogiup);
        btn_dong = findViewById(R.id.btn_close);
        random = new Random();
        activity.pauseTimer();

        activity.imgHoiykienkhangia.setBackgroundResource(R.drawable.app__ic_help_audience_used);
        activity.imgHoiykienkhangia.setClickable(false);

        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<>();
        addLabel();
        addValue();
        Bardataset = new BarDataSet(BARENTRY, "");
        BARDATA = new BarData(BarEntryLabels, Bardataset);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bieudohelp.setData(BARDATA);
        bieudohelp.animateY(5000);


        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                activity.startTimer();
            }
        });


    }

    private void addValue() {
        for (int i = 0; i < activity.buttons.size(); i++) {
            if (activity.buttons.get(i).getText().toString().equals(activity.daDung)) {
               vitridapan = i;
               if (i == 0){
                   while (a<b||a<c||a<d||a+b+c+d!=100){
                       rddapan();
                   }
                   setData();
               }else if (i == 1){
                   while (b<a||b<c||b<d||a+b+c+d!=100){
                       rddapan();
                   }
                   setData();
               }else if (i == 2){
                   while (c<a||c<b||c<d||a+b+c+d!=100){
                       rddapan();
                   }
                   setData();
               }else if (i == 3){
                   while (d<a||d<b||d<c||a+b+c+d!=100){
                       rddapan();
                   }
                   setData();
               }
            }
        }
    }

    private void rddapan(){
        a = random.nextInt(100 - 0 + 1) + 1;
        b = random.nextInt(100 - 0 + 1) + 1;
        c = random.nextInt(100 - 0 + 1) + 1;
        d = random.nextInt(100 - 0 + 1) + 1;
    }

    private void setData(){
        BARENTRY.add(new BarEntry(a, 0));
        BARENTRY.add(new BarEntry(b, 1));
        BARENTRY.add(new BarEntry(c, 2));
        BARENTRY.add(new BarEntry(d, 3));
    }

    public void addLabel() {
        BarEntryLabels.add("A");
        BarEntryLabels.add("B");
        BarEntryLabels.add("C");
        BarEntryLabels.add("D");
    }

    private int vitridapandung(){
        int index = 0;
        for (int i = 0; i < activity.buttons.size(); i++) {
            if (activity.buttons.get(i).getText().toString().equals(activity.daDung)) {
                for (int j = 0; j < BarEntryLabels.size(); j++){
                    index = j;
                }

            }
        }
        return index;
    }

}
