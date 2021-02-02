package com.study.gongteacher.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.study.gongteacher.R;

public class NetWorkResponseFillter {

//서버 커넥션으 타입의 메시지를 출력 해주는 함수
    public String getResponseMsg(@NonNull Context context,@NonNull String type){
        CharSequence msg;
        switch (type){
            case "auth_miss_match":
                msg =  context.getText(R.string.auth_miss_match);
                break;
            case "auth_code_finish":
                msg = context.getText(R.string.auth_code_finish);
                break;
            case "send_auth_code_ok":
                msg = context.getText(R.string.send_auth_code_ok);
                break;
            case "send_auth_code_fail":
                msg = context.getText(R.string.send_auth_code_fail);
                break;
            case "auth_time_finish":
                msg=  context.getText(R.string.auth_time_finish);
                break;
            case "auth_success":
                msg =  context.getText(R.string.auth_ok);
                break;
            case"db_connection_fail":
                msg =  context.getText(R.string.db_connection_fail);
                break;
            case"db_connection_ok":
                msg =  context.getText(R.string.db_connection_ok);
                break;
            case "db_read_fail":
                msg = context.getText(R.string.db_read_fail);
                break;
            case "db_upload_fail":
                msg = context.getText(R.string.db_upload_fail);
                break;
            case "id_duplicate_fail":
                msg =  context.getText(R.string.id_duplicate_not_use);
                break;
            case "id_duplicate_ok":
                msg = context.getText(R.string.id_use);
                break;
            case "account_auth_fail":
                msg = context.getText(R.string.account_auth_fail);
                break;
            case "signup_ok":
                msg = context.getText(R.string.signup_ok);
                break;
            case "signup_fail":
                msg = context.getText(R.string.signup_fail);
                break;
            default:
                msg =  context.getText(R.string.unkown_error);
        }
        return (String) msg;

    }







}
