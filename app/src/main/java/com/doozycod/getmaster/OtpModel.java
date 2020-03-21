package com.doozycod.getmaster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpModel {


    @SerializedName("user_data")
    @Expose
    user_data user_data;

    public user_data getUser_data() {
        return user_data;
    }

    public void setUser_data(user_data user_data) {
        this.user_data = user_data;
    }

    public class user_data {

        @SerializedName("id")
        @Expose
        String id;
        @SerializedName("otp")
        @Expose
        String otp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
    }
}
