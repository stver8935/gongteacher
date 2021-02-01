package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Chat;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class ChatListResponse extends BaseResponse {
    @Expose
    @SerializedName("chat_list")
    private List<Chat> chatList;

    public List<Chat> getChatList() {
        return chatList;
    }
}
