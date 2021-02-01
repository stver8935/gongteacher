package com.study.gongteacher.ui.main;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.study.gongteacher.ui.base.BaseViewModel;
import com.study.gongteacher.ui.main.MainActivityNavigator;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Fragment> fragments;
    private MainActivityNavigator mainActivityNavigator;


    MainViewModel(){
        isLoading = new MutableLiveData<>();
    }


    @Override
    protected MutableLiveData<Boolean> getLoadingStatus()
    {
        return isLoading;
    }

    @Override
    protected void setIsLoading(boolean loading){
        isLoading.postValue(loading);
    }


    void loadFragment(Fragment fragment){
        setIsLoading(true);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment){
        setIsLoading(false);
        this.fragments.postValue(fragment);
    }



}
