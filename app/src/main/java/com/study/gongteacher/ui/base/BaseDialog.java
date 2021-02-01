package com.study.gongteacher.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면 사이즈 변수
        int screenWidth,screenHeight;

        //핸드폰 화면 px 사이즈
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;

        //커스텀 다이얼로그 기본 사이즈 설정
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());

        lp.width = screenWidth/3*2;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        Window window = getWindow();
        window.setAttributes(lp);

        Log.d("Screen Size : "," height : "+screenHeight+" width : "+screenWidth);
        Log.d("Dialog Size : "," height : "+lp.height+" width : "+lp.width);



    }


}
