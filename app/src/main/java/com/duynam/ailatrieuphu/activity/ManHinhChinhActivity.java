package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.Bangxephangadapter;
import com.duynam.ailatrieuphu.fragment.FragmenManHinhChinh;
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
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManHinhChinhActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout mainView, drawerView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private FrameLayout container;
    public ImageView img_avatar;
    private GoogleApiClient mGoogleApiClient;
    private String email, username, photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
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

        addUsers();

        View hView = navigationView.getHeaderView(0);
        img_avatar = hView.findViewById(R.id.img_avatar);
        if (SaveLogin.getPhoto(ManHinhChinhActivity.this) == null) {

        } else {
            Picasso.get().load(SaveLogin.getPhoto(ManHinhChinhActivity.this)).into(img_avatar);
        }

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, new FragmenManHinhChinh());
        transaction.commit();

    }

    private void addUsers() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        final String stamp = new SimpleDateFormat("dd,MM,yyyy").format(timestamp);

        email = SaveLogin.getEmail(ManHinhChinhActivity.this);
        final String email_convert = email.replace(".", ",");
        username = SaveLogin.getName(ManHinhChinhActivity.this);
        photo = SaveLogin.getPhoto(ManHinhChinhActivity.this);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("Users").child(email_convert);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    User user = new User();
                    user.email = email;
                    user.username = username;
                    user.photo = photo;
                    user.level = "Cấp 1";
                    user.timestamp = stamp;
                    DatabaseReference signup =database.getReference("Users").child(email_convert);
                    signup.setValue(user, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                        }
                    });
                } else {
                    User user =dataSnapshot.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
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
                startActivity(new Intent(ManHinhChinhActivity.this, BangxephangActivity.class));
                break;
        }
        return false;
    }
}
