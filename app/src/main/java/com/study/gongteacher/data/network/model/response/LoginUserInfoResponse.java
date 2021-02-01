package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.network.model.BaseResponse;

public class LoginUserInfoResponse extends BaseResponse {
    @Expose
    @SerializedName("user_key")
    private int userKey;

    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("profile_image_path")
    private String profileImagePath;

    @Expose
    @SerializedName("introduce")
    private String introduce;

    @Expose
    @SerializedName("email")
    private String email;


    public int getUserKey() {
        return userKey;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getEmail() {
        return email;
    }
}
