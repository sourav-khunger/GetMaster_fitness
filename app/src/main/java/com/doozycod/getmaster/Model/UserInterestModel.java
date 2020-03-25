package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInterestModel {
    @SerializedName("user_interests")
    @Expose
    private UserInterests userInterests;

    public UserInterests getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(UserInterests userInterests) {
        this.userInterests = userInterests;
    }
    public class UserInterests {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("interests")
        @Expose
        private List<String> interests = null;
        @SerializedName("response")
        @Expose
        private String response;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

    }
}
