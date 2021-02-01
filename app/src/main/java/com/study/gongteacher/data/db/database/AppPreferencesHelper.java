package com.study.gongteacher.data.db.database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.study.gongteacher.data.dto.LoginUserInfo;
import com.study.gongteacher.data.dto.User;

import javax.inject.Inject;

public class AppPreferencesHelper{

    private final static String PREF_KEY_LOGIN_USER = "LOGIN_USER";
    private  SharedPreferences mPrefs;
    private  SharedPreferences.Editor editor;

    private Gson gson = new Gson();
    private static AppPreferencesHelper appPreferencesHelper;
    private Context context;


    public static AppPreferencesHelper getInstance(@NonNull Context context){
        if (appPreferencesHelper == null){
         appPreferencesHelper = new AppPreferencesHelper(context);
        }
        return appPreferencesHelper;
    }

    private AppPreferencesHelper(Context context) {
        this.context = context;
    }

    public void setLoginUser(LoginUserInfo loginUserInfo){
        mPrefs = context.getSharedPreferences(PREF_KEY_LOGIN_USER,Context.MODE_PRIVATE);
        editor = mPrefs.edit();

        String userinfo = gson.toJson(loginUserInfo);
        editor.putString("login_user_info",userinfo);
        editor.commit();
    }
    public LoginUserInfo getLoginUser(){

        mPrefs = context.getSharedPreferences(PREF_KEY_LOGIN_USER,Context.MODE_PRIVATE);
        editor = mPrefs.edit();

        String val = mPrefs.getString("login_user_info","");
        return gson.fromJson(val,LoginUserInfo.class);
    }


}
