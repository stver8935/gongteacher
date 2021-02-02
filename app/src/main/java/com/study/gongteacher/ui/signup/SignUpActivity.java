package com.study.gongteacher.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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
import com.study.gongteacher.data.dto.SignUpAccount;
import com.study.gongteacher.data.network.model.request.AccountAuthCodeRequest;
import com.study.gongteacher.data.network.model.request.AccountAuthRequest;
import com.study.gongteacher.data.network.model.request.CheckDuplicateIdRequest;
import com.study.gongteacher.data.network.model.request.SignUpRequest;
import com.study.gongteacher.databinding.ActivitySignupBinding;
import com.study.gongteacher.ui.base.BaseActivity;
import com.study.gongteacher.ui.login.LoginActivity;
import com.study.gongteacher.utils.CheckAccount;
import com.study.gongteacher.utils.NetWorkResponseFillter;
import com.study.gongteacher.utils.Timer;
import com.study.gongteacher.utils.TimerConverter;

public class SignUpActivity extends BaseActivity<SignUpViewModel> implements SignUpNavigator {

    //뷰바인딩
    private ActivitySignupBinding binding;

    //Gson
    private Gson gson = new Gson();

    //회원가입 계정 정보를 넣어줄 객체
    private SignUpAccount signUpAccount;

    //회원가입 정보에대한 정규식이나 널 체크를 해주는 객체
    private CheckAccount checkAccount = new CheckAccount();

    //이메일 인증 코드 키
    private int AuthCodeKey;

    //네트워크 연결 타입에 대한 메시지를 반환 해주는 객체
    private NetWorkResponseFillter rpFillter;

    // 핸들러
   private Thread timerThread;


   //인증 시간내 인지 구분하는 변수
   private boolean isAuthTime = false;


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
    protected void onDestroy() {
        super.onDestroy();

        //타이머 스레드 종료
        if (timerThread.isAlive()){
            timerThread.interrupt();
        }
    }

    // 초기화
    void init(){
        rpFillter = new NetWorkResponseFillter();
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
            viewModel.doCheckAuthCode(new AccountAuthRequest(email,AuthCodeKey,authCode));
        });
        //회원가입 버튼
        binding.btnSignup.setOnClickListener(v -> signUp(signUpAccount));




        //인증 시간
        binding.tvSignupAuthTime.setText("안녕");
    }

    /*
     * 옵저버 초기화
     * 수정일  2021.01.28 stver8935
     * */
    private void initObserver(){

        //아이디 중복체크 데이터 옵저버
        viewModel.getCheckDuplicateIdLiveData().observe(this, response -> {
            Log.d("Oberve","getCheckDuplicateIdLiveData : "+gson.toJson(response));

            String msg = rpFillter.getResponseMsg(this,response.getType());
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            binding.tvSignupIdHint.setText(msg);
            if (response.isStatus()){

                signUpAccount.setChkDuplicateId(response.isStatus());
                binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(this,R.color.colorBlue));

            }else{

                binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(this,R.color.colorRed));

            }

        });

        //이메일 인증 번호 전송 데이터 옵저버
        viewModel.getSendAuthCodeLiveData().observe(this, response -> {
            Log.d(" Observer "," accountAuthCodeResponse : "+gson.toJson(response));
            String msg = rpFillter.getResponseMsg(this,response.getType());
//            Toast.makeText(SignUpActivity.this, "잠시후 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
//            Toast.makeText(SignUpActivity.this, "인증번호가 발송 되었습니다", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            if (response.isStatus()){
                //5분간 인증 유효시간 설정
                startTimer(30000);
                AuthCodeKey = response.getAuthCodeKey();
            }

        });

        //이메일 인증 번호 확인 옵저버
        viewModel.getAuthCodeLiveData().observe(this, accountAuthResponse -> {
            Log.e("email_auth",gson.toJson(accountAuthResponse));
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
            Log.d("Observer"," accountAuthResponse : "+gson.toJson(response));

            String msg = rpFillter.getResponseMsg(this,response.getType());
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            if (response.isStatus()){
                //회원가입 성공
                openLoginActivity(new Intent());
            }

        });

    }




    //로그인 페이지 이동
    @Override
    public void openLoginActivity(Intent intent) {
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }



