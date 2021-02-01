package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.ChatRoom;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class ChatRoomListResponse extends BaseResponse {
    @Expose
    @SerializedName("chat_room_list")
    private List<ChatRoom> chatRoomList;

    public List<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }

}
