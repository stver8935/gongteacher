package com.study.gongteacher.ui.splash;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.gongteacher.data.network.service.AppUpdateService;

public class SplashViewModelFactory implements ViewModelProvider.Factory{

    private final AppUpdateService appUpdateService;
    private final Context context;

        public SplashViewModelFactory(Context context,AppUpdateService appUpdateService) {
            this.context = context;
            this.appUpdateService = appUpdateService;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if(modelClass.isAssignableFrom(SplashViewModel.class)){
                    return (T) new SplashViewModel(context,appUpdateService);

            }

            throw new IllegalArgumentException("Unknown ViewModel class");

        }

}
