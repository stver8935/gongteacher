package com.study.gongteacher.data.network.service;

import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class FaceBookLoginService {
    private CallbackManager callbackManager;
    private LoginManager loginManager;


    public FaceBookLoginService(){
        callbackManager = CallbackManager.Factory.create();

    }

    public void doLogin(FacebookCallback<LoginResult> callback){
        LoginManager.getInstance().registerCallback(callbackManager, callback);
    }




}
