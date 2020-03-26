
package com.doozycod.getmaster.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExploreModel {

    @SerializedName("featured_users")
    @Expose
    private List<FeaturedUser> featuredUsers = null;
    @SerializedName("recent_users")
    @Expose
    private List<RecentUser> recentUsers = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<FeaturedUser> getFeaturedUsers() {
        return featuredUsers;
    }

    public void setFeaturedUsers(List<FeaturedUser> featuredUsers) {
        this.featuredUsers = featuredUsers;
    }

    public List<RecentUser> getRecentUsers() {
        return recentUsers;
    }

    public void setRecentUsers(List<RecentUser> recentUsers) {
        this.recentUsers = recentUsers;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
