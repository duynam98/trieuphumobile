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

    public static boolean saveCurrentVolume(int current_volume, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_VOLUME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constant.KEY_VOLUME, current_volume);
        editor.apply();
        return true;
    }


    public static boolean saveNhacnen(boolean isMedia, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_MEDIA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.KEY_MEDIA, isMedia);
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

    public static boolean saveNamedoithu(String name, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_NAME_DOITHU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_NAME_DOITHU, name);
        editor.apply();
        return true;
    }

    public static boolean savePhotodoithu(String photo, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PHOTO_DOITHU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_PHOTO_DOITHU, photo);
        editor.apply();
        return true;
    }

    public static boolean saveEmaildoithu(String email, Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_EMAIL_DOITHU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.KEY_EMAIL_DOITHU, email);
        editor.apply();
        return true;
    }

    public static String getEmaildoithu(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_EMAIL_DOITHU, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_EMAIL_DOITHU, null);
    }

    public static String getNamedoithu(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_NAME_DOITHU, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_NAME_DOITHU, null);
    }

    public static String getPhotodoithu(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PHOTO_DOITHU, Context.MODE_PRIVATE);
        return preferences.getString(Constant.KEY_PHOTO_DOITHU, null);
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

    public static boolean getMedia(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_MEDIA, Context.MODE_PRIVATE);
        return preferences.getBoolean(Constant.KEY_MEDIA, true);
    }

    public static int getVolume(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_VOLUME, Context.MODE_PRIVATE);
        return preferences.getInt(Constant.KEY_VOLUME, 0);
    }

}
