package com.doozycod.getmaster.Service;


import com.doozycod.getmaster.Model.AboutUserModel;
import com.doozycod.getmaster.Model.VerificationModel;
import com.doozycod.getmaster.OtpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
