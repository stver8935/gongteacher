package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDuplicateIdRequest {
    @Expose
    @SerializedName("user_id")
    private String userId;

    public CheckDuplicateIdRequest(String userId) {
        this.userId = userId;
    }
}
