package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserInfoRequest {
    @Expose
    @SerializedName("user_key")
    private int user_key;

    public LoginUserInfoRequest(int user_key) {
        this.user_key = user_key;
    }
}
