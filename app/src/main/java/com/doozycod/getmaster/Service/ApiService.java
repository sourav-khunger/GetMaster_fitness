package com.doozycod.getmaster.Service;


import com.doozycod.getmaster.Model.DeletePhotoModel;
import com.doozycod.getmaster.Model.ExploreModel;
import com.doozycod.getmaster.Model.LanguageModel;
import com.doozycod.getmaster.Model.UserInterestModel;
import com.doozycod.getmaster.Model.UserPhotosModel;
import com.doozycod.getmaster.Model.VerificationModel;
import com.doozycod.getmaster.OtpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//  api Interface for Retrofit
public interface ApiService {

    @POST("otp/send.php")
    @FormUrlEncoded
    Call<OtpModel> sendOTP(@Field("mobile") String mobile, @Field("country_code") String country_code);

    @POST("otp/verify.php")
    @FormUrlEncoded
    Call<VerificationModel> verifyOTP(@Field("id") String id, @Field("otp") String otp);

    @POST("user/about.php")
    @FormUrlEncoded
    Call<VerificationModel> aboutYouApi(@Field("id") String id,
                                        @Field("full_name") String full_name,
                                        @Field("email") String email,
                                        @Field("gender") String gender,
                                        @Field("user_type") String user_type);

    @POST("user/profile_pic.php")
    @FormUrlEncoded
    Call<VerificationModel> addProfilePic(@Field("id") String id,
                                          @Field("profile_pic") String profile_pic);

    @POST("photo/set.php")
    @FormUrlEncoded
    Call<UserPhotosModel> uploadPhotos(@Field("user_id") String id,
                                       @Field("photo") String photo);

    @GET("photo/get.php")
    Call<UserPhotosModel> getPhotos(@Field("user_id") String id,
                                    @Field("photo") String profile_pic);

    @POST("photo/delete.php")
    @FormUrlEncoded
    Call<UserPhotosModel> deletePhotos(@Field("id") String id,
                                       @Field("user_id") String user_id);

    @POST("interest/set.php")
    @FormUrlEncoded
    Call<UserInterestModel> addInterest(@Field("user_id") String user_id,
                                        @Field("interests") String interests);
    @POST("language/set.php")
    @FormUrlEncoded
    Call<LanguageModel> addLanguage(@Field("user_id") String user_id,
                                    @Field("languages") String languages);
    @POST("home/explore.php")
    @FormUrlEncoded
    Call<ExploreModel> getExplore(@Field("user_id") String user_id);

}
