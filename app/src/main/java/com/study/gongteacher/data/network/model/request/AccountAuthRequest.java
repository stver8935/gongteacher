package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountAuthRequest {
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("auth_code")
    private String authCode;

    public AccountAuthRequest(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
    }
}
