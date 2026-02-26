package com.mohnad.tareqstore;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF = "tareq_store_prefs";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";

    private final SharedPreferences sp;

    public SessionManager(Context context) {
        sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return sp.getBoolean(KEY_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean value) {
        sp.edit().putBoolean(KEY_LOGGED_IN, value).apply();
    }

    public void saveUser(String name, String email, String password) {
        sp.edit()
                .putString(KEY_NAME, name)
                .putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password)
                .apply();
    }

    public boolean hasUser() {
        return sp.getString(KEY_EMAIL, null) != null && sp.getString(KEY_PASSWORD, null) != null;
    }

    public boolean validateLogin(String email, String password) {
        String savedEmail = sp.getString(KEY_EMAIL, null);
        String savedPass = sp.getString(KEY_PASSWORD, null);
        return savedEmail != null && savedPass != null
                && savedEmail.equals(email.trim())
                && savedPass.equals(password);
    }

    public void logout() {
        sp.edit().putBoolean(KEY_LOGGED_IN, false).apply();
    }
}