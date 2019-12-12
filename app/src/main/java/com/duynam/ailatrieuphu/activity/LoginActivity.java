package com.duynam.ailatrieuphu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.dialog.Dialogsansang;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgLight;
    private ImageView imgDangnhap;
    private ImageView imgChoithu;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1000;
    private String email, photo, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        initView();

        Animation rote = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imgLight.startAnimation(rote);

        if (SaveLogin.getEmail(this) != null) {
            startActivity(new Intent(LoginActivity.this, ManHinhChinhActivity.class));
        }

        imgChoithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogsansang dialog_sansang = new Dialogsansang(LoginActivity.this);
                dialog_sansang.show();
            }
        });

        imgDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            if (SaveLogin.getEmail(LoginActivity.this) != null) {
                startActivity(new Intent(LoginActivity.this, ManHinhChinhActivity.class));
                finish();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            email = account.getEmail();
            if (account.getPhotoUrl() != null) {
                photo = account.getPhotoUrl().toString();
            } else {
                photo = null;
            }
            name = account.getDisplayName();
            SaveLogin.saveEmail(email, LoginActivity.this);
            SaveLogin.saveName(name, LoginActivity.this);
            SaveLogin.savePhoto(photo, LoginActivity.this);
        } catch (ApiException e) {
            Log.e("ERROR", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void initView() {
        imgLight = findViewById(R.id.img_light);
        imgDangnhap = findViewById(R.id.img_dangnhap);
        imgChoithu = findViewById(R.id.img_choithu);
    }

}
