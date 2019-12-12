package com.duynam.ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Random;

public class Media {

    Context context;
    MediaPlayer nhacnen, nhacchondapan, nhacchonsai, nhacchondung, hetgio, background_music;
    Random random = new Random();

    public Media(Context context) {
        this.context = context;
    }

    public void chaynhacnen(int vitri) {
        if (vitri == 1) {
            nhacnen = MediaPlayer.create(context, R.raw.background_music);
        } else if (vitri == 6) {
            nhacnen.reset();
            nhacnen = MediaPlayer.create(context, R.raw.background_music_b);
        } else if (vitri == 11) {
            nhacnen.reset();
            nhacnen = MediaPlayer.create(context, R.raw.nhacnenkho);
        }
        nhacnen.start();
        nhacnen.setLooping(true);
    }

    public void nhacchondapan(char pos) {
        switch (pos) {
            case 'A':
                int[] dapanA = {R.raw.ans_a, R.raw.ans_a2};
                nhacchondapan = MediaPlayer.create(context, dapanA[random.nextInt(dapanA.length)]);
                break;
            case 'B':
                int[] dapanB = {R.raw.ans_b, R.raw.ans_b2};
                nhacchondapan = MediaPlayer.create(context, dapanB[random.nextInt(dapanB.length)]);
                break;
            case 'C':
                int[] dapanC = {R.raw.ans_c, R.raw.ans_c2};
                nhacchondapan = MediaPlayer.create(context, dapanC[random.nextInt(dapanC.length)]);
                break;
            case 'D':
                int[] dapanD = {R.raw.ans_d, R.raw.ans_d2};
                nhacchondapan = MediaPlayer.create(context, dapanD[random.nextInt(dapanD.length)]);
                break;
        }
        nhacchondapan.start();
    }

    public void chonsai(int pos) {
        switch (pos) {
            case 0:
                int[] dapanA = {R.raw.lose_a, R.raw.lose_a2};
                nhacchonsai = MediaPlayer.create(context, dapanA[random.nextInt(dapanA.length)]);
                break;
            case 1:
                int[] dapanB = {R.raw.lose_b, R.raw.lose_b2};
                nhacchonsai = MediaPlayer.create(context, dapanB[random.nextInt(dapanB.length)]);
                break;
            case 2:
                int[] dapanC = {R.raw.lose_c, R.raw.lose_c2};
                nhacchonsai = MediaPlayer.create(context, dapanC[random.nextInt(dapanC.length)]);
                break;
            case 3:
                int[] dapanD = {R.raw.lose_d, R.raw.lose_d2};
                nhacchonsai = MediaPlayer.create(context, dapanD[random.nextInt(dapanD.length)]);
                break;
        }
        nhacchonsai.start();
    }

    public void chondung(int pos) {
        switch (pos) {
            case 0:
                int[] dapanA = {R.raw.true_a, R.raw.true_a2, R.raw.true_a3};
                nhacchondung = MediaPlayer.create(context, dapanA[random.nextInt(dapanA.length)]);
                break;
            case 1:
                int[] dapanB = {R.raw.true_b, R.raw.true_b2, R.raw.true_b3};
                nhacchondung = MediaPlayer.create(context, dapanB[random.nextInt(dapanB.length)]);
                break;
            case 2:
                int[] dapanC = {R.raw.true_c, R.raw.true_c2, R.raw.true_c3};
                nhacchondung = MediaPlayer.create(context, dapanC[random.nextInt(dapanC.length)]);
                break;
            case 3:
                int[] dapanD = {R.raw.true_d2, R.raw.true_d3};
                nhacchondung = MediaPlayer.create(context, dapanD[random.nextInt(dapanD.length)]);
                break;
        }
        nhacchondung.start();
    }

    public void hetgio() {
        hetgio = MediaPlayer.create(context, R.raw.timeout);
        hetgio.start();
    }

    public void reset() {
        if (nhacnen != null) {
            nhacnen.release();
        }
    }

    public void startnhacnen() {
        if (background_music != null){
            background_music.release();
        }
        background_music  = MediaPlayer.create(context, R.raw.background_music);
        background_music.start();
        background_music.setLooping(true);
    }

    public void pause() {
        nhacnen.pause();
    }

    public void resumenhacnen() {
        nhacnen.start();
    }

}
