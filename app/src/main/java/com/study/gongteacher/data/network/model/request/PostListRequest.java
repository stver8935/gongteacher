package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostListRequest {
    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    public PostListRequest(String userType, String accessToken) {
        this.userType = userType;
        this.accessToken = accessToken;
    }
}
