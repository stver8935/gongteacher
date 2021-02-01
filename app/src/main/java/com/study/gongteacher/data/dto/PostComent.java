package com.study.gongteacher.data.dto;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class PostComent {


    @Expose
    @SerializedName("post_key")
    private int postKey;

    @Expose
    @SerializedName("coment")
    private String coment;

    @Expose
    @SerializedName("upload_date")
    private Date uploadDate;

    //댓글쓴 사람의 아이디 왜래키
    @Expose
    @SerializedName("upload_id")
    private String uploadId;


}
