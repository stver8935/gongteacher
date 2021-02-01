package com.study.gongteacher.data;

import com.study.gongteacher.App;
import com.study.gongteacher.data.db.database.AppPreferencesHelper;
import com.study.gongteacher.data.db.database.LocalDatabase;
import com.study.gongteacher.data.network.service.AccountService;
import com.study.gongteacher.data.network.service.AppUpdateService;


public class
DataManager {
    private static DataManager dataManager;

    private DataManager(){};


    //데이터 매니저 싱글턴
    public static synchronized DataManager getInstance(){
        if (dataManager == null){
            dataManager = new DataManager();
        }
        return dataManager;
    }


    public LocalDatabase getLocalDatabse(){
        return LocalDatabase.getInstance(App.getGlobalApplicationContext());
    }

    //쉐어드프리퍼런스
    public AppPreferencesHelper getAppPreferencesHelper(){
        return AppPreferencesHelper.getInstance(App.getGlobalApplicationContext());
    }

    //앱 실행 서비스
    public AppUpdateService getAppUpdateService(){
        return AppUpdateService.getInstance();
    }

    //계정관련 네트워크 서비스
    public AccountService getAccountService(){
        return AccountService.getInstance();
    }


}
