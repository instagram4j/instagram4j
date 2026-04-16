package com.jxinsta.web.endpoints.profile;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.paginators.PostPaginator;
import com.jxinsta.web.paginators.ProfilePaginator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import android.org.json.JSONArray;
import android.org.json.JSONObject;

/**
 * This class represents the profile of an Instagram user. 
 * It provides methods to fetch user-specific data like posts, followers, and stories, as well as actions like following or blocking.
 */
public class Profile extends ProfileData {
    protected final String session;
    private final String crsf;

    /**
     * Internal constructor for Profile.
     *
     * @param user    The JSON object containing profile data.
     * @param session The session ID cookie.
     * @param crsf    The CSRF token.
     */
    public Profile(@NotNull JSONObject user, @NotNull String session, @NotNull String crsf) {
        super(user);
        this.session = session;
        this.crsf = crsf;
    }

    /**
     * Fetches the posts of this profile.
     *
     * @param cursor The pagination cursor (optional). Pass {@code null} for the first page.
     * @return A {@link PostPaginator} to iterate through the user's posts.
     */
    public PostPaginator getPosts(@Nullable String cursor) {
        return new PostPaginator(session, crsf,username, cursor);
    }

    /**
     * Fetches the followers of this profile.
     *
     * @param cursor The pagination cursor (optional). Pass {@code null} for the first page.
     * @return A {@link ProfilePaginator} to iterate through the followers.
     */
    public ProfilePaginator getFollowers(@Nullable String cursor) {
        return new ProfilePaginator(session,crsf, pk, "followers", cursor);
    }

    /**
     * Fetches the list of accounts this profile is following.
     *
     * @param cursor The pagination cursor (optional). Pass {@code null} for the first page.
     * @return A {@link ProfilePaginator} to iterate through the followings.
     */
    public ProfilePaginator getFollowings(@Nullable String cursor) {
        return new ProfilePaginator(session,crsf, pk, "following", cursor);
    }

    /**
     * Fetches the active story reels of this user.
     *
     * @return A list of {@link Story} objects, or {@code null} if no stories are available.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public List<Story> getStory() throws InstagramException {
        var vars = new JSONObject();
        vars.put("reel_ids_arr", new JSONArray("[" + pk + "]"));

        var res = Utils.postGraphQL(session, Constants.GraphQl.USER_STORY, vars);
        var reels = res.getJSONObject("xdt_api__v1__feed__reels_media").getJSONArray("reels_media");
        if (reels.isEmpty())
            return null;
        else {
            var list = new ArrayList<Story>();
            var reel = reels.getJSONObject(0);
            var items = reel.getJSONArray("items");
            for (int j = 0; j < items.length(); j++) {
                var item = items.getJSONObject(j);
                list.add(new Story(item));
            }
            return list;
        }
    }

    /**
     * Fetches the story highlights of this user.
     *
     * @return A list of {@link Story} objects belonging to highlights, or {@code null} if none exist.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public List<Story> getHighlights() throws InstagramException {
        var variables = new JSONObject();
        variables.put("user_id", pk);

        var response = Utils.postGraphQL(session, Constants.GraphQl.HIGHLIGHTS, variables);
        var edges = response.getJSONObject("highlights").getJSONArray("edges");
        if (edges.isEmpty())
            return null;

        String[] ids = new String[edges.length()];
        for (int i = 0; i < edges.length(); i++) {
            ids[i] = edges.getJSONObject(i).getJSONObject("node").getString("id");
        }
        return Story.getActualStory(ids, session);
    }

    /**
     * Follows this user.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void follow() throws InstagramException {
        var variables = new JSONObject();
        variables.put("target_user_id", pk);
        Utils.postGraphQL(session, Constants.GraphQl.FOLLOW, variables);
    }

    /**
     * Unfollows this user.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void unfollow() throws InstagramException {
        var variables = new JSONObject();
        variables.put("target_user_id", pk);
        Utils.postGraphQL(session, Constants.GraphQl.UNFOLLOW, variables);
    }

    /**
     * Blocks this user.
     *
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public void block() throws InstagramException {
        var variables = new JSONObject();
        variables.put("target_user_ids", new JSONArray("[" + pk + "]"));
        Utils.postGraphQL(session, Constants.GraphQl.BLOCK, variables);
    }
}
