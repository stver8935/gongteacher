package com.study.gongteacher.ui.custom.confirmdialog;

import androidx.lifecycle.MutableLiveData;

import com.study.gongteacher.ui.base.BaseViewModel;

public class ConfirmDialogViewModel extends BaseViewModel {


    private MutableLiveData<String> PositiveBtnText;
    private MutableLiveData<String> NegativeBtnText;
    private MutableLiveData<String> Title;
    private MutableLiveData<String> Message;


    ConfirmDialogViewModel(){
        PositiveBtnText = new MutableLiveData<>();
        NegativeBtnText = new MutableLiveData<>();
        Title = new MutableLiveData<>();
        Message = new MutableLiveData<>();
    }


}
