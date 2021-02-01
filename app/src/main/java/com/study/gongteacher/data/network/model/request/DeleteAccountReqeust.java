package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteAccountReqeust {
    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("accress_token")
    private String accressToken;

    public DeleteAccountReqeust(String userType, String accressToken) {
        this.userType = userType;
        this.accressToken = accressToken;
    }
}
