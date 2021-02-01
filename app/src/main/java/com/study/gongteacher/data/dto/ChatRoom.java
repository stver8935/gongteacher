package com.study.gongteacher.data.dto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    @PrimaryKey
    @Expose
    @SerializedName("chat_room_id")
    private int chatRoomId;

    @Expose
    @SerializedName("room_type")
    private String type;

    @Expose
    @SerializedName("title")
    private String title;


    //채팅방 공지사항
    @Expose
    @SerializedName("notice_list")
    private ArrayList<Post> noticeArrayList;

    //채팅방 유저 리스트
    @Expose
    @SerializedName("user_list")
    private ArrayList<User> userArrayList;

    @Expose
    @SerializedName("chat_list")
    private ArrayList<Chat> chatArrayList;

    @Expose
    @SerializedName("image_list")
    private ArrayList<Image> imageArrayList;



    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<Post> getNoticeArrayList() {
        return noticeArrayList;
    }

    public void setNoticeArrayList(ArrayList<Post> noticeArrayList) {
        this.noticeArrayList = noticeArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<Chat> getChatArrayList() {
        return chatArrayList;
    }

    public void setChatArrayList(ArrayList<Chat> chatArrayList) {
        this.chatArrayList = chatArrayList;
    }

    public ArrayList<Image> getImageArrayList() {
        return imageArrayList;
    }

    public void setImageArrayList(ArrayList<Image> imageArrayList) {
        this.imageArrayList = imageArrayList;
    }
}