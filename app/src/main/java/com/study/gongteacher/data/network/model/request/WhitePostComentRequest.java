package com.study.gongteacher.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.PostComent;

public class WhitePostComentRequest {
    @Expose
    @SerializedName("post_coment")
    private PostComent postComent;

    public WhitePostComentRequest(PostComent postComent) {
        this.postComent = postComent;
    }
}
