package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletePostComentRequest {
    @Expose
    @SerializedName("post_coment")
    private String postComentKey;

    public DeletePostComentRequest(String postComentKey) {
        this.postComentKey = postComentKey;
    }

}
