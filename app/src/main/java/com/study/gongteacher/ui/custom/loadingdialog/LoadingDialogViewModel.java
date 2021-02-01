package com.study.gongteacher.ui.custom.loadingdialog;

import androidx.lifecycle.MutableLiveData;

import com.study.gongteacher.ui.base.BaseViewModel;

public class LoadingDialogViewModel extends BaseViewModel {
    private MutableLiveData<String> Title;


    LoadingDialogViewModel(){
        Title = new MutableLiveData<>();
    }



}