/*
* 클래스 내에서 사용할 함수
* 수정일  2021.01.28 stver8935
* */

    //아이디 널 체크 힌트 메시지 변경
    private boolean chkNullId(String id){
        Log.d(" chkNullId "," id : "+ id);

        if(checkAccount.checkNullText(id)){
            //아이디 널 체크
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupIdHint.setText(getText(R.string.id_insert));
        }

        return checkAccount.checkNullText(id);
    }

    //아이디 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexId(String id){
        Log.d(" chkRegexId "," id : "+ id);
        CharSequence notiText;
        boolean check = checkAccount.checkRegexId(id);

        if(check){
            //아이디 정규식 체크
            notiText = getText(R.string.id_use);
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }else{
            notiText = getText(R.string.id_not_use);
            binding.tvSignupIdHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }
        binding.tvSignupIdHint.setText(notiText);

        return check;
    }


    //비밀 번호 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexPwd(String pwd){
        Log.d(" RegexPwd "," pwd : "+ pwd);
        boolean check = checkAccount.checkRegexPwd(pwd);
        String notiText;

        if(check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_use).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }else{
            notiText = getText(R.string.pwd_not_use).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }
        binding.tvSignupPasswordHint.setText(notiText);
       return check;
    }



    //비밀번호 널 체크 및 힌트 메시지 변경
    private boolean chkNullPwd(String pwd){
        Log.d(" chkNullPwd ", " pwd : "+pwd);
        String notiText;
        boolean check = checkAccount.checkNullText(pwd);
        if(check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_insert).toString();
            binding.tvSignupPasswordHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupPasswordHint.setText(notiText);
        }
        return check;
    }


    //비밀 번호 확인 널 체크 및 힌트 메시지 변경
    private boolean chkNullCofirmPwd(String confirmPwd) {
        CharSequence notiText;
        boolean check = checkAccount.checkNullText(confirmPwd);
        Log.d(" chkNullCofirmPwd ", " confirmPwd : "+confirmPwd);

        if (check) {
            notiText = getText(R.string.pwd_insert);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupPasswordCheckHint.setText(notiText);
        }
        return check;
    }

    //비밀번호와 비밀번호 확인 비교  및 힌트 메시지 변경
    private boolean comparePwd(String pwd,String confirmPwd){
        Log.d(" comparePwd ", "pwd : "+pwd+" confirmPwd : "+confirmPwd);

        CharSequence notiText;
        boolean check = checkAccount.confirmPassword(pwd,confirmPwd);

        if(check){
            //비밀번호 형식에 맞지 않다면
            notiText = getText(R.string.pwd_match);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));
        }else{
            notiText = getText(R.string.pwd_miss_match);
            binding.tvSignupPasswordCheckHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }
        binding.tvSignupPasswordCheckHint.setText(notiText);
        return check;
    }

    //닉네임 널 체크 및 힌트 메시지 변경
    private boolean chkNullNickName(String nickName){
        Log.d(" chkNullNickName ", "nickname : "+nickName);

        CharSequence notiText;
        boolean check = checkAccount.checkNullText(nickName);

        if (check){
            notiText = getText(R.string.nick_name_insert);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
            binding.tvSignupNickNameHint.setText(notiText);
        }
        return check;
    }
    //닉네임 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexNickName(String nickName){
        Log.d(" chkRegexNickName ", "nickname : "+nickName);

        CharSequence notiText;
        boolean check = checkAccount.checkRegexNickName(nickName);

        if(check){
            //사용할수 없는 닉네임 입니다.
            notiText = getText(R.string.nick_name_not_use);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));

        }else{
            notiText = getText(R.string.nick_name_use);
            binding.tvSignupNickNameHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }

        binding.tvSignupNickNameHint.setText(notiText);
        return check;
    }


    //이메일 정규식 체크 및 힌트 메시지 변경
    private boolean chkRegexEmail(String email) {
        Log.d(" chkRegexEmail ", "email : "+email);

        CharSequence notiText;
        boolean check = checkAccount.checkRegexEmail(email);

        if (check){
            notiText = getText(R.string.email_use);
            binding.tvSignupEmailHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorBlue));

        }else{
            notiText = getText(R.string.email_not_use);
            binding.tvSignupEmailHint.setTextColor(ContextCompat.getColor(SignUpActivity.this,R.color.colorRed));
        }
        binding.tvSignupEmailHint.setText(notiText);
        return check;
    }
    //이메일 널 체크 및 힌트 메시지 변경
    public boolean chkNullEmail(String email) {
        Log.d(" chkNullEmail ", "email : "+email);

        CharSequence notiText;
        boolean check = checkAccount.checkNullText(email);

        if (check){
            notiText = getText(R.string.nick_name_insert);
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
        }else if(!isAuthTime){
            msg = getText(R.string.auth_time_finish);
        }else{
            viewModel.doSignUp(
                    new SignUpRequest(
                            "app",
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


    //인증 코드 타이머 스레드 실행
    private void startTimer(long authTime){

        //스레드 객체가 null 이 아니고 실행 되고 있을때 인터럽트를 건다.
        if (timerThread!=null && timerThread.isAlive()){
            timerThread.interrupt();
        }

        timerThread = new Timer(timerHandler,authTime);
        timerThread.start();

    }


    //핸들러
    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            String type = msg.getData().getString("type");
            Log.e("handler","Handle Message type : "+type);

            try {
                switch (type){
                    case "timer":
                        long time = msg.getData().getLong("time");

                        if (time<=0){
                            //인증 유효 시간이 0이라면 타이머 스레드를 멈추고 메시지 출력
                            timerThread.interrupt();
                            Toast.makeText(SignUpActivity.this, getText(R.string.auth_time_finish), Toast.LENGTH_SHORT).show();
                            isAuthTime= false;
                        }else{

                            TimerConverter timerConverter = new TimerConverter();
                            String timeStr = timerConverter.convertStopWatch(time);
                            binding.tvSignupAuthTime.setText(timeStr);
                            isAuthTime = true;
                        }
                        break;

                    default:
                        Log.e("handle Msg type","data type unkown :"+type);
                }






            }catch (NullPointerException e){

            }


        }
    };

}
