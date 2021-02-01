package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppVersionRequest {
    //버전 업데이트 요청
    @Expose
    @SerializedName("app_version_name")
    private String appVersionName;

    @Expose
    @SerializedName("app_version_code")
    private int appVersionCode;

    public AppVersionRequest(String appVersionName, int appVersionCode) {
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
    }
}
