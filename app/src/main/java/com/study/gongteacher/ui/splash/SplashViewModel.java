package com.study.gongteacher.ui.splash;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.study.gongteacher.data.network.model.request.AppVersionRequest;
import com.study.gongteacher.data.network.model.response.AppVersionResponse;
import com.study.gongteacher.data.network.service.AppUpdateService;
import com.study.gongteacher.ui.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashViewModel extends BaseViewModel {

    //앱 실행시 앱버전 및 상태 서버통신 서비스
    private AppUpdateService appUpdateService;

    private MutableLiveData<AppVersionResponse> appVersionLiveData;

    private Context context;


    SplashViewModel(Context context,AppUpdateService startAppService) {
        this.appVersionLiveData = new MutableLiveData<AppVersionResponse>();
        this.appUpdateService  = startAppService;
        this.context = context;
    }


    public MutableLiveData<AppVersionResponse> getStartAppResponseData(){
        return appVersionLiveData;
    }


    void doAppUpdate(AppVersionRequest appVersionUpdateReqeust){

        appUpdateService.getUpdateApi().getUpdateVersion(appVersionUpdateReqeust).enqueue(new Callback<AppVersionResponse>() {
            @Override
            public void onResponse(Call<AppVersionResponse> call, Response<AppVersionResponse> response) {


                Gson gson = new Gson();
                String ob =gson.toJson(response.body());

                Log.e(" body " ,  ob);


                if (response != null && response.isSuccessful()){

                    appVersionLiveData.setValue(response.body());

                }else{

                    Log.e("App Update Error ","code : "+response.code());

                }
            }

            @Override
            public void onFailure(Call<AppVersionResponse> call, Throwable t) {
                Log.e(" App Update Error ","code : "+t.getMessage());
            }
        });
    }










}
