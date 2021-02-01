package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Tutor;
import com.study.gongteacher.data.network.model.BaseResponse;

public class TutorProfileResponse extends BaseResponse {

    @Expose
    @SerializedName("tutor_profile")
    private Tutor tutor;

    public Tutor getTutor() {
        return tutor;
    }
}
