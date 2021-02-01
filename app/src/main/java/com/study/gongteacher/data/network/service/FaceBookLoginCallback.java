package com.study.gongteacher.data.network.service;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

public class FaceBookLoginCallback implements FacebookCallback<LoginResult> {
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d("onSuccess","FaceBookCallback");
        request(loginResult.getAccessToken());

    }

    @Override
    public void onCancel() {
        Log.d("onCancel","FaceBookCallback");
    }

    @Override
    public void onError(FacebookException error) {
        Log.d(" onError "," FaceBookCallback : "+error.getMessage());

    }

    // 사용자 정보 요청
    public void request(AccessToken token) {

        GraphRequest graphRequest = GraphRequest.newMeRequest(token,

                new GraphRequest.GraphJSONObjectCallback() {

                    @Override

                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("result",object.toString());


                    }

                });



        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

    }
}
