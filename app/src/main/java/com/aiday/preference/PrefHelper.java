package com.aiday.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class PrefHelper {
    private static final int mode = Context.MODE_PRIVATE;
    private static final String PREF_NAME = "aiday_pref";

    private static Context sAppContext;

    private final SharedPreferences mPreference;

    private static PrefHelper sInstance;

    public static void setContext(Context context) {
        sAppContext = context;
    }

    static Context getContext() {
        return sAppContext;
    }

    public static PrefHelper getInstance() {
        if (sInstance == null) {
            synchronized (PrefHelper.class) {
                if (sInstance == null) {
                    sInstance = new PrefHelper(sAppContext);
                }
            }
        }
        return sInstance;
    }

    private PrefHelper(@NonNull Context context) {
        mPreference = context.getSharedPreferences(PREF_NAME, mode);
    }

    public void saveState(@NonNull String keyString, int value) {
        SharedPreferences.Editor editor = prepareEditor(keyString);
        editor.putInt(keyString, value);
        editor.apply();
    }

    public void saveState(@NonNull String keyString, long value) {
        SharedPreferences.Editor editor = prepareEditor(keyString);
        editor.putLong(keyString, value);
        editor.apply();
    }

    public void saveState(@NonNull String keyString, String value) {
        SharedPreferences.Editor editor = prepareEditor(keyString);
        editor.putString(keyString, value);
        editor.apply();
    }

    public void saveState(@NonNull String keyString, boolean value) {
        SharedPreferences.Editor editor = prepareEditor(keyString);
        editor.putBoolean(keyString, value);
        editor.apply();
    }

    @NonNull
    private SharedPreferences.Editor prepareEditor(@NonNull String keyString) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.remove(keyString);
        return editor;
    }

    public String loadString(@NonNull String keyString, String def) {
        return mPreference.getString(keyString, def);
    }

    public int loadInt(@NonNull String keyString, int def) {
        return mPreference.getInt(keyString, def);
    }

    public long loadLong(@NonNull String keyString, long def) {
        return mPreference.getLong(keyString, def);
    }

    public boolean loadBoolean(@NonNull String keyString, boolean def) {
        return mPreference.getBoolean(keyString, def);
    }
}
