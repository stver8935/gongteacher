package com.study.gongteacher.data.dto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class Post {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("contnets")
    private String contents;

    @Expose
    @SerializedName("image")
    private ArrayList<Image> imageArrayList;

    @Expose
    @SerializedName("upload_id")
    private String uploadId;

    @Expose
    @SerializedName("upload_date")
    private Date uploadDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Image> getImageArrayList() {
        return imageArrayList;
    }

    public void setImageArrayList(ArrayList<Image> imageArrayList) {
        this.imageArrayList = imageArrayList;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
