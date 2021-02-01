package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Chat;
import com.study.gongteacher.data.dto.ChatRoom;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class AccountResponse extends BaseResponse {
    //회원가입
    public class SignUp {
        @Expose
        @SerializedName("user")
        private User user;

        public User getUser() {
            return user;
        }
    }

    //로그인
    public class Login{
        @Expose
        @SerializedName("user")
        private User user;

        public User getUser() {
            return user;
        }
    }

    public class Logout{

    }

    //프로필 업데이트
    public class ProfileUpdate{
        @Expose
        @SerializedName("user")
        private User user;

        public User getUser() {
            return user;
        }
    }

    //회원탈퇴
    public class DeleteAccount{

    }

    //채팅리스트
    public class ChatList{
        @Expose
        @SerializedName("chat_list")
        private List<Chat> chatList;

        public List<Chat> getChatList() {
            return chatList;
        }
    }

    //채팅방 리스트
    public class ChatRoomList{
        @Expose
        @SerializedName("chat_room_list")
        private List<ChatRoom> chatRoomList;

        public List<ChatRoom> getChatRoomList() {
            return chatRoomList;
        }
    }

    //채팅방 정보
    public class ChatRoomInfo{
        @Expose
        @SerializedName("chat_room_info")
        private ChatRoom chatRoom;

        public ChatRoom getChatRoom() {
            return chatRoom;
        }
    }

}
