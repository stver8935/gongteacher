package com.study.gongteacher.method;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class Permission {

    private Context context;

    private String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private String CAMERA = Manifest.permission.CAMERA;

    public Permission(Context context) {
        this.context = context;

    }


    //필요 권한 리스트에 넣고 반환해주는 함수
    private String[] getPermission(String PermisisonName){
        ArrayList<String> PermissionList = new ArrayList<>();

        if (CAMERA.equals(PermisisonName)){
            PermissionList.add(CAMERA);
        }else if (READ_EXTERNAL_STORAGE.equals(PermisisonName)){
            PermissionList.add(READ_EXTERNAL_STORAGE);
        }else if (WRITE_EXTERNAL_STORAGE.equals(PermisisonName)){
            PermissionList.add(WRITE_EXTERNAL_STORAGE);
        }else if (PermisisonName.equals("ALL")){
            PermissionList.add(CAMERA);
            PermissionList.add(READ_EXTERNAL_STORAGE);
            PermissionList.add(WRITE_EXTERNAL_STORAGE);
        }

        Log.d("Permission List : ",""+PermissionList.toString());


        return PermissionList.toArray(new String[PermissionList.size()]);
    }


    //권한 확인
    public boolean checkPermission(String PermissionName){
        boolean check = false;

        int checkGranted = PackageManager.PERMISSION_GRANTED;

        int cameraCheck = ContextCompat.checkSelfPermission(context,CAMERA);
        int storageReadCheck = ContextCompat.checkSelfPermission(context,READ_EXTERNAL_STORAGE);
        int storageWriteCheck = ContextCompat.checkSelfPermission(context,WRITE_EXTERNAL_STORAGE);



        switch (PermissionName){
            case "CAMERA":
                    if (cameraCheck == checkGranted){
                        return true;
                    }
                break;
            case "READ_EXTERNAL_STORAGE":
                    if (storageReadCheck == checkGranted){
                        return true;
                    }
                break;
            case "WRITE_EXTERNAL_STORAGE":
                    if(storageWriteCheck == checkGranted){
                        return true;
                    }
                break;
            case "ALL":
                    if (cameraCheck == checkGranted
                            &&storageReadCheck == checkGranted
                            &&storageWriteCheck == checkGranted){
                        return true;
                    }

                break;

        }

        return check;
    }

    public void requestSplash(PermissionListener listener){
            TedPermission.with(context)
                    .setRationaleMessage("앱 설정 권한이 필요합니다")
                    .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있어요.")
                    .setPermissionListener(listener)
                    .setPermissions(getPermission("ALL"))
                    .check();
    }


    public void requestCamera(PermissionListener listener){

            TedPermission.with(context)
                    .setPermissionListener(listener)
                    .setRationaleMessage("카메라 접근 권한이 필요합니다")
                    .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있어요.")
                    .setPermissions(getPermission(CAMERA))
                    .check();

    }



    public void requestReadStorage(PermissionListener listener){
            TedPermission.with(context)
                    .setPermissionListener(listener)
                    .setRationaleMessage("앨범 읽기 권한이 필요합니다")
                    .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있어요.")
                    .setPermissions(getPermission(READ_EXTERNAL_STORAGE))
                    .check();


    }

    public void requestWriteStorage(PermissionListener listener){
            TedPermission.with(context)
                    .setPermissionListener(listener)
                    .setRationaleMessage("앨범 쓰기 권한이 필요합니다")
                    .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있어요.")
                    .setPermissions(getPermission(WRITE_EXTERNAL_STORAGE))
                    .check();
    }

}
