package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatRoomListRequest {
    //유저 타입
    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    public ChatRoomListRequest(String userType, String accessToken) {
        this.userType = userType;
        this.accessToken = accessToken;
    }
}
