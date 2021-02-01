package com.study.gongteacher.ui.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {


    protected VM viewModel;

    @NonNull
    protected abstract VM createViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }
}
