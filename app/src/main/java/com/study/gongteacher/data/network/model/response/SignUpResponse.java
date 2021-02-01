package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.BaseResponse;

public class SignUpResponse extends BaseResponse {
    @Expose
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public boolean isStatus() {
        return super.isStatus();
    }
}
