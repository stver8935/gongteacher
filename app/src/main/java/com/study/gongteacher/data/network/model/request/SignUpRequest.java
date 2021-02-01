package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @Expose
    @SerializedName("user_type")
    private String type;

    @Expose
    @SerializedName("user_id")
    private String id;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("nick_name")
    private String nickName;

    @Expose
    @SerializedName("email")
    private String email;

    public SignUpRequest(String type, String id, String password, String nickName, String email) {
        this.type = type;
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
    }
}
