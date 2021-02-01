package com.study.gongteacher.data.network.model.response;

import android.widget.BaseExpandableListAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.network.model.BaseResponse;

public class CheckDuplicateIdResponse extends BaseResponse {
    @Expose
    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
