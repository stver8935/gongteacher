package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.BaseResponse;

public class UpdateProfileResponse extends BaseResponse {
    @Expose
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }
}
