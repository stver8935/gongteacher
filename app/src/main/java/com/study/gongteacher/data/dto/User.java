package com.study.gongteacher.data.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("access_token")
    public String AccessToken;

    @Expose
    @SerializedName("user_type")
    public String userType;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("user_id")
    public String userId;

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("nick_name")
    public String nickName;

    @Expose
    @SerializedName("profile_img")
    public String profileImg;

    @Expose
    @SerializedName("introduce")
    public String introduce;

    @Expose
    @SerializedName("location")
    private Address location;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
