package com.study.gongteacher.ui.signup;
import android.content.Context;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.study.gongteacher.data.dto.User;
import com.study.gongteacher.data.network.model.request.AccountAuthCodeRequest;
import com.study.gongteacher.data.network.model.request.AccountAuthRequest;
import com.study.gongteacher.data.network.model.request.CheckDuplicateIdRequest;
import com.study.gongteacher.data.network.model.request.SignUpRequest;
import com.study.gongteacher.data.network.model.response.AccountAuthCodeResponse;
import com.study.gongteacher.data.network.model.response.AccountAuthResponse;
import com.study.gongteacher.data.network.model.response.CheckDuplicateIdResponse;
import com.study.gongteacher.data.network.model.response.SignUpResponse;
import com.study.gongteacher.data.network.service.AccountService;
import com.study.gongteacher.ui.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseViewModel {

    //아이디 확인
    private MutableLiveData<CheckDuplicateIdResponse> checkDuplicateIdLiveData;

    //인증코드 발송
    private MutableLiveData<AccountAuthCodeResponse> sendAuthCodeLiveData;

    //인증코드 확인
    private MutableLiveData<AccountAuthResponse> authCodeLiveData;

    //회원가입
    private MutableLiveData<SignUpResponse> signupLiveData;


    private AccountService accountService;
    private Context context;

    private Gson gson = new Gson();

    SignUpViewModel(Context context, AccountService accountService){
            this.context = context;
            this.accountService = accountService;

            checkDuplicateIdLiveData = new MutableLiveData<>();
            sendAuthCodeLiveData = new MutableLiveData<>();
            authCodeLiveData = new MutableLiveData<>();
            signupLiveData = new MutableLiveData<>();

    }



    public MutableLiveData<CheckDuplicateIdResponse> getCheckDuplicateIdLiveData(){
        return checkDuplicateIdLiveData;
    }

    public MutableLiveData<AccountAuthCodeResponse> getSendAuthCodeLiveData(){
        return sendAuthCodeLiveData;
    }

    public MutableLiveData<AccountAuthResponse> getAuthCodeLiveData(){
        return authCodeLiveData;
    }

    public MutableLiveData<SignUpResponse> getSignupLiveData(){
        return signupLiveData;
    }



    //네트워크
    //아이디 중복확인
    public void doDuplicateCheckId(CheckDuplicateIdRequest request){
        accountService.getAccountApi().checkDuplicateId(request).enqueue(new Callback<CheckDuplicateIdResponse>() {
            @Override
            public void onResponse(Call<CheckDuplicateIdResponse> call, Response<CheckDuplicateIdResponse> response) {
                Log.d(" networking ","doDuplicateCheckId : "+gson.toJson(response.body()));
                checkDuplicateIdLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<CheckDuplicateIdResponse> call, Throwable t) {
                Log.e(" networking ","doDuplicateCheckId Error: "+t.getMessage());
            }
        });
    }

    //이메일 인증코드 발송
    public void doSendAuthCode(AccountAuthCodeRequest request){
        accountService.getAccountApi()
                .doAccountAuthCode(request)
                .enqueue(new Callback<AccountAuthCodeResponse>() {
            @Override
            public void onResponse(Call<AccountAuthCodeResponse> call, Response<AccountAuthCodeResponse> response) {
                Log.d("doSendAuthCode",""+gson.toJson(response.body()));
                sendAuthCodeLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<AccountAuthCodeResponse> call, Throwable t) {

            }
        });
    }

    //이메일 인증 코드 확인
    public void doCheckAuthCode(AccountAuthRequest request){
        accountService.getAccountApi()
                .doAccountAuth(request)
                .enqueue(new Callback<AccountAuthResponse>() {
            @Override
            public void onResponse(Call<AccountAuthResponse> call, Response<AccountAuthResponse> response) {
                Log.d("doCheckAuthCode",""+gson.toJson(response.body()));
                authCodeLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<AccountAuthResponse> call, Throwable t) {


            }
        });
    }

    //회원가입
    public void doSignUp(SignUpRequest request){

        accountService.getAccountApi().doSignUp(request).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.d("doSignUp",""+gson.toJson(response.body()));
                signupLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

}
