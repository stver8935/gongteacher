package com.study.gongteacher.ui.searchaddress;

import androidx.lifecycle.MutableLiveData;

import com.study.gongteacher.data.dto.Address;
import com.study.gongteacher.ui.base.BaseViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchAddressViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<Address>> addressLiveData;

    public MutableLiveData<ArrayList<Address>> getAddressLiveData(){
        return addressLiveData;
    }


    public void doSearchAddress(String searchWord){
        addressLiveData.setValue(new ArrayList<Address>());
    }


}
