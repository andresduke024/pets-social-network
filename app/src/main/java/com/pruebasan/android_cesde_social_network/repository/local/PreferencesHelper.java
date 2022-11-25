package com.pruebasan.android_cesde_social_network.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static <T> void setPreferences(Context context, String key, T value) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(ConstantsPreferences.namePreferences, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            }

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getPreferences(Context context, String key) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(ConstantsPreferences.namePreferences, Context.MODE_PRIVATE);
            if (preferences.getAll().containsKey(key)) {
                return preferences.getAll().get(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean deletePreference(Context context, String key) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(ConstantsPreferences.namePreferences, Context.MODE_PRIVATE);
            return preferences.edit().remove(key).commit();
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean deletePreferences(Context context) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(ConstantsPreferences.namePreferences, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            return editor.clear().commit();
        } catch (Exception e) {
            return false;
        }
    }
}
