package com.study.gongteacher.ui.custom.loadingdialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.study.gongteacher.R;
import com.study.gongteacher.databinding.CustomDialogLoadingBinding;
import com.study.gongteacher.ui.base.BaseDialog;


public class LoadingDialog extends BaseDialog {

        private String TitleText;

        private CustomDialogLoadingBinding binding;


        public LoadingDialog(@NonNull Context context, String title_text) {
            super(context);
            TitleText = title_text;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = CustomDialogLoadingBinding.inflate(getLayoutInflater());
            View v = binding.getRoot();
            setContentView(v);
            setCancelable(false);

            binding.tvLoadingDialogTitle.setText(TitleText);
        }


    }
