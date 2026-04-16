package com.jxinsta.mobile.endpoints.profile;

import com.jxinsta.mobile.InstagramException;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;
import com.jxinsta.mobile.utils.Likable;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import android.org.json.JSONObject;

public class Story implements Likable {
    public String userID;
    public String username;
    public String storyId;
    public String download_url;
    public boolean is_video;
    public int views = -1;
    public int duration = -1;
    public int likes = -1;
    public final String auth;

    public Story(@NotNull JSONObject storyItem, String auth) {
        this.auth = auth;
        JSONObject user = storyItem.optJSONObject("user");
        if (user != null) {
            this.userID = user.optString("id");
            this.username = user.optString("username");
        }

        this.storyId = storyId = storyItem.optString("id");
        this.is_video = storyItem.optInt("media_type") == 2;

        if (storyItem.has("video_duration")) {
            this.duration = (int) storyItem.optDouble("video_duration");
        }

        this.likes = storyItem.optBoolean("has_privately_liked", false) ? 1 : 0;

        // Extract Download URL
        if (is_video && storyItem.has("video_versions")) {
            this.download_url = storyItem.optJSONArray("video_versions")
                    .optJSONObject(0)
                    .optString("url");
        } else if (storyItem.has("image_versions2")) {
            JSONObject imageVersions = storyItem.optJSONObject("image_versions2");
            if (imageVersions != null && imageVersions.has("candidates")) {
                this.download_url = imageVersions.optJSONArray("candidates")
                        .optJSONObject(0)
                        .optString("url");
            }
        }
    }


    @Override
    public String toString() {
        return "Story{" +
                "id='" + userID + '\'' +
                ", download_url='" + download_url + '\'' +
                ", isVideo='" + is_video + '\'' +
                ", views=" + views +
                ", duration=" + duration +
                ", likes=" + likes +
                '}';
    }

    @Override
    public void like() throws InstagramException {
        var body = Utils.genSignedBody(Map.of("media_id", storyId));
        Utils.post(Constants.Endpoints.STORY_LIKE, auth, body);
    }

    public void dislike() throws InstagramException {
        var body = Utils.genSignedBody(Map.of("media_id", storyId));
        Utils.post(Constants.Endpoints.STORY_UNLIKE, auth, body);
    }
}
