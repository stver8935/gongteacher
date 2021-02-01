package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadPostComentRequest {
    @Expose
    @SerializedName("post_key")
    private String postKey;

    public ReadPostComentRequest(String postKey) {
        this.postKey = postKey;
    }
}
