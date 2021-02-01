package com.study.gongteacher.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    private  MutableLiveData<Boolean> isLoading;

    public BaseViewModel(){
        isLoading = new MutableLiveData<>();
    }

   protected void setIsLoading(boolean bool){
        isLoading.postValue(bool);
    }

   protected MutableLiveData<Boolean> getLoadingStatus(){
        return isLoading;
    }


}
