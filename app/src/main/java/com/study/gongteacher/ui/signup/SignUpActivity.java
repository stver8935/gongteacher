package com.study.gongteacher.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.study.gongteacher.R;
import com.study.gongteacher.data.DataManager;
import com.study.gongteacher.data.dto.Chat;
import com.study.gongteacher.data.dto.SignUpAccount;
import com.study.gongteacher.data.network.model.request.AccountAuthCodeRequest;
import com.study.gongteacher.data.network.model.request.AccountAuthRequest;
import com.study.gongteacher.data.network.model.request.CheckDuplicateIdRequest;
import com.study.gongteacher.data.network.model.request.SignUpRequest;
import com.study.gongteacher.data.network.model.response.AccountAuthCodeResponse;
import com.study.gongteacher.data.network.model.response.AccountAuthResponse;
import com.study.gongteacher.data.network.model.response.CheckDuplicateIdResponse;
import com.study.gongteacher.data.network.model.response.SignUpResponse;
import com.study.gongteacher.databinding.ActivitySignupBinding;
import com.study.gongteacher.ui.base.BaseActivity;
import com.study.gongteacher.ui.login.LoginActivity;
import com.study.gongteacher.utils.CheckAccount;

public class SignUpActivity extends BaseActivity<SignUpViewModel> implements SignUpNavigator {

    //뷰바인딩
    private ActivitySignupBinding binding;

    //Gson
    private Gson gson = new Gson();

    //회원가입 계정 정보를 넣어줄 객체
    private SignUpAccount signUpAccount;

    //회원가입 정보에대한 정규식이나 널 체크를 해주는 객체
    CheckAccount checkAccount = new CheckAccount();


