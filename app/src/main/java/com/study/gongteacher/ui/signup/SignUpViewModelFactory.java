package com.study.gongteacher.ui.signup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.study.gongteacher.data.network.service.AccountService;
import com.study.gongteacher.ui.login.LoginViewModel;

public class SignUpViewModelFactory implements ViewModelProvider.Factory {

    private final AccountService accountService;
    private Context context;

    public SignUpViewModelFactory(Context context,AccountService accountService) {
        this.context = context;
        this.accountService = accountService;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SignUpViewModel.class)){
            return (T) new SignUpViewModel(context,accountService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
