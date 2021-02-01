package com.study.gongteacher.ui.custom.notificaiondialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.study.gongteacher.R;
import com.study.gongteacher.databinding.CustomDialogNotificationBinding;
import com.study.gongteacher.ui.base.BaseDialog;


public class NotificationDialog extends BaseDialog {
    

    private String TitleText;
    private String MessageText;

    private CustomDialogNotificationBinding binding;

    public NotificationDialog(@NonNull Context context, String TitleText, String MessageText) {
        super(context);
        this.TitleText = TitleText;
        this.MessageText = MessageText;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //뷰 바인딩
        binding = CustomDialogNotificationBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        setCancelable(false);
        binding.tvDialogConfirmationTitle.setText(TitleText);
        binding.tvDialogConfirmationMessage.setText(MessageText);

    }




    private void setOnClickListner(){

        //확인 버튼
        binding.btnDialogConfirmationCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });




    }



}
