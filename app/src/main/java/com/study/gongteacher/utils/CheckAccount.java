package com.study.gongteacher.utils;

import android.text.TextUtils;

import com.gun0912.tedpermission.util.ObjectUtils;

import java.util.regex.Pattern;

public class CheckAccount {


    //이메일
    private String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    //아이디  5 - 12 영숫자
    private String idRegex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";


    //사용자 이름 형식
    private String nickNameRegex = "^[a-zA-Zㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1}[a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣_]{4,11}$";

    //비속어 필터 처리 ..
    private String filter = "(쌍|썅)[\\\\S\\\\s]{0,3}(년|놈)";

    //비밀번호
    private String pwdRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
    //핸드폰 번호
    private String phoneRegex = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";


    //아이디 정규식 확인
    public boolean checkRegexId(String userId){
        return Pattern.matches(idRegex,userId);
    }

    //비밀번호 정규식 확인
    public boolean checkRegexPwd(String pwd){
        return Pattern.matches(pwdRegex,pwd);
    }

    //이메일 정규식확인
    public boolean checkRegexEmail(String email){
        return Pattern.matches(emailRegex,email);
    }

    //닉네임 정규식 체크
    public boolean checkRegexNickName(String nickName){
        return Pattern.matches(nickNameRegex,nickName);
    }

    //비밀번호와 비밀번호 확인 비교
    public boolean confirmPassword(String pwd , String confPwd){
        if (pwd.equals(confPwd)){
            return true;
        }else{
            return false;
        }
    }



    //텍스트 널체크
    public boolean checkNullText(String text){
       return TextUtils.isEmpty(text)?true:false;
    }

    //객체 널체크
    public boolean checkNullObj(Object o){
         return ObjectUtils.isEmpty(o);
    }




}
