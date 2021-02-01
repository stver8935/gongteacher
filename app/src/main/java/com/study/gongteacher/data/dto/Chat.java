package com.study.gongteacher.data.dto;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Chat {

    @Expose
    @SerializedName("chat_id")
    private int chatId;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("message_type")
    private String messageType;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("upload_date")
    private Date uploadDate;

    //채팅룸의 왜래키
    @Expose
    @SerializedName("chat_room_key")
    private String chatRoomKey;


}