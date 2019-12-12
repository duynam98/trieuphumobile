package com.duynam.ailatrieuphu.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.interface_.Level;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import de.hdodenhof.circleimageview.CircleImageView;

public class KetquaThachdauActivity extends AppCompatActivity {

    private BarChart barChart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    String email;
    TextView tv_ketqua, tv_namdoithu, tv_name;
    CircleImageView img_me, img_doithu;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua_thachdau);
        tv_ketqua = findViewById(R.id.tv_ketqua);
        tv_namdoithu = findViewById(R.id.tv_name_doithu);
        tv_name = findViewById(R.id.tv_name);
        img_me = findViewById(R.id.img_avatar);
        img_doithu = findViewById(R.id.img_avatar2);
        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        if (SaveLogin.getNamedoithu(this) != null){
            tv_namdoithu.setText(SaveLogin.getNamedoithu(this));
            Picasso.get().load(SaveLogin.getPhotodoithu(this)).into(img_doithu);
        }
        tv_name.setText(SaveLogin.getName(this));
        Picasso.get().load(SaveLogin.getPhoto(this)).into(img_me);


        Intent intent = getIntent();
        long me = intent.getLongExtra("diem", 0);
        long doithu = intent.getLongExtra("diemdoithu", 0);

        BarEntryLabels = new ArrayList<>();
        BARENTRY = new ArrayList<>();
        barChart = findViewById(R.id.ketqua);
        YAxis rightYAxis = barChart.getAxisRight();
        YAxis leftYAxis = barChart.getAxisLeft();
        rightYAxis.setEnabled(false);
        leftYAxis.setEnabled(false);
        addLabel();

        BARENTRY.add(new BarEntry(me, 0));
        BARENTRY.add(new BarEntry(doithu, 1));

        Bardataset = new BarDataSet(BARENTRY, "");
        barChart.setDescription("");
        BARDATA = new BarData(BarEntryLabels, Bardataset);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(BARDATA);
        barChart.animateY(3000);

        if (me > doithu){
            tv_ketqua.setText("BẠN ĐÃ THẮNG");
        } else if (me == doithu){
            tv_ketqua.setText("HÒA");
        }
        else {
            tv_ketqua.setText("BẠN ĐÃ THUA");
        }

        update();
        sendNotification();

    }

    private void update(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        final String stamp = new SimpleDateFormat("dd,MM,yyyy").format(timestamp);
        if (SaveLogin.getEmail(KetquaThachdauActivity.this) != null){
            Intent intent = getIntent();
            final long diem = intent.getLongExtra("diem", 0);
            email = SaveLogin.getEmail(KetquaThachdauActivity.this);
            String email_convert = email.replace(".", ",");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference users = database.getReference("Users").child(email_convert);
            users.child("score").setValue(diem);
            users.child("level").setValue(Level.level(diem));
            users.child("timestamp").setValue(stamp);

        } else {

        }
    }

    public void addLabel() {
        BarEntryLabels.add("");
        BarEntryLabels.add("");
    }

    private void sendNotification() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String send_email = SaveLogin.getEmaildoithu(KetquaThachdauActivity.this);
                    String mess = SaveLogin.getName(KetquaThachdauActivity.this) + " đã thách đấu bạn";
                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic YjM5MzFhODYtMzZkZC00MDhkLTkxMWItNmEyMTYzZjBjNjMz");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"3654b6a3-c634-42ad-8b2a-e7655d7bb47e\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"" + mess +"\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

}
