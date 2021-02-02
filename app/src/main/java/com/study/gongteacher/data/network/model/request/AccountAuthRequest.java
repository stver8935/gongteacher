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

    @Expose
    @SerializedName("auth_code_key")
    private int authCodeKey;

    public AccountAuthRequest(String email,int authCodeKey, String authCode) {
        this.email = email;
        this.authCodeKey = authCodeKey;
        this.authCode = authCode;
    }
}
