package com.study.gongteacher.ui.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.data.OAuthLoginState;
import com.study.gongteacher.R;
import com.study.gongteacher.data.db.database.AppPreferencesHelper;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.request.LoginRequest;
import com.study.gongteacher.data.network.model.request.LoginUserInfoRequest;
import com.study.gongteacher.data.network.model.response.LoginResponse;
import com.study.gongteacher.data.network.model.response.LoginUserInfoResponse;
import com.study.gongteacher.data.network.service.AccountService;
import com.study.gongteacher.data.network.service.FaceBookLoginService;
import com.study.gongteacher.ui.base.BaseViewModel;
import com.study.gongteacher.ui.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class LoginViewModel extends BaseViewModel {

    private Context context;
    private Gson gson = new Gson();

    //로그인 요청
    private MutableLiveData<LoginResponse> loginResponseLiveData;
    //로그인 유저 정보
    private MutableLiveData<LoginUserInfoResponse> loginUserInfoResponseLiveData;

    private MutableLiveData<String> fcmToken;

    private MutableLiveData<User> User;

    private AppPreferencesHelper mPref;

    //페이스북 로그인 서비스
    private FaceBookLoginService faceBookLoginService;
    //로그인 서비스
    private AccountService accountService;
    //네이버 로그인 모듈
    private OAuthLogin mAuthLoginModule;


    //로그인 상태
    private MutableLiveData<Boolean> isLoginState;


    //test
    SessionCallback callback;




    public LoginViewModel(Context context,AccountService accountService){

        this.context = context;

        //서비스
        this.faceBookLoginService = new FaceBookLoginService();
        this.accountService = accountService;

        //라이브 데이터
        this.loginResponseLiveData = new MutableLiveData<>();
        this.fcmToken = new MutableLiveData<>();
        this.User = new MutableLiveData<>();
        this.isLoginState = new MutableLiveData<>();
        this.loginUserInfoResponseLiveData = new MutableLiveData<>();

        //네이버 로그인 모듈
        mAuthLoginModule = OAuthLogin.getInstance();

        init();
    }


    private void init(){
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();


    }


    //앱 계정 로그인
    public void  doLogin(LoginRequest request){
        //로그인 여부
        accountService.getAccountApi()
                .doLogin(request)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        String msg = gson.toJson(response.body());
                        Log.d("msg",msg);
                        loginResponseLiveData.postValue(response.body());

                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });

    };
    //네이버 계정 로그인
   public void doNaverLogin(){
        String clientId = (String) context.getText(R.string.naver_client_id);
        String secret = (String) context.getText(R.string.naver_secret);
        String name = "com.study.gongteacher";

       //네이버 로그인 모듈 초기화
       mAuthLoginModule.init(
               context,
               clientId,
               secret,
               name
       );

       OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
           @Override
           public void run(boolean success) {
               if (success){
                   String accessToken = mAuthLoginModule.getAccessToken(context);
                   String refreshToken = mAuthLoginModule.getRefreshToken(context);
                   long expiresAt = mAuthLoginModule.getExpiresAt(context);
                   String tokenType = mAuthLoginModule.getTokenType(context);
                   String name = mAuthLoginModule.getState(context).name();

                   Log.d("LoginData","accessToken : "+ accessToken);
                   Log.d("LoginData","refreshToken : "+ refreshToken);
                   Log.d("LoginData","expiresAt : "+ expiresAt);
                   Log.d("LoginData","tokenType : "+ tokenType);
                   Log.d("LoginData","name : "+ name);

                   doLogin(new LoginRequest("naver",refreshToken,""));

               }else{
                   String errorCode = mAuthLoginModule
                           .getLastErrorCode(context).getCode();
                   String errorDesc = mAuthLoginModule.getLastErrorDesc(context);
                   Toast.makeText(context, "errorCode:" + errorCode
                           + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
               }
           }

       };

       mAuthLoginModule.startOauthLoginActivity(((LoginActivity)context),mOAuthLoginHandler);



   }



    //구글 계정 로그인
    public void doGoogleLgin(){

    };
    //페이스북 계정 로그인
    public void doFacebookLogin(){};

    //카카오 계정 로그인
    public void doKakaoLogin(){

    };




    public void doLoginUserInfo(LoginUserInfoRequest request){
        Log.d("doLoginUserInfo",gson.toJson(request));

        accountService.getAccountApi()
                        .doLoginUserInfo(request)
                        .enqueue(new Callback<LoginUserInfoResponse>() {
            @Override
            public void onResponse(Call<LoginUserInfoResponse> call, Response<LoginUserInfoResponse> response) {
                Log.d("doLoginUserInfo","successed : "+gson.toJson(response.body()));

                getLoginUserInfoResponseLiveData().postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginUserInfoResponse> call, Throwable t) {
                Log.e("doLoginUserInfo","fail "+t.getMessage());
            }
        });
    }


   //서버 로그인 상태 확인
    public void doLoginCheck(){};

   //네이버 로그인 상태 확인
    public void doNaverLoginCheck(){
        boolean  check = OAuthLoginState.OK.equals(mAuthLoginModule.getInstance().getState(context));
        Log.d("network","naver Login state : "+check);
//        if (check){
//
//        }else{
//
//        }
    }
    //구글 로그인 상태 확인
    public void doGoogleLoginCheck(){}
    //페이스북 로그인 상태 확인
    public void doFacebookLoginCheck(){}
    //카카오 로그인 상태확인
    public void doKakaoLoginCheck(){}





    /*MutableLiveData*/
    //로딩 상태 라이브 데이터
    public MutableLiveData<Boolean> getLoadingStatus(){
        return super.getLoadingStatus();
    }

    //fcm 토큰 라이브 데이터
    MutableLiveData<String> getFcmTokenLiveData(){
        return this.fcmToken;
    }
    //로그인 상태 라이브 데이터
    MutableLiveData<Boolean> getIsLoginState(){
        return this.isLoginState;
    }
    //로그인 요청 라이브 데이터
    MutableLiveData<LoginResponse> getLoginResponseLiveData(){return this.loginResponseLiveData;}
    //로그인 유저 정보 요청 라이브 데이터
    MutableLiveData<LoginUserInfoResponse> getLoginUserInfoResponseLiveData(){
        return this.loginUserInfoResponseLiveData;
    }


    /*이하 메소드*/
    @Override
    public void setIsLoading(boolean loading) {
        super.setIsLoading(loading);
    }




    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(context.getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        ((LoginActivity)context).finish();
                    } else {
                        Toast.makeText(context.getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(context.getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    result.getProfileImagePath();
                    result.getNickname();
                    String asd = gson.toJson(result);

                    Log.e("kakao ok",""+asd);

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(context.getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



}
