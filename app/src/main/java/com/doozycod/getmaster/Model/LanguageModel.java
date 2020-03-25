package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LanguageModel {

    @SerializedName("user_languages")
    @Expose
    private UserLanguages userLanguages;

    public UserLanguages getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(UserLanguages userLanguages) {
        this.userLanguages = userLanguages;
    }
    public class UserLanguages {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("languages")
        @Expose
        private List<String> languages = null;
        @SerializedName("response")
        @Expose
        private String response;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

    }
}