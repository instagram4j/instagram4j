package com.jxinsta.web.endpoints.profile;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

/**
 * Represents an Instagram story or highlight item.
 * Contains metadata about the story, including user information, media type, and download URL.
 */
public class Story {
    /** The PK (id) of the user who posted the story. */
    public String userID;
    /** The username of the user who posted the story. (Note: May not always be populated). */
    public String username;
    /** The unique identifier of the story item. */
    public String storyId;
    /** The URL to download the story media (image or video). */
    public String download_url;
    /** Whether the story is a video. */
    public boolean is_video;
    /** The number of views the story has received. -1 if unknown. */
    public int views = -1;
    /** The duration of the video story in seconds. -1 if not a video. */
    public int duration = -1;
    /** The number of likes the story has received. -1 if unknown. */
    public int likes = -1;

    /**
     * Internal constructor for Story.
     *
     * @param item The JSON object containing story item data.
     */
    public Story(@NotNull JSONObject item) {
        this.storyId = item.getString("id");
        this.userID = item.getJSONObject("user").getString("pk");
        this.is_video = item.getInt("media_type") == 2;
        if (is_video) {
            this.download_url = item.getJSONArray("video_versions").getJSONObject(0).getString("url");
            this.duration = (int) item.getFloat("video_duration");
        } else
            this.download_url = item.getJSONObject("image_versions2").getJSONArray("candidates").getJSONObject(0).getString("url");

        this.views = item.optInt("viewer_count", -1);
    }

    /**
     * Fetches the actual story objects for a given list of reel/user IDs.
     *
     * @param ids     An array of reel IDs or user IDs.
     * @param session The session ID cookie.
     * @return A list of {@link Story} objects, or {@code null} if no stories are found.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public static List<Story> getActualStory(String[] ids, @NotNull String session) throws InstagramException {
        List<Story> stories = new ArrayList<>();
        JSONObject vars = new JSONObject();
        vars.put("initial_reel_id", ids[0]);
        vars.put("reel_ids", new JSONArray(ids));
        vars.put("first", 10);

        var response = Utils.postGraphQL(session, Constants.GraphQl.STORY, vars);
        var reels = response.getJSONObject("xdt_api__v1__feed__reels_media__connection").getJSONArray("edges");
        if (reels.isEmpty())
            return null;

        for (int i = 0; i < reels.length(); i++) {
            var node = reels.getJSONObject(i).getJSONObject("node");
            var items = node.getJSONArray("items");
            for (int j = 0; j < items.length(); j++) {
                var item = items.getJSONObject(j);
                var story = new Story(item);
                stories.add(story);
            }
        }
        return stories;
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
}
