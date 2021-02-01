package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatRoomInfoRequest {
    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("accress_token")
    private String accressToken;

    @Expose
    @SerializedName("chat_room_key")
    private int chatRoomKey;


    public ChatRoomInfoRequest(String userType, String accressToken, int chatRoomKey) {
        this.userType = userType;
        this.accressToken = accressToken;
        this.chatRoomKey = chatRoomKey;
    }
}
