package com.study.gongteacher.ui.main;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.study.gongteacher.R;
import com.study.gongteacher.databinding.ActivityMainBinding;
import com.study.gongteacher.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel> implements MainActivityNavigator {



   private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
    }



    void init(){
        //버튼 클릭 리스너 초기화
        setOnClickListener();

    }

    private void setOnClickListener(){

        //메뉴 클릭 이벤트
        binding.mainNavmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_study:
                        Toast.makeText(MainActivity.this, "study", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_chat:
                        Toast.makeText(MainActivity.this, "chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_myprofile:
                        Toast.makeText(MainActivity.this, "myProfile", Toast.LENGTH_SHORT).show();
                        break;
                }
            }


        });






    }





    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        return null;
    }


    @Override
    public void MainActivity(){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }




}
