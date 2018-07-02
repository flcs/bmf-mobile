package br.com.bmf.bmfmobile.persistence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Guarda os dados das preferencias do usuario usando o recurso SharedPreferences
 */
public enum Preferences {

    VALOR("VALOR"),
    JSON_ATIVOS("JSON_ATIVOS");

    private String pref;

    Preferences(String pref) {
        this.pref = pref;
    }

    private static final String PREFERENCES = "preferencias";

    public void putString(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(pref, value);
        edit.apply();
    }

    public void putLong(Context context, Long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(pref, value);
        edit.apply();
    }

    public void putFloat(Context context, Float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(pref, value);
        edit.apply();
    }

    public void putBoolean(Context context, Boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(pref, value);
        edit.apply();
    }

    public String getString(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(pref, "");
    }

    public Long getLong(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(pref, 0);
    }

    public Float getFloat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(pref, 0);
    }

    public Boolean getBoolean(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(pref, false);
    }
}
