package com.study.gongteacher.data.dto;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Tutor {

    @Expose
    @SerializedName("tutor_id")
    private String tutorId;

    //과외비
    @Expose
    @SerializedName("education_expenses")
    private String educationExpenses;


    //소개
    @Expose
    @SerializedName("introduce")
    private String introduce;

    //해당 게시글 좋아요 갯수
    @Expose
    @SerializedName("favorite_count")
    private int favoriteCount;

    //최신 업데이트 날짜
    @Expose
    @SerializedName("upload_date")
    private Date uploadDate;

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getEducationExpenses() {
        return educationExpenses;
    }

    public void setEducationExpenses(String educationExpenses) {
        this.educationExpenses = educationExpenses;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}