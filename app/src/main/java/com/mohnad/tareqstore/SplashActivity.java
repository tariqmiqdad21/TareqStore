package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.imgLogo).setAlpha(0f);
        findViewById(R.id.tvAppName).setAlpha(0f);
        findViewById(R.id.tvTagline).setAlpha(0f);

        findViewById(R.id.imgLogo).animate().alpha(1f).setDuration(600).start();
        findViewById(R.id.tvAppName).animate().alpha(1f).setDuration(700).start();
        findViewById(R.id.tvTagline).animate().alpha(1f).setDuration(800).start();


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SessionManager session = new SessionManager(SplashActivity.this);
            Intent intent;

            if (session.isLoggedIn()) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }, 1400);
    }
}
