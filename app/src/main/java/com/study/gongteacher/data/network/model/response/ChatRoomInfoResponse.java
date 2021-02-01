package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.ChatRoom;
import com.study.gongteacher.data.network.model.BaseResponse;

public class ChatRoomInfoResponse extends BaseResponse {
    @Expose
    @SerializedName("chat_room_info")
    private ChatRoom chatRoom;

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}
