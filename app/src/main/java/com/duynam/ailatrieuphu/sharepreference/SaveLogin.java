package com.duynam.ailatrieuphu.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.duynam.ailatrieuphu.interface_.Constant;

public class SaveLogin {

    public SaveLogin() {
    }

    public static boolean saveEmail(String email, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_EMAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_EMAIL, email);
        editor.apply();
        return true;
    }

    public static boolean savePhoto(String photo, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PHOTO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_PHOTO, photo);
        editor.apply();
        return true;
    }

    public static boolean saveName(String photo, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_NAME, photo);
        editor.apply();
        return true;
    }

    public static String getEmail(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_EMAIL, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_EMAIL, null);
    }

    public static String getPhoto(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PHOTO, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_PHOTO, null);
    }

    public static String getName(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_NAME, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_NAME, null);
    }

}
