package com.study.gongteacher.ui.custom.confirmdialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.study.gongteacher.R;
import com.study.gongteacher.databinding.CustomDialogConfirmBinding;
import com.study.gongteacher.ui.base.BaseDialog;

public class ConfirmDialog extends BaseDialog {
    private Context context;


    //패키지 버전 이름
    private String PackageName;
    //패키지 정보
    private PackageInfo PackageInfo = null;
    //다이얼로그 positive, negative 버튼 리스너
    private ConfirmDialogListener listener;
    //다이얼로그 타이틀 텍스트
    private String TitleText;
    //다이얼로그 메시지 텍스트
    private String MessageText;

    CustomDialogConfirmBinding binding;



    public ConfirmDialog(@NonNull Context context, ConfirmDialogListener listener, String title_text, String message_text) {
        super(context);
        this.listener = listener;
        TitleText = title_text;
        MessageText = message_text;

    }

    public ConfirmDialog(@NonNull Context context , String title_text, String message_text) {
        super(context);
        TitleText = title_text;
        MessageText = message_text;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CustomDialogConfirmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

            context=getContext();
            try {
                PackageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        //뷰 바인딩

        PackageName = PackageInfo.packageName;

    }




}



