package com.study.gongteacher.data.dto;

import com.gun0912.tedpermission.util.ObjectUtils;
import com.study.gongteacher.utils.CheckAccount;

public class SignUpAccount extends CheckAccount{
    private String id;
    private String pwd;
    private String confirmPwd;
    private String email;
    private String nickName;

    //아이디 중복확인 여부
    private boolean chkDuplicateId;

    //인증여부
    private boolean chkAuthCode;


    public boolean isChkDuplicateId() {
        return chkDuplicateId;
    }

    public void setChkDuplicateId(boolean chkDuplicateId) {
        this.chkDuplicateId = chkDuplicateId;
    }
    public boolean isChkAuthCode() {
        return chkAuthCode;
    }

    public void setChkAuthCode(boolean chkAuthCode) {
        this.chkAuthCode = chkAuthCode;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }










}
