package com.study.gongteacher.ui.custom.confirmdialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.gongteacher.ui.splash.SplashViewModel;

public class ConfirmDialogViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SplashViewModel.class)){
            return (T) new ConfirmDialogViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}
