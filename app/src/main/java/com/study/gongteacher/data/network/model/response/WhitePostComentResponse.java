package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.PostComent;
import com.study.gongteacher.data.network.model.BaseResponse;

public class WhitePostComentResponse extends BaseResponse {
    @Expose
    @SerializedName("post_coment")
    private PostComent postComent;

    public PostComent getPostComent() {
        return postComent;
    }
}
