package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerificationModel {
    @SerializedName("user_data")
    @Expose
    AboutUserModel aboutUserModel;

    public AboutUserModel getAboutUserModel() {
        return aboutUserModel;
    }

    public void setAboutUserModel(AboutUserModel aboutUserModel) {
        this.aboutUserModel = aboutUserModel;
    }


}
