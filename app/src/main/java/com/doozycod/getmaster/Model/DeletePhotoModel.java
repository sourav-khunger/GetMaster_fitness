package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeletePhotoModel {

    @SerializedName("user_photos")
    @Expose
    private List<UserPhoto> userPhotos = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<UserPhoto> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<UserPhoto> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public class UserPhoto {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

    }
}