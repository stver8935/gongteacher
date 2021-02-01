package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AccountAuthCodeRequest {
    @Expose
    @SerializedName("email")
    private String email;

    public AccountAuthCodeRequest(String email) {
        this.email = email;
    }
}
