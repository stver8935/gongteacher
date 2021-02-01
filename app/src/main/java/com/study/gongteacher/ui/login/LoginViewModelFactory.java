package com.study.gongteacher.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.gongteacher.data.network.service.AccountService;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final AccountService accountService;
    private Context context;

    public LoginViewModelFactory(Context context,AccountService accountService) {
        this.context =context;
        this.accountService = accountService;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(context,accountService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
