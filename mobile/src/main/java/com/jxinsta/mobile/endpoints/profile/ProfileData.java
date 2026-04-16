package com.jxinsta.mobile.endpoints.profile;

import android.org.json.JSONObject;

public class ProfileData {
    public String username;
    public String pk;
    public String name;
    public String biography;
    public String profilePicURL;
    public boolean isBusinessAccount;
    public boolean isPrivate;
    public boolean isVerified;
    public int posts;
    public int followers;
    public int followings;


    public ProfileData(JSONObject user) {
        this.pk = user.optString("pk_id", user.has("pk") ? String.valueOf(user.get("pk")) : user.optString("id", null));
        this.username = user.optString("username");
        this.name = user.optString("full_name");
        this.biography = user.optString("biography");
        this.profilePicURL = user.optString("profile_pic_url");
        this.isBusinessAccount = user.optBoolean("is_business");
        this.isPrivate = user.optBoolean("is_private");
        this.isVerified = user.optBoolean("is_verified");
        this.posts = user.optInt("media_count");
        this.followers = user.optInt("follower_count");
        this.followings = user.optInt("following_count");
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "username='" + username + '\'' +
                ", pk='" + pk + '\'' +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", profilePicURL='" + profilePicURL + '\'' +
                ", isBusinessAccount=" + isBusinessAccount +
                ", isPrivate=" + isPrivate +
                ", isVerified=" + isVerified +
                ", posts=" + posts +
                ", followers=" + followers +
                ", followings=" + followings +
                '}';
    }
}
