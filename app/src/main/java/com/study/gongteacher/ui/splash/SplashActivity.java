package com.study.gongteacher.ui.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;
import com.study.gongteacher.data.DataManager;
import com.study.gongteacher.data.network.model.request.AppVersionRequest;
import com.study.gongteacher.data.network.model.response.AppVersionResponse;
import com.study.gongteacher.databinding.ActivitySplashBinding;
import com.study.gongteacher.utils.Permission;
import com.study.gongteacher.ui.base.BaseActivity;
import com.study.gongteacher.ui.custom.confirmdialog.ConfirmDialogListener;
import com.study.gongteacher.ui.login.LoginActivity;
import com.study.gongteacher.ui.main.MainActivity;
import com.study.gongteacher.utils.PackageInfo;

import java.util.List;


public class SplashActivity extends BaseActivity<SplashViewModel> implements SplashNavigator {


    private ActivitySplashBinding binding;
    private PackageInfo packageInfo;
    private AppVersionRequest appVersionReqeust;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        packageInfo = new PackageInfo(this);
        init();

        //업버전 업데이트 요청 데이터 생성
        appVersionReqeust = new AppVersionRequest(packageInfo.getVersionNeme(),packageInfo.getVersionCode());
    }


    @Override
    public void onResume(){
        super.onResume();

        Gson gson = new Gson();
        String ob = gson.toJson(appVersionReqeust);
        Log.e(" version  : ",ob);
        viewModel.doAppUpdate(appVersionReqeust);
    }




    protected void  init(){

        initObservers();
    }




    private void initObservers(){
        //로딩 데이터 옵저버 추가
        viewModel.getStartAppResponseData().observe(this,new AppUpdateObserver());
    }




    @NonNull
    @Override
    protected SplashViewModel createViewModel() {
        SplashViewModelFactory factory = new SplashViewModelFactory(this,DataManager.getInstance().getAppUpdateService());

        return ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }


    //메인 액티비티이동
    @Override
    public void openMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    //로그인 화면으로 갈 경우
    @Override
    public void openLoginActivity(){
        Permission requestPermission = new Permission(this);

        if (!requestPermission.checkPermission("ALL")){

            requestPermission.requestSplash(new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    Toast.makeText(SplashActivity.this, "권한 허용", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {
                    Toast.makeText(SplashActivity.this, "권한 거부", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }else{

            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }



    }

    //업데이트 페이지 이동
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    //업데이트 페이지 이동
    public void openUpdatePage(){

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getOpPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getOpPackageName())));
        }
    }


    /*옵저버*/
    private class AppUpdateObserver implements Observer<AppVersionResponse> {

        @Override
        public void onChanged(AppVersionResponse response) {
            Gson gson  = new Gson();
            String resposne = gson.toJson(response);
            Log.e("App_Version_Check" ,resposne);

            String Msg = response.getMessage();
            boolean Status = response.isStatus();

            if (Msg.equals("update")){

                if (Status){
                    binding.tvSplashLoading.setText("최신 버전 입니다.");
                    binding.pbSplashLoading.setProgress(100);
                    openLoginActivity();

                }else{
                    binding.tvSplashLoading.setText("새로운 버전이 있습니다");
                    //플레이 스토어 이동 확인 다이얼 로그
                }

            }else{
                Toast.makeText(SplashActivity.this, "예기치 못한 오류 "+Msg, Toast.LENGTH_SHORT).show();
                finish();
            }

        }
        }



    //업데이트 확인 여부 다이얼로그 콜백 리스너
   ConfirmDialogListener dialogListener =new ConfirmDialogListener() {
        @Override
        public void onPositiveBtnListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                openUpdatePage();
            }
        }

        @Override
        public void onNegativeBtnListener() {
            finish();
        }
    };


}
