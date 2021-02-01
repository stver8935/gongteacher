package com.study.gongteacher.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {
    @Expose
    @SerializedName("tag")
    private String tag;

    @Expose
    @SerializedName("tag_key")
    private int tagId;


}
