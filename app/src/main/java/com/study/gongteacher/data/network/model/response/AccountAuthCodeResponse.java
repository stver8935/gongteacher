package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.network.model.BaseResponse;

public class AccountAuthCodeResponse extends BaseResponse {
    @Expose
    @SerializedName("auth_code_key")
    private int authCodeKey;

    @Expose
    @SerializedName("upload_date")
    private String uploadDate;

    public String getUploadDate() {
        return uploadDate;
    }

    public int getAuthCodeKey() {
        return authCodeKey;
    }
}
