package com.duynam.ailatrieuphu.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.media.MediaPlayer;
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
import com.duynam.ailatrieuphu.dialog.Dialog_Dungcuocchoi;
import com.duynam.ailatrieuphu.dialog.Dialog_hetgio;
import com.duynam.ailatrieuphu.model.Cauhoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChoithuActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private ProgressBar progressBartime;
    private TextView tvTime;
    CountDownTimer countDownTimer;
    private ProgressBar time;
    private TextView tvMonney;
    private ImageView imageView3;
    private ImageView imgDungcuocchoi;
    private ImageView imgHelp50;
    private ImageView imgHoiykienkhangia;
    private ImageView imgNguoithan;
    private TextView tvCauhoi;
    private TextView tvSocauhoi;
    private Button btnDaA, btnDaB, btnDaC, btnDaD;
    private Media nhacnen;

    Random rd;
    private Cauhoi cauhoi;
    String daDung;
    private DataAdapter adapter;
    int vtA, vtB, vtC;
    int vitrihientai = 1;
    int vitricauhoi;
    private ArrayList<Cauhoi> cauhoiList;
    private ArrayList<String> cautraloi;
    private ArrayList<Button> buttons;
    private ArrayList<Integer> passedList;
    private Boolean selectTrue;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choithu);
        initView();
        nhacnen = new Media(ChoithuActivity.this);
        nhacnen.chaynhacnen(vitrihientai);

        handler = new Handler();
        rd = new Random();
        cauhoi = new Cauhoi();
        cautraloi = new ArrayList<>();
        passedList = new ArrayList<>();
        buttons = new ArrayList<>(Arrays.asList(btnDaA, btnDaB, btnDaC, btnDaD));

        loadDatabse();
        data_Load();
        chondapan();

        imgDungcuocchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Dungcuocchoi dungcuocchoi = new Dialog_Dungcuocchoi(ChoithuActivity.this);
                dungcuocchoi.show();
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

    public void data_Load() {
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

        startTimer("stop");
        startTimer("start");
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
        tvCauhoi = findViewById(R.id.tv_cauhoi);
        tvSocauhoi = findViewById(R.id.tv_socauhoi);
        btnDaA = findViewById(R.id.btn_daA);
        btnDaB = findViewById(R.id.btn_daB);
        btnDaC = findViewById(R.id.btn_daC);
        btnDaD = findViewById(R.id.btn_daD);
    }


    private void startTimer(String type) {

        if (type.equals("start")) {
            countDownTimer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;
                    progressBartime.setProgress((int) seconds);
                    tvTime.setText(seconds + "");
                }

                @Override
                public void onFinish() {
                    tvTime.setText("30");
                    progressBartime.setProgress(30);
                    nhacnen.stop();
                    Dialog_hetgio dialog_hetgio = new Dialog_hetgio(ChoithuActivity.this, vitrihientai-1);
                    nhacnen.hetgio();
                    dialog_hetgio.show();
                }
            }.start();
        } else {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }

    }

    private void chondapan() {

        btnDaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickabke(true);
                startTimer("stop");
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
                            intent.putExtra("socauhoi", vitrihientai-1);
                            startActivity(intent);
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
                startTimer("stop");
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
                            intent.putExtra("socauhoi", vitrihientai-1);
                            startActivity(intent);
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
                startTimer("stop");
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
                            intent.putExtra("socauhoi", vitrihientai-1);
                            startActivity(intent);
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
                startTimer("stop");
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
                            intent.putExtra("socauhoi", vitrihientai-1);
                            startActivity(intent);
                        }
                    };
                    handler.postDelayed(runnable, 6000);
                }
            }
        });

    }

    private void animation() {
        Animation animation = AnimationUtils.loadAnimation(ChoithuActivity.this, R.anim.blink);
        if (selectTrue) {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().toString().equals(daDung)) {
                    buttons.get(i).setBackgroundResource(R.drawable.select_right);
                    buttons.get(i).startAnimation(animation);
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
                    data_Load();
                }
            };
            handler.postDelayed(runnable, 3000);
            nhacnen.resumenhacnen();
        }
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
