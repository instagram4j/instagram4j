package com.jxinsta.web.endpoints.profile;

import android.org.json.JSONObject;

/**
 * A data class representing basic information about an Instagram profile.
 * This class is used for read-only user information and as a base for the interactive {@link Profile} class.
 */
public class ProfileData {
    /** The username of the account. */
    public String username;
    /** The unique identifier (PK) of the account. */
    public String pk;
    /** The full name displayed on the profile. */
    public String name;
    /** The bio/biography of the user. */
    public String biography;
    /** The URL of the profile picture. */
    public String profilePicURL;
    /** Indicates if the account is a business account. */
    public boolean isBusinessAccount;
    /** Indicates if the account is private. */
    public boolean isPrivate;
    /** Indicates if the account is verified. */
    public boolean isVerified;
    /** The total number of posts made by this user. (Note: May need separate fetch for some endpoints). */
    public int posts;
    /** The number of followers. -1 if not fetched. */
    public int followers;
    /** The number of accounts this user is following. -1 if not fetched. */
    public int followings;

    /**
     * Constructs a ProfileData object from a JSON user object.
     *
     * @param user The JSON object containing user data from Instagram's API.
     */
    public ProfileData(JSONObject user){
        this.name = user.getString("full_name");
        this.pk = user.getString("id");
        this.username = user.getString("username");
        this.biography = user.optString("biography");
        this.profilePicURL = user.getString("profile_pic_url");
        this.isPrivate = user.getBoolean("is_private");
        this.isVerified = user.getBoolean("is_verified");
        this.followers = user.optInt("follower_count",-1);
        this.followings = user.optInt("following_count",-1);
        this.isBusinessAccount = user.optBoolean("is_business");
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                ", username='" + username + '\'' +
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
