package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.network.model.BaseResponse;

public class AppVersionResponse extends BaseResponse {

    @Expose
    @SerializedName("app_version_code")
    private int newVersionCode;

    @Expose
    @SerializedName("app_version_name")
    private String newVersionName;

    public int getNewVersionCode() {
        return newVersionCode;
    }

    public String getNewVersionName() {
        return newVersionName;
    }


}
