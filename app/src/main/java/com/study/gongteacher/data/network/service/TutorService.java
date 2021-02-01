package com.study.gongteacher.data.network.service;

import com.study.gongteacher.data.network.EndPoint;
import com.study.gongteacher.data.network.model.request.TutorListRequest;
import com.study.gongteacher.data.network.model.request.TutorProfileRequest;
import com.study.gongteacher.data.network.model.response.TutorListResponse;
import com.study.gongteacher.data.network.model.response.TutorProfileResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class TutorService {
    private TutorService tutorService;
    private TutorServiceApi tutorServiceApi;


   private TutorService(){
       Retrofit retrofit =
               new Retrofit.Builder()
               .baseUrl(EndPoint.SERVER_BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       tutorServiceApi = retrofit.create(TutorServiceApi.class);

   }


   public TutorService getInstance(){

       if(tutorService == null){
           tutorService =new TutorService();
       }
       return tutorService;
   }

   public TutorServiceApi getTutorServiceApi(){
       return tutorServiceApi;
   }


   private interface TutorServiceApi{
       @FormUrlEncoded
       @POST("tutor_list.php")
       Call<TutorListResponse> doTutorList(TutorListRequest tutorListRequest);

       @POST("tutor_profile.php")
       Call<TutorProfileResponse> doTutorProfile(TutorProfileRequest tutorRequest);


   }






}
