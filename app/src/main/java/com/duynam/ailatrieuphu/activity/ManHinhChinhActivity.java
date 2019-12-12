package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.fragment.FragmenManHinhChinh;
import com.duynam.ailatrieuphu.fragment.FragmenSetting;
import com.duynam.ailatrieuphu.fragment.Fragment_BXH;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManHinhChinhActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout mainView, drawerView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private FrameLayout container;
    public DrawerLayout drawerLayout;
    public ImageView img_avatar;
    public TextView tv_Username;
    private GoogleApiClient mGoogleApiClient;
    private MediaPlayer nhacnen;
    String name, photo, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        addUsers();
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mainView = findViewById(R.id.mainView);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        View hView = navigationView.getHeaderView(0);

        LinearLayout header = hView.findViewById(R.id.header_menu);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManHinhChinhActivity.this, TrangcanhanActivity.class));
            }
        });

        img_avatar = hView.findViewById(R.id.img_avatar);
        tv_Username = hView.findViewById(R.id.tv_nameUser);

        tv_Username.setText(SaveLogin.getName(this));
        if (SaveLogin.getPhoto(ManHinhChinhActivity.this) == null) {

        } else {
            Picasso.get().load(SaveLogin.getPhoto(ManHinhChinhActivity.this)).into(img_avatar);
        }

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, new FragmenManHinhChinh());
        transaction.commit();

    }


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        nhacnen = MediaPlayer.create(ManHinhChinhActivity.this, R.raw.background_music);
        nhacnen.setLooping(true);
        nhacnen.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nhacnen.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        nhacnen.pause();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.container, new FragmenManHinhChinh());
                transaction.commit();
                break;
            case R.id.nav_dangxuat:
                SaveLogin.saveEmail(null, ManHinhChinhActivity.this);
                SaveLogin.saveName(null, ManHinhChinhActivity.this);
                SaveLogin.savePhoto(null, ManHinhChinhActivity.this);
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        startActivity(new Intent(ManHinhChinhActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                break;
            case R.id.nav_bangxephang:
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.drawer_layout, new Fragment_BXH());
                transaction.commit();
                break;
            case R.id.nav_caidat:
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.drawer_layout, new FragmenSetting());
                transaction.commit();
                break;
            case R.id.nav_chondoithu:
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.drawer_layout, new Fragment_BXH());
                transaction.commit();
                break;
            case R.id.nav_exit:
                System.exit(0);
                break;
        }
        DrawerLayout mDrawerLayout;
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();
        return false;
    }

    private void addUsers() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        final String stamp = new SimpleDateFormat("dd,MM,yyyy").format(timestamp);
        final String month = new SimpleDateFormat("MM,yyyy").format(timestamp);
        email = SaveLogin.getEmail(ManHinhChinhActivity.this);
        photo = SaveLogin.getPhoto(ManHinhChinhActivity.this);
        name = SaveLogin.getName(ManHinhChinhActivity.this);
        final String email_convert = email.replace(".", ",");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users = database.getReference("Users").child(email_convert);
        final User user = new User();
        user.email = email;
        user.username = name;
        user.photo = photo;
        user.level = "Cấp 1";
        user.timestamp = stamp;
        user.month = month;
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    OneSignal.sendTag("User_ID", email);
                    users.setValue(user, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        }
                    });
                } else {
                    User user = dataSnapshot.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void callFragmentThachdau() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.drawer_layout, new Fragment_BXH());
        transaction.commit();
    }

}
