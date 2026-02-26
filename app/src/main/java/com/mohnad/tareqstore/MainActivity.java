package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        ImageView imgBanner = findViewById(R.id.imgBanner);
        MaterialButton btnBrowse = findViewById(R.id.btnBrowse);
        MaterialButton btnCart = findViewById(R.id.btnCart);
        MaterialButton btnLogout = findViewById(R.id.btnLogout); // ✅ جديد
        tvBadge = findViewById(R.id.tvBadge);

        // Session
        SessionManager session = new SessionManager(this); // ✅ جديد

        // Banner images
        int[] banners = {
                R.drawable.p1,
                R.drawable.p2,
                R.drawable.p3,
                R.drawable.p4,
                R.drawable.p5,
                R.drawable.p6,
                R.drawable.p7,
                R.drawable.p8,
                R.drawable.p9,
                R.drawable.p10
        };

        // Set random banner
        int index = new Random().nextInt(banners.length);
        imgBanner.setImageResource(banners[index]);

        findViewById(R.id.cardBanner).setAlpha(0f);
        findViewById(R.id.cardBanner).setTranslationY(40f);
        findViewById(R.id.cardBanner).animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(550)
                .start();

        // Buttons
        btnBrowse.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        btnCart.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // ✅ Logout Button
        btnLogout.setOnClickListener(v -> {
            session.logout();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update cart badge
        int count = CartRepo.count();
        tvBadge.setText(String.valueOf(count));
        tvBadge.animate()
                .alpha(count == 0 ? 0f : 1f)
                .setDuration(180)
                .start();
    }
}