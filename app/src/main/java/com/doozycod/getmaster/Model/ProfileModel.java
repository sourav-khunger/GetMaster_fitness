package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ProfileModel {

    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("user_photos")
    @Expose
    private List<String> userPhotos = null;
    @SerializedName("user_languages")
    @Expose
    private List<String> userLanguages = null;
    @SerializedName("user_interests")
    @Expose
    private List<String> userInterests = null;
    @SerializedName("response")
    @Expose
    private String response;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<String> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<String> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public List<String> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(List<String> userLanguages) {
        this.userLanguages = userLanguages;
    }

    public List<String> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(List<String> userInterests) {
        this.userInterests = userInterests;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}