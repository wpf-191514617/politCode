package com.zodiac.polit.util;

import android.app.Activity;
import android.content.SharedPreferences;

import com.zodiac.polit.MyApplication;

/**
 * Created by john on 2018/9/8.
 *
 * SharedPreferences 工具类
 *
 */

public class PreferenceUtils {

    private static final String SHAREDPRE_FILE = "com.polit";

    private static PreferenceUtils mInstance;

    private SharedPreferences mSharedPreferences;

    private PreferenceUtils() {
        mSharedPreferences = MyApplication.getContext().getSharedPreferences(SHAREDPRE_FILE, Activity.MODE_PRIVATE);
    }

    public static PreferenceUtils getInstance() {
        if (mInstance == null) {
            synchronized (PreferenceUtils.class) {
                if (mInstance == null) {
                    mInstance = new PreferenceUtils();
                }
            }
        }
        return mInstance;
    }


    public void putStringValue(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putIntValue(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putFloatValue(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public void putLongValue(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    public String getStringValue(String key) {
        return getStringValue(key, null);
    }

    public String getStringValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public int getIntValue(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
    public float getFloatValue(String key) {
        return mSharedPreferences.getFloat(key,0.0f);
    }

    public boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }


    public boolean getBooleanValue(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public long getLongValue(String key) {
        return getLongValue(key, 0);
    }


    public long getLongValue(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }


}