    @NonNull
    @Override
    protected SignUpViewModel createViewModel() {
        SignUpViewModelFactory factory = new SignUpViewModelFactory(SignUpActivity.this, DataManager.getInstance().getAccountService());
        return ViewModelProviders.of(SignUpActivity.this,factory).get(SignUpViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInsatnceState) {
        super.onCreate(savedInsatnceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        init();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    //로그인 페이지 이동
    @Override
    public void openLoginActivity(Intent intent) {
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }


    //전체 초기화
    void init(){
        signUpAccount = new SignUpAccount();
        initObserver();
        initListener();
    }


    //뷰리스너 초기화
    private void initListener(){


        //아이디 텍스트 입력 변화
        binding.etSignupId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String id = s.toString();
                //아이디 텍스트가 변경 된다면 중복확인이 필요한 상태로 변경
                signUpAccount.setChkDuplicateId(false);
                if (!chkNullId(id) && chkRegexId(id)){
                    signUpAccount.setId(id);
                }
            }
        });

        //패스워드 입력 텍스트 감지
        binding.etSignupPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String pwd = s.toString();
                String confirmPwd = binding.etSignupConfirmPassword.getText().toString();
                if (!chkNullPwd(pwd) && chkRegexPwd(pwd)){
                    Log.d("Text Change ","insert pwd");

                    signUpAccount.setPwd(pwd);
                }
                comparePwd(pwd,confirmPwd);
            }
        });

        //패스워드 확인 입력 텍스트 감지
        binding.etSignupConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
               String confirmPwd = s.toString();
               String pwd = binding.etSignupPassword.getText().toString();

                if (!chkNullCofirmPwd(confirmPwd) && comparePwd(pwd,confirmPwd)){
                    signUpAccount.setConfirmPwd(confirmPwd);
                }
            }
        });

        //닉네임 입력 텍스트 감지
        binding.etSignupNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String nickName = s.toString();
                if (!chkNullNickName(nickName) && chkRegexNickName(nickName)){
                    signUpAccount.setNickName(nickName);
                }
            }
        });

        //이메일 입력 텍스트 감지
        binding.etSignupEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                if (!chkNullEmail(email) && chkRegexEmail(email)){
                    signUpAccount.setEmail(email);
                }
            }
        });

        //아이디 중복 확인 텍스트 감지
        binding.btnDuplicateId.setOnClickListener(v -> {
            Log.d("onClick","DuplicateId Btn Click");
            CheckDuplicateIdRequest request = new CheckDuplicateIdRequest(binding.etSignupId.getText().toString());
            viewModel.doDuplicateCheckId(request);
        });


        //이메일 인증 번호 전송 버튼
        binding.btnSignupSendAuthCode.setOnClickListener(v -> {
            Log.d("onClick","send AuthCode btn Click");
            String email = binding.etSignupEmail.getText().toString();
            viewModel.doSendAuthCode(new AccountAuthCodeRequest(email));
        });
        //이메일 인증번호 확인 버튼
        binding.btnCheckSignupAuthCode.setOnClickListener(v -> {
            Log.d("onClick","Check AuthCode Btn Click");

            String authCode = binding.etSignupEmailAuthCode.getText().toString();
            String email = binding.etSignupEmail.getText().toString();
            viewModel.doCheckAuthCode(new AccountAuthRequest(email,authCode));
        });
        //회원가입 버튼
        binding.btnSignup.setOnClickListener(v -> signUp(signUpAccount));

    }

    /*
     * 옵저버 초기화
     * 수정일  2021.01.28 stver8935
     * */
    private void initObserver(){

        //아이디 중복체크 데이터 옵저버
        viewModel.getCheckDuplicateIdLiveData().observe(this, duplicateCheckIdResponse -> {
            Log.d("Oberve","getCheckDuplicateIdLiveData : "+gson.toJson(duplicateCheckIdResponse));
            boolean status = duplicateCheckIdResponse.isStatus();
            if (status){
                signUpAccount.setChkDuplicateId(duplicateCheckIdResponse.isStatus());
                Toast.makeText(this, getText(R.string.id_can_use), Toast.LENGTH_SHORT).show();
                binding.tvSignupIdHint.setText(getText(R.string.id_can_use));
                binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(this,R.color.colorBlue));
            }else{

                Toast.makeText(this, getText(R.string.id_duplicate_can_not_use), Toast.LENGTH_SHORT).show();
                binding.tvSignupIdHint.setText(getText(R.string.id_duplicate_can_not_use));
                binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(this,R.color.colorRed));
            }

        });

        //이메일 인증 번호 전송 데이터 옵저버
        viewModel.getSendAuthCodeLiveData().observe(this, accountAuthCodeResponse -> {

            String response_string = gson.toJson(accountAuthCodeResponse);
            boolean status = accountAuthCodeResponse.isStatus();

            Log.d(" auth_code ",response_string);
            if (status){
                Toast.makeText(SignUpActivity.this, "인증번호가 발송 되었습니다", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SignUpActivity.this, "잠시후 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
            }
        });

        //이메일 인증
        viewModel.getAuthCodeLiveData().observe(this, accountAuthResponse -> {
            boolean status = accountAuthResponse.isStatus();
            if (status){
                signUpAccount.setChkAuthCode(status);
                Toast.makeText(SignUpActivity.this, "인증 되었습니다. 가입 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SignUpActivity.this,"인증번호를 다시 확인해 주세요 ",Toast.LENGTH_LONG).show();
            }

        });

        //회원가입 상태 확인 옵저버
        viewModel.getSignupLiveData().observe(this, response ->{
            Log.d("Observe","getSignupLiveData : "+gson.toJson(response));

            String type = response.getType();
            boolean status = response.isStatus();
            String msg = response.getMessage();
            CharSequence toastMsg;

            if (status){
                if (type.equals("signup")) {
                    //회원가입 성공
                    toastMsg = getText(R.string.signup_ok);
                    openLoginActivity(new Intent());
                    Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
                }else{
                    toastMsg = getText(R.string.unkown_error)+") Error Message"+msg+")";
                    Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
                }


            }else{
                switch (type){
                    case "signup":
                        toastMsg = getText(R.string.signup_fail);
                        break;
                    case "auth":
                        toastMsg = getText(R.string.auth_code_check);
                        break;
                    case "duplicate":
                        toastMsg = getText(R.string.id_duplicate_can_not_use);
                        break;
                    case "database":
                        toastMsg = getText(R.string.database_error);
                        break;
                    default:
                        toastMsg = getText(R.string.unkown_error)+") Error Message"+msg+")";
                }
                Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
            }
            
        });

    }







/*
* 클래스 내에서 사용할 함수
* 수정일  2021.01.28 stver8935
* */

    //아이디 널 체크 힌트 메시지 변경
    private boolean chkNullId(String id){

        boolean check = checkAccount.checkNullText(id);
        CharSequence notiText;
        Log.d(" chkNullId "," id : "+ id+" : "+check);

        if(check){
            //아이디 널 체크
            notiText = getText(R.string.id_please_insert);
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupIdHint.setText(notiText);
        }

        return check;
    }

    //아이디 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexId(String id){
        boolean check = checkAccount.checkRegexId(id);
        CharSequence notiText;

        Log.d(" chkRegexId "," id : "+ id+" : "+check);

        if(!check){
            //아이디 정규식 체크
            notiText = getText(R.string.id_can_not_use);
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }else{
            notiText = getText(R.string.id_can_use);
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }
        binding.tvSignupIdHint.setText(notiText);

        return check;
    }


    //비밀 번호 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexPwd(String pwd){

        String notiText;
        boolean check = checkAccount.checkRegexPwd(pwd);

        Log.d(" RegexPwd "," pwd : "+ pwd+" : "+check);

        if(!check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_can_not_use).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));

        }else{
            notiText = getText(R.string.pwd_can_use).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }
        binding.tvSignupPasswordHint.setText(notiText);
       return check;
    }
    //비밀번호 널 체크 및 힌트 메시지 변경
    private boolean chkNullPwd(String pwd){
        String notiText;
        boolean check = checkAccount.checkNullText(pwd);
        Log.d(" chkNullPwd ", " pwd : "+pwd+" : "+check);

        if(check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_please_insert).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupPasswordHint.setText(notiText);
        }
        return check;
    }


    //비밀 번호 확인 널 체크 및 힌트 메시지 변경
    private boolean chkNullCofirmPwd(String confirmPwd) {
        CharSequence notiText;
        boolean check = checkAccount.checkNullText(confirmPwd);
        Log.d(" chkNullCofirmPwd ", " confirmPwd : "+confirmPwd+" : "+check);

        if (check) {
            notiText = getText(R.string.pwd_miss_match);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupPasswordCheckHint.setText(notiText);
        }
        return check;
    }
    //비밀번호와 비밀번호 확인 비교  및 힌트 메시지 변경
    private boolean comparePwd(String pwd,String confirmPwd){
        CharSequence notiText;
        boolean check = checkAccount.confirmPassword(pwd,confirmPwd);
        Log.d(" comparePwd ", "pwd : "+pwd+" confirmPwd : "+confirmPwd+" : "+check);

        if(!check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_miss_match);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));

        }else{
            notiText = getText(R.string.pwd_match);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }
        binding.tvSignupPasswordCheckHint.setText(notiText);
        return check;
    }

    //닉네임 널 체크 및 힌트 메시지 변경
    private boolean chkNullNickName(String nickName){
        CharSequence notiText;
        boolean check = checkAccount.checkNullText(nickName);
        Log.d(" chkNullNickName ", "nickname : "+nickName+" : "+check);

        if (check){
            notiText = getText(R.string.nick_name_please_insert);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupNickNameHint.setText(notiText);
        }
        return check;
    }
    //닉네임 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexNickName(String nickName){
        CharSequence notiText;
        boolean check = checkAccount.checkRegexNickName(nickName);

        Log.d(" chkRegexNickName ", "nickname : "+nickName+" : "+check);

        if(!check){
            //사용할수 없는 닉네임 입니다.
            notiText = getText(R.string.nick_name_can_not_use);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }else{
            notiText = getText(R.string.nick_name_can_use);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }

        binding.tvSignupNickNameHint.setText(notiText);
        return check;
    }


    //이메일 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexEmail(String email) {
        CharSequence notiText;
        boolean check = checkAccount.checkRegexEmail(email);
        Log.d(" chkRegexEmail ", "email : "+email+" : "+check);

        if (check){
            notiText = getText(R.string.email_can_use);
            binding.tvSignupEmailHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));

        }else{
            notiText = getText(R.string.email_can_not_use);
            binding.tvSignupEmailHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }
        binding.tvSignupEmailHint.setText(notiText);
        return check;
    }
    //이메일 널 체크 및 힌트 메시지 변경
    public boolean chkNullEmail(String email) {
        CharSequence notiText;
        boolean check = checkAccount.checkNullText(email);
        Log.d(" chkNullEmail ", "email : "+email+" : "+check);
        if (check){
            notiText = getText(R.string.nick_name_please_insert);
            binding.tvSignupEmailHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupEmailHint.setText(notiText);
        }
        return check;
    }


    //회원가입 가능 여부확인
    private boolean signUp(SignUpAccount account) {
        Log.d("signUp","start");

        String id = account.getId();
        String pwd = account.getPwd();
        String confirmPwd = account.getConfirmPwd();
        String nickName = account.getNickName();
        String email = account.getEmail();
        boolean isDuplicateId = account.isChkDuplicateId();
        CharSequence msg;


        if (chkNullId(id) || !chkRegexId(id)){
            //아이디
            msg = getText(R.string.id_check);
            binding.etSignupId.requestFocus();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }else if (!isDuplicateId){
            //중복확인 여부
            msg = getText(R.string.id_duplicate_check);
            binding.etSignupId.requestFocus();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }else if(chkNullPwd(pwd) || !chkRegexPwd(pwd)){
            //패스워드
            msg = getText(R.string.pwd_check);
            binding.etSignupPassword.requestFocus();

        }else if (chkNullCofirmPwd(confirmPwd) || !comparePwd(pwd,confirmPwd)){
            //패스워드 확인
            msg = getText(R.string.confirm_pwd_check);
            binding.etSignupConfirmPassword.requestFocus();

        }else if (chkNullNickName(nickName) || !chkRegexNickName(nickName)){
            //닉네임
            msg = getText(R.string.nick_name_check);
            binding.etSignupNickName.requestFocus();

        }else if (chkNullEmail(email) || !chkRegexEmail(email)){
            msg = getText(R.string.email_check);
            binding.etSignupEmail.requestFocus();

        }else if (!account.isChkAuthCode()) {
            //인증 코드 확인
            msg = getText(R.string.auth_code_check);
            binding.etSignupEmailAuthCode.requestFocus();
        }else{
            viewModel.doSignUp(
                    new SignUpRequest(
                            "default",
                            account.getId(),
                            account.getPwd(),
                            account.getNickName(),
                            account.getEmail()));
            return true;
        }

        //요청이 되지 않았다면 메시지 출력 false
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return false;
    }



}
