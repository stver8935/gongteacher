package com.study.gongteacher.data.network.service;

import com.study.gongteacher.data.network.EndPoint;
import com.study.gongteacher.data.network.model.request.AccountAuthCodeRequest;
import com.study.gongteacher.data.network.model.request.AccountAuthRequest;
import com.study.gongteacher.data.network.model.request.ChatListReqeust;
import com.study.gongteacher.data.network.model.request.ChatRoomListRequest;
import com.study.gongteacher.data.network.model.request.DeleteAccountReqeust;
import com.study.gongteacher.data.network.model.request.CheckDuplicateIdRequest;
import com.study.gongteacher.data.network.model.request.LoginRequest;
import com.study.gongteacher.data.network.model.request.LoginUserInfoRequest;
import com.study.gongteacher.data.network.model.request.SignUpRequest;
import com.study.gongteacher.data.network.model.request.UpdateProfileRequest;
import com.study.gongteacher.data.network.model.request.UserAddressRequest;
import com.study.gongteacher.data.network.model.response.AccountAuthCodeResponse;
import com.study.gongteacher.data.network.model.response.AccountAuthResponse;
import com.study.gongteacher.data.network.model.response.ChatListResponse;
import com.study.gongteacher.data.network.model.response.ChatRoomListResponse;
import com.study.gongteacher.data.network.model.response.DeleteAccountResponse;
import com.study.gongteacher.data.network.model.response.CheckDuplicateIdResponse;
import com.study.gongteacher.data.network.model.response.LoginResponse;
import com.study.gongteacher.data.network.model.response.LoginUserInfoResponse;
import com.study.gongteacher.data.network.model.response.LogoutResponse;
import com.study.gongteacher.data.network.model.response.SignUpResponse;
import com.study.gongteacher.data.network.model.response.UpdateProfileResponse;
import com.study.gongteacher.data.network.model.response.UserAddressResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class AccountService {


    private static AccountService accountService;
    private static AccountApi accountApi;


    private AccountService(){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(EndPoint.SERVER_BASE_URL).build();
        accountApi = retrofit.create(AccountApi.class);

    }

    public static AccountService getInstance(){
        if(accountService == null){
            accountService = new AccountService();
        }
        return accountService;
    }

    public AccountApi getAccountApi(){
        return accountApi;
    }


    public interface AccountApi{


        //회원가입
        @POST("signup.php")
        Call<SignUpResponse> doSignUp(@Body SignUpRequest signUpRequest);

        //이메일 계정 인증
        @POST("email_auth.php")
        Call<AccountAuthResponse> doAccountAuth(@Body AccountAuthRequest accountAuthRequest);

        //이메일로 인증코드 발송
        @POST("send_mail.php")
        Call<AccountAuthCodeResponse> doAccountAuthCode(@Body AccountAuthCodeRequest accountAuthCodeRequest);

        //로그인
        @POST("login.php")
        Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

        //로그인 유저 정보 가져오기
        @POST("login_user_info.php")
        Call<LoginUserInfoResponse> doLoginUserInfo(@Body LoginUserInfoRequest loginUserInfoRequest);

        //로그아웃
        @POST("logout.php")
        Call<LogoutResponse> doLogout(@Body LoginRequest logoutRequest);

        //회원탈퇴
        @POST("delete_account.php")
        Call<DeleteAccountResponse> deleteAccount(@Body DeleteAccountReqeust deleteAccountReqeust);

        //프로필 변경
        @POST("update_profile.php")
        Call<UpdateProfileResponse> updateProfile(@Body UpdateProfileRequest updateProfileRequest);

        //사용자 위치 리스트 가져오기
        @POST("user_address.php")
        Call<UserAddressResponse> getUserAddress(@Body UserAddressRequest userAddressRequest);


        //아이디 중복 확인
        @POST("check_duplicate_id.php")
        Call<CheckDuplicateIdResponse> checkDuplicateId(@Body CheckDuplicateIdRequest duplicateCheckIdRequest);

        //채팅방 리스트 가져오기
        @POST("chat_room_list.php")
        Call<ChatRoomListResponse> getChatRoomList(@Body ChatRoomListRequest chatRoomListRequest);

        //채팅 리스트 가져오기
        @POST("chat_list.php")
        Call<ChatListResponse> getChatList(@Body ChatListReqeust chatListReqeust);


    }
}
