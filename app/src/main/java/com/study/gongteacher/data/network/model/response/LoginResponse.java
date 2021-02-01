package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.BaseResponse;

public class LoginResponse extends BaseResponse {
    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("user_key")
    private int userKey;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("login_fail_count")
    private int loginFailCount;



    public String getUserType() {
        return userType;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserKey() {
        return userKey;
    }

    public int getLoginFailCount() {
        return loginFailCount;
    }
}
