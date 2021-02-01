package com.study.gongteacher.data.network.service;

import com.study.gongteacher.data.network.EndPoint;
import com.study.gongteacher.data.network.model.request.AppVersionRequest;
import com.study.gongteacher.data.network.model.response.AppVersionResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class AppUpdateService {
    private static AppUpdateService updateService;
    private static AppUpdateService.UpdateApi updateApi;


    private AppUpdateService(){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(EndPoint.SERVER_BASE_URL).build();
        updateApi = retrofit.create(AppUpdateService.UpdateApi.class);

    }

    public static AppUpdateService getInstance(){
        if(updateService == null){
            updateService = new AppUpdateService();
        }
        return updateService;
    }

    public AppUpdateService.UpdateApi getUpdateApi(){
        return updateApi;
    }


    public interface UpdateApi{
        
        //버전 체크
        @POST("app_version.php")
        Call<AppVersionResponse> getUpdateVersion(@Body AppVersionRequest appVersionRequest);

    }
}
