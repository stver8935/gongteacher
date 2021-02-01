package com.study.gongteacher.ui.searchaddress;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.gongteacher.ui.login.LoginViewModel;

public class SearchAddressViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new SearchAddressViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}

