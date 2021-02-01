package com.study.gongteacher.ui.main.fragment.home;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.study.gongteacher.R;
import com.study.gongteacher.ui.base.BaseFragment;
import com.study.gongteacher.ui.base.BaseViewModel;
import com.study.gongteacher.ui.main.MainActivity;

public class HomeFragment extends BaseFragment {

    private HomeFragmentViewModel homeFragmentViewModel;



    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @NonNull
    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        return v;
    }
}
