package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.User;

public class UpdateProfileRequest {
    @Expose
    @SerializedName("user_info")
    private User user;
    public UpdateProfileRequest(User user) {
        this.user = user;
    }
}
