package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(this);

        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView tvCreate = findViewById(R.id.tvCreateAccount);

        // إذا كان مسجل دخول من قبل
        if (session.isLoggedIn()) {
            goMain();
            return;
        }

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String pass = etPassword.getText() != null ? etPassword.getText().toString() : "";

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email required");
                etEmail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                etPassword.setError("Password required");
                etPassword.requestFocus();
                return;
            }

            if (!session.hasUser()) {
                etEmail.setError("No account found. Create one first.");
                return;
            }

            if (session.validateLogin(email, pass)) {
                session.setLoggedIn(true);
                goMain();
            } else {
                etPassword.setError("Wrong email or password");
                etPassword.requestFocus();
            }
        });

        tvCreate.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    private void goMain() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}