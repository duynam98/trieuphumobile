package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.duynam.ailatrieuphu.Media;
import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.DataAdapter;
import com.duynam.ailatrieuphu.dialog.Dialog5050;
import com.duynam.ailatrieuphu.dialog.DialogDungcuocchoi;
import com.duynam.ailatrieuphu.dialog.Dialogdoicauhoi;
import com.duynam.ailatrieuphu.dialog.Dialoggoidien;
import com.duynam.ailatrieuphu.dialog.Dialoghetgio;
import com.duynam.ailatrieuphu.dialog.Dialoghoiykienkhangia;
import com.duynam.ailatrieuphu.model.Cauhoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChoithuActivity extends AppCompatActivity {

    public CircleImageView circleImageView;
    public ProgressBar progressBartime;
    public TextView tvTime;
    CountDownTimer countDownTimer;
    private long mTimeLeftInMillis = 10000;
    public ProgressBar time;
    public TextView tvMonney;
    public ImageView imageView3;
    public ImageView imgDungcuocchoi, imgdoicauhoi;
    public ImageView imgHelp50;
    public ImageView imgHoiykienkhangia;
    public ImageView imgNguoithan;
    public TextView tvCauhoi;
    public TextView tvSocauhoi;
    public Button btnDaA, btnDaB, btnDaC, btnDaD;
    public Media nhacnen;

    Random rd;
    private Cauhoi cauhoi;
    public String daDung;
    private DataAdapter adapter;
    int vtA, vtB, vtC;
    public int vitrihientai = 1;
    public int vitricauhoi, sodiem = 0;
    private ArrayList<Cauhoi> cauhoiList;
    private ArrayList<String> cautraloi;
    public ArrayList<Button> buttons;
    private ArrayList<Integer> passedList;
    private Boolean selectTrue;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choithu);
        initView();
        nhacnen = new Media(ChoithuActivity.this);

        handler = new Handler();
        rd = new Random();
        cauhoi = new Cauhoi();
        cautraloi = new ArrayList<>();
        passedList = new ArrayList<>();
        buttons = new ArrayList<>(Arrays.asList(btnDaA, btnDaB, btnDaC, btnDaD));

        loadDatabse();
        loadcauhoi();
        chondapan();
        nhacnen.chaynhacnen(vitrihientai);

        imgDungcuocchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDungcuocchoi dungcuocchoi = new DialogDungcuocchoi(ChoithuActivity.this);
                dungcuocchoi.show();
            }
        });

        imgdoicauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogdoicauhoi dialogdoicauhoi = new Dialogdoicauhoi(ChoithuActivity.this);
                dialogdoicauhoi.show();
            }
        });

        imgHelp50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog5050 dialog5050 = new Dialog5050(ChoithuActivity.this);
                dialog5050.show();
            }
        });

        imgHoiykienkhangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialoghoiykienkhangia dialoghoiykienkhangia = new Dialoghoiykienkhangia(ChoithuActivity.this);
                dialoghoiykienkhangia.show();
            }
        });

        imgNguoithan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialoggoidien dialoggoidien = new Dialoggoidien(ChoithuActivity.this);
                dialoggoidien.show();
            }
        });

    }


    public void loadDatabse() {
        try {
            adapter = new DataAdapter(ChoithuActivity.this);
            adapter.create();
            adapter.open();
            cauhoiList = adapter.getData();
            Log.e("size", "loadDatabse: " + cauhoiList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.close();
    }

    public void loadcauhoi() {
        if (vitrihientai >= 1)
            randomQues();
        while (isPassed(vitricauhoi)) {
            randomQues();
        }
        cauhoi = cauhoiList.get(vitricauhoi);
        cautraloi = new ArrayList<>();
        cautraloi.add(cauhoi.daA);
        cautraloi.add(cauhoi.daB);
        cautraloi.add(cauhoi.daC);
        cautraloi.add(cauhoi.daD);
        tvSocauhoi.setText("Câu hỏi số " + vitrihientai + ": ");
        tvCauhoi.setText(cauhoi.cauhoi);
        btnDaA.setText(randAnswer('A'));
        btnDaA.clearAnimation();
        btnDaB.setText(randAnswer('B'));
        btnDaB.clearAnimation();
        btnDaC.setText(randAnswer('C'));
        btnDaC.clearAnimation();
        btnDaD.setText(randAnswer('D'));
        btnDaD.clearAnimation();
        daDung = cauhoi.daDung;

        buttons = new ArrayList<>(Arrays.asList(btnDaA, btnDaB, btnDaC, btnDaD));
        passedList.add(vitricauhoi);

        mTimeLeftInMillis = 10000;
        pauseTimer();
        startTimer();
        clickabke(false);
    }

    public void randomQues() {
        if (vitrihientai <= 5)
            vitricauhoi = rd.nextInt(9 - 0 + 1) + 0;
        else if (vitrihientai <= 10)
            vitricauhoi = rd.nextInt(19 - 10 + 1) + 10;
        else if (vitrihientai <= 14)
            vitricauhoi = rd.nextInt(29 - 20 + 1) + 20;
        else
            vitricauhoi = rd.nextInt(33 - 30 + 1) + 30;
    }

    public boolean isPassed(int pos) {
        for (int i = 0; i < passedList.size(); i++) {
            if (pos == passedList.get(i))
                return true;
        }
        return false;
    }

    public String randAnswer(char pos) {
        int answerData = rd.nextInt(cautraloi.size());
        switch (pos) {
            case 'A':
                vtA = answerData;
                btnDaA.setBackgroundResource(R.drawable.a);
                break;
            case 'B':
                while (answerData == vtA)
                    answerData = rd.nextInt(cautraloi.size());
                vtB = answerData;
                btnDaB.setBackgroundResource(R.drawable.b);
                break;
            case 'C':
                while (answerData == vtA || answerData == vtB)
                    answerData = rd.nextInt(cautraloi.size());
                vtC = answerData;
                btnDaC.setBackgroundResource(R.drawable.c);
                break;
            case 'D':
                while (answerData == vtA || answerData == vtB || answerData == vtC)
                    answerData = rd.nextInt(cautraloi.size());
                btnDaD.setBackgroundResource(R.drawable.d);
                break;
        }
        return cautraloi.get(answerData);
    }

    public void help50() {
        int dap1 = 0, dap2 = 0;
        while (dap1 == dap2 || buttons.get(dap1).getText().toString().equals(daDung) || buttons.get(dap2).getText().toString().equals(daDung)) {
            dap1 = rd.nextInt(buttons.size());
            dap2 = rd.nextInt(buttons.size());
        }
        buttons.get(dap1).setText("");
        buttons.get(dap1).setClickable(false);
        buttons.get(dap2).setText("");
        buttons.get(dap2).setClickable(false);
    }


    private void initView() {
        circleImageView = findViewById(R.id.circleImageView);
        progressBartime = findViewById(R.id.time);
        tvTime = findViewById(R.id.tv_time);
        time = findViewById(R.id.time);
        tvMonney = findViewById(R.id.tv_monney);
        imageView3 = findViewById(R.id.imageView3);
        imgDungcuocchoi = findViewById(R.id.img_dungcuocchoi);
        imgHelp50 = findViewById(R.id.img_help50);
        imgHoiykienkhangia = findViewById(R.id.img_hoiykienkhangia);
        imgNguoithan = findViewById(R.id.img_nguoithan);
        imgdoicauhoi = findViewById(R.id.img_doicauhoi);
        tvCauhoi = findViewById(R.id.tv_cauhoi);
        tvSocauhoi = findViewById(R.id.tv_socauhoi);
        btnDaA = findViewById(R.id.btn_daA);
        btnDaB = findViewById(R.id.btn_daB);
        btnDaC = findViewById(R.id.btn_daC);
        btnDaD = findViewById(R.id.btn_daD);
    }


    public void startTimer() {

        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                mTimeLeftInMillis = leftTimeInMilliseconds;
                progressBartime.setProgress((int) seconds);
                tvTime.setText(seconds + "");
            }

            @Override
            public void onFinish() {
                if (vitrihientai == 16){
                    Intent intent = new Intent(ChoithuActivity.this, KetquaActivity.class);
                    intent.putExtra("socauhoi", vitrihientai);
                    intent.putExtra("diem", sodiem);
                    startActivity(intent);
                }
                tvTime.setText("30");
                progressBartime.setProgress(30);
                Dialoghetgio dialog_hetgio = new Dialoghetgio(ChoithuActivity.this, vitrihientai - 1, sodiem);
                nhacnen.hetgio();
                dialog_hetgio.show();
            }
        }.start();
    }

    public void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void chondapan() {

        btnDaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickabke(true);
                pauseTimer();
                btnDaA.setBackgroundResource(R.drawable.selected);
                nhacnen.nhacchondapan('A');
                nhacnen.pause();
                if (btnDaA.getText().toString().trim().equals(daDung)) {
                    selectTrue = true;
                    vitrihientai++;
                    CountDownTimer timer1 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaA.setBackgroundResource(R.drawable.select_right);
                            animation();
                            nextcauhoi();
                            nhacnen.chaynhacnen(vitrihientai);
                            congdiem(vitrihientai);
                        }
                    };
                    timer1.start();
                } else {
                    selectTrue = false;
                    CountDownTimer timer2 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaA.setBackgroundResource(R.drawable.select_wrong);
                            animation();
                        }
                    };
                    timer2.start();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ChoithuActivity.this, KetquaActivity.class);
                            intent.putExtra("socauhoi", vitrihientai - 1);
                            intent.putExtra("diem", sodiem);
                            startActivity(intent);
                            finish();
                        }
                    };
                    handler.postDelayed(runnable, 6000);
                }
            }
        });

        btnDaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickabke(true);
                pauseTimer();
                btnDaB.setBackgroundResource(R.drawable.selected);
                nhacnen.nhacchondapan('B');
                nhacnen.pause();
                if (btnDaB.getText().toString().trim().equals(daDung)) {
                    selectTrue = true;
                    vitrihientai++;
                    CountDownTimer timer1 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaB.setBackgroundResource(R.drawable.select_right);
                            animation();
                            nextcauhoi();
                            nhacnen.chaynhacnen(vitrihientai);
                            congdiem(vitrihientai);
                        }
                    };
                    timer1.start();
                } else {
                    selectTrue = false;
                    CountDownTimer timer2 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaB.setBackgroundResource(R.drawable.select_wrong);
                            animation();
                        }
                    };
                    timer2.start();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ChoithuActivity.this, KetquaActivity.class);
                            intent.putExtra("socauhoi", vitrihientai - 1);
                            intent.putExtra("diem", sodiem);
                            startActivity(intent);
                            finish();
                        }
                    };
                    handler.postDelayed(runnable, 6000);
                }
            }
        });

        btnDaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickabke(true);
                pauseTimer();
                btnDaC.setBackgroundResource(R.drawable.selected);
                nhacnen.nhacchondapan('C');
                nhacnen.pause();
                if (btnDaC.getText().toString().trim().equals(daDung)) {
                    selectTrue = true;
                    vitrihientai++;
                    CountDownTimer timer1 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaC.setBackgroundResource(R.drawable.select_right);
                            animation();
                            nextcauhoi();
                            nhacnen.chaynhacnen(vitrihientai);
                            congdiem(vitrihientai);
                        }
                    };
                    timer1.start();
                } else {
                    selectTrue = false;
                    CountDownTimer timer2 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaC.setBackgroundResource(R.drawable.select_wrong);
                            animation();
                        }
                    };
                    timer2.start();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ChoithuActivity.this, KetquaActivity.class);
                            intent.putExtra("socauhoi", vitrihientai - 1);
                            intent.putExtra("diem", sodiem);
                            startActivity(intent);
                            finish();
                        }
                    };
                    handler.postDelayed(runnable, 6000);
                }
            }
        });

        btnDaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickabke(true);
                pauseTimer();
                btnDaD.setBackgroundResource(R.drawable.selected);
                nhacnen.nhacchondapan('D');
                nhacnen.pause();
                if (btnDaD.getText().toString().trim().equals(daDung)) {
                    selectTrue = true;
                    vitrihientai++;
                    CountDownTimer timer1 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaD.setBackgroundResource(R.drawable.select_right);
                            animation();
                            nextcauhoi();
                            nhacnen.chaynhacnen(vitrihientai);
                            congdiem(vitrihientai);
                        }
                    };
                    timer1.start();
                } else {
                    selectTrue = false;
                    CountDownTimer timer2 = new CountDownTimer(4000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            btnDaD.setBackgroundResource(R.drawable.select_wrong);
                            animation();
                        }
                    };
                    timer2.start();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ChoithuActivity.this, KetquaActivity.class);
                            intent.putExtra("socauhoi", vitrihientai - 1);
                            intent.putExtra("diem", sodiem);
                            startActivity(intent);
                            finish();
                        }
                    };
                    handler.postDelayed(runnable, 6000);
                }
            }
        });

    }

    private void animation() {
        Animation animation = AnimationUtils.loadAnimation(ChoithuActivity.this, R.anim.blink);
        Animation blink_true = AnimationUtils.loadAnimation(ChoithuActivity.this, R.anim.blink_true);
        if (selectTrue) {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().toString().equals(daDung)) {
                    buttons.get(i).setBackgroundResource(R.drawable.select_right);
                    buttons.get(i).startAnimation(blink_true);
                    nhacnen.chondung(i);
                }
            }
        } else {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().toString().equals(daDung)) {
                    buttons.get(i).setBackgroundResource(R.drawable.select_right);
                    buttons.get(i).startAnimation(animation);
                    nhacnen.chonsai(i);
                }
            }
        }
    }

    private void nextcauhoi() {
        if (selectTrue) {
            handler = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    loadcauhoi();
                }
            };
            handler.postDelayed(runnable, 3000);
            nhacnen.resumenhacnen();
        }
    }

    private void congdiem(int vitri){
        if (vitri < 5){
            sodiem = sodiem + 200000;
        }else if (vitri == 6){
            sodiem = 2000000;
        }else if (sodiem < 10){
            sodiem = sodiem + 200000;
        }else if (vitri == 11){
            sodiem = 15000000;
        }else if (vitri < 15){
            sodiem = sodiem + 200000;
        }else if (vitri == 16){
            sodiem = 100000000;
        }
        tvMonney.setText(sodiem+"");
    }

    private void clickabke(boolean click) {
        if (click) {
            btnDaA.setClickable(false);
            btnDaB.setClickable(false);
            btnDaC.setClickable(false);
            btnDaD.setClickable(false);
        } else {
            btnDaA.setClickable(true);
            btnDaB.setClickable(true);
            btnDaC.setClickable(true);
            btnDaD.setClickable(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        nhacnen.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseTimer();
    }
}
