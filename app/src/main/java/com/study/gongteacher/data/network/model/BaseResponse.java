package com.study.gongteacher.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    //작업의 종류
    @Expose
    @SerializedName("type")
    private String type;

    //작업의 상태
    @Expose
    @SerializedName("status")
    private boolean status;

    //작업의 반환 메시지
    @Expose
    @SerializedName("message")
    private String message;

    public String getType() { return type; }
    public String getMessage() {
        return message;
    }
    public boolean isStatus() {
        return status;
    }





}
