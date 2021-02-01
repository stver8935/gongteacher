package com.study.gongteacher.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class PackageInfo {

    private Context context;
    private android.content.pm.PackageInfo packageInfo;

    public PackageInfo(Context context){
        this.context = context;

        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);

        } catch (
                PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getVersionNeme(){ return packageInfo.versionName; }
    public String getPackageName(){ return packageInfo.packageName; }
    public int getVersionCode(){ return packageInfo.versionCode; }

}
