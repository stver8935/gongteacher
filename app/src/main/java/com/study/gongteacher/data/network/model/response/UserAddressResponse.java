package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Address;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class UserAddressResponse extends BaseResponse {
    @Expose
    @SerializedName("user_address")
    private List<Address> userAddressList;

    public List<Address> getUserAddressList() {
        return userAddressList;
    }
}
