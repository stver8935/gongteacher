package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.PostComent;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class ReadPostComentResponse extends BaseResponse {
    @Expose
    @SerializedName("coment_list")
    private List<PostComent> postComentList;

    public List<PostComent> getPostComentList() {
        return postComentList;
    }

}
