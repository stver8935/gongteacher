package com.study.gongteacher.ui.login;

import android.accounts.Account;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaSession2;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.CallbackManager;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.study.gongteacher.R;
import com.study.gongteacher.data.DataManager;
import com.study.gongteacher.data.dto.LoginUserInfo;
import com.study.gongteacher.data.network.model.request.LoginRequest;
import com.study.gongteacher.data.network.model.request.LoginUserInfoRequest;
import com.study.gongteacher.data.network.model.response.LoginResponse;
import com.study.gongteacher.data.network.model.response.LoginUserInfoResponse;
import com.study.gongteacher.databinding.ActivityLoginBinding;
import com.study.gongteacher.ui.base.BaseActivity;
import com.study.gongteacher.ui.custom.confirmdialog.ConfirmDialog;
import com.study.gongteacher.ui.custom.loadingdialog.LoadingDialog;
import com.study.gongteacher.ui.main.MainActivity;
import com.study.gongteacher.ui.signup.SignUpActivity;
import com.google.gson.Gson;
import com.study.gongteacher.utils.CheckAccount;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends BaseActivity<LoginViewModel> implements LoginActivityNavigator {


    private Gson gson = new Gson();

    private String FcmToken;
    private LoadingDialog loadingDialog;
    private ConfirmDialog customDialog;


    private CallbackManager callbackManager;


    private ActivityLoginBinding binding;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);
        init();

    }

    private void init(){
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //계정 인증키값
                        Log.e("facebook",gson.toJson(loginResult.getAccessToken().getUserId()));

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("facebook",gson.toJson(error));

                    }
                });

        //옵저버 등록 및 초기화
        initObserver();
        //버튼 클릭 리스너
        initListener();
        getHashKey();


    }
    private void getHashKey(){
        try {                                                        // 패키지이름을 입력해줍니다.
            PackageInfo info = getPackageManager().getPackageInfo("com.study.gongteacher", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("hash","key_hash="+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void initObserver(){
        viewModel.getLoginResponseLiveData().observe(this,new LoginObserver());
        viewModel.getFcmTokenLiveData().observe(this,new FcmObserver());
        viewModel.getLoadingStatus().observe(this,new LoadingObserver());
        viewModel.getLoginUserInfoResponseLiveData().observe(this,new LoginUserInfoObserver());
        loadingDialog = new LoadingDialog(this,"로그인 중 입니다.");

    }


    private void initListener(){

        /*
        * 로그인 버튼 리스너
        * 소셜 로그인후 앱 데이터 베이스 내에 계정 정보가 없을시
        *  소셜 로그인 반환 정보로 자동 가입 실행
        * 소셜 로그인은 항상 자동로그인 상태
        * */

        //앱 계정 로그인
        binding.btnLogin.setOnClickListener(v ->{
            String id = binding.loginId.getText().toString();
            String pwd = binding.loginPassword.getText().toString();
            boolean isAutoLogin = binding.loginAuto.isChecked();

            LoginRequest request = new LoginRequest("app",id,pwd);
            viewModel.doLogin(request);

        });

        //네이버 로그인
        binding.civLoginNaver.setOnClickListener(v ->{
            viewModel.doNaverLogin();
        });

        //구글 로그인
        binding.civLoginGoogle.setOnClickListener(v ->{
//            viewModel.doGoogleLgin();
            signIn();

        });


        //페이스북 로그인
        binding.civLoginFacebook.setOnClickListener(v ->{
//            viewModel.doFacebookLogin();
            binding.btnFacebookLogin.performClick();
        });

        //카카오 로그인
        binding.civLoginKakao.setOnClickListener(v ->{
            viewModel.doKakaoLogin();

        });



        //회원가입 페이지 이동
        binding.loginSignup.setOnClickListener(v -> {
            openSignUpActivity(new Intent());
        });
    }



    private void signIn() {
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.google_id))
//                .requestEmail()
//                .build();
//
//        GoogleSignInClient mGoogleSignInClient;
//        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this,gso);
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.e("google result :",gson.toJson(data));
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        //페이스북
        if (callbackManager.onActivityResult(requestCode,resultCode,data)){
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        //카카오
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

    }


    @NonNull
    @Override
    protected LoginViewModel createViewModel() {
        LoginViewModelFactory factory = new LoginViewModelFactory(this,DataManager.getInstance().getAccountService());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }


    void onLoginBtnClick(){
        Log.d("onLoginBtnClick ","Click");
        CheckAccount account = new CheckAccount();

        String id = binding.loginId.getText().toString();
        String password = binding.loginPassword.getText().toString();

        if (account.checkNullText(id)){
            Toast.makeText(this, "아이디를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }else if (account.checkNullText(password)){
            Toast.makeText(this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }else{
//            viewModel.setIsLoading(true);
//            User user = new User();
//            user.UserType = "default";
//            user.Id = id;
//            user.Password = password;
//            user.FcmToken = FcmToken;
//            LoginRequest loginRequest = new LoginRequest(user);
//            String msg = gson.toJson(loginRequest);
//            Log.d(" LoginReqeust ",msg);
//            viewModel.loginNetwork(loginRequest);
        }


    }





    @Override
    public void openSignUpActivity(Intent intent) {
        intent.setClass(this,SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void openMainActivity(Intent intent) {
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }



//    //로그인 성공여부 처리
//    private void onLogin(LoginResponse response){
//
//        boolean status = response.getStatus();
//        String responseString = gson.toJson(response);
//        Log.d("Login Response ", responseString);
//
//
//        //로그인 성공여부 체크
//        if (status){
//            User user = response.getUser();
//
//            user.AutoLogin = loginAuto.isChecked();
//            //로그인 유저 저장
//            viewModel.setLoginUser(user);
//            loadingDialog.dismiss();
//
//            Intent intent = new Intent(this,MainActivity.class);
//            startMainActivity(intent);
//            finish();
//        }else{
//
//
//            createDialog(response);
//
//        }
//    }

//    //알림다이얼로그 생성
//    public void createDialog(LoginResponse response){
//
//        String logMsg = gson.toJson(response);
//        Log.d("craete Dialog ","msg"+logMsg);
//
//        String DialogTitle = "예기치 못한 에러";
//        String DialogMsg  ="잠시후 다시 시도해 주세요";
//
//        switch (response.getMessage()){
//            case "login":
//                DialogTitle = "로그인 실패 ("+response.getUser().LoginFailuresCount+"/5) ";
//                DialogMsg = "아이디나 비밀번호를 다시 입력해 주세요.";
//
//                break;
//            case "database":
//                DialogTitle= "데이터베이스 연결 오류";
//                DialogMsg = "잠시후 다시 시도해 주세요";
//
//                break;
//        }
//
//        NotificationDialog confirmationDialog = new NotificationDialog(this,DialogTitle,DialogMsg);
//        confirmationDialog.show();
//    }






    //로딩 옵저버
    private class LoadingObserver implements Observer<Boolean>{
        @Override
        public void onChanged(Boolean isLoading) {
            if(isLoading == null || !isLoading){
                loadingDialog.dismiss();
                return;
            }else if (isLoading){
                loadingDialog.show();
            }

        }
    }



//    //로그인 옵저버
    private class LoginObserver implements Observer<LoginResponse> {

        @Override
        public void onChanged(LoginResponse response) {

            String log = gson.toJson(response);
            Log.d("Login Observer ", "msg : "+log);

            if (response.isStatus()){
             //계정정보 요청
              viewModel.doLoginUserInfo(new LoginUserInfoRequest(response.getUserKey()));

            } else{
                //실패 카운트
                response.getLoginFailCount();

            }

        }
    }

    //로그인 계정정보 옵저버
    private class LoginUserInfoObserver implements Observer<LoginUserInfoResponse>{

        @Override
        public void onChanged(LoginUserInfoResponse response) {

            Log.d("Login User Info Load",gson.toJson(response));

            if (response.isStatus()){


               LoginUserInfo loginUserInfo = new LoginUserInfo();
               loginUserInfo.setAutoLogin(true);
               loginUserInfo.setUserType(response.getUserType());
               loginUserInfo.setUserKey(response.getUserKey());
               loginUserInfo.setUserId(response.getUserId());
               loginUserInfo.setEmail(response.getEmail());
               loginUserInfo.setIntroduce(response.getIntroduce());
               loginUserInfo.setProfileImagePath(response.getProfileImagePath());

                DataManager.getInstance().getAppPreferencesHelper().setLoginUser(loginUserInfo);
                LoginUserInfo aa = DataManager.getInstance().getAppPreferencesHelper().getLoginUser();

                String asd = gson.toJson(aa);

                Log.d("loginCheck ",asd);

            }else{

                Log.e("Login User Info Error",""+response.getMessage());

            }

        }
    }




    private class FcmObserver implements Observer<String>{

        @Override
        public void onChanged(String token) {
            if (!token.equals("")||!token.equals(null)){
                FcmToken = token;
            }else{
                finish();
                Log.e("Fcm Observer","no value token");
            }

            Log.d("token", FcmToken);

        }
    }



    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("google", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
    }
}
