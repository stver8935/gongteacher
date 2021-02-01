package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Post;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class PostListResponse extends BaseResponse {
    @Expose
    @SerializedName("post")
    private List<Post> post;

    public List<Post> getPost() {
        return post;
    }
}
