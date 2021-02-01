package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatListReqeust {
    //유저 타입
    @Expose
    @SerializedName("user_type")
    private String userType;

    //유저의 서버 접근 토큰
    @Expose
    @SerializedName("access_token")
    private String accessToken;


    @Expose
    @SerializedName("chat_room_key")
    private int chatRoomKey;

    public ChatListReqeust(String userType, String accessToken, int chatRoomKey) {
        this.userType = userType;
        this.accessToken = accessToken;
        this.chatRoomKey = chatRoomKey;
    }
}
