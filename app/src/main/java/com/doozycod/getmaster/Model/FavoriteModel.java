package com.doozycod.getmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteModel {

    @SerializedName("user_favorites")
    @Expose
    private List<UserFavorite> userFavorites = null;

    public List<UserFavorite> getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(List<UserFavorite> userFavorites) {
        this.userFavorites = userFavorites;
    }

}
