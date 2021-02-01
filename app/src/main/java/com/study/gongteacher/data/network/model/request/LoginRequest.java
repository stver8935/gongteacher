package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {


    @Expose
    @SerializedName("user_type")
    private String type;

    @Expose
    @SerializedName("user_id")
    private String id;

    @Expose
    @SerializedName("password")
    private String password;

    public LoginRequest(String type, String id, String password) {
        this.type = type;
        this.id = id;
        this.password = password;
    }

}
