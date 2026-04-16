package com.jxinsta.web.endpoints;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.endpoints.post.PostData;
import com.jxinsta.web.endpoints.profile.ProfileData;
import com.jxinsta.web.paginators.HashtagPaginator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import android.org.json.JSONObject;

/**
 * This class contains all the public APIs available in the library. 
 * These endpoints/methods do not require login or authentication.
 */
public class PublicAPIs {

    /**
     * Fetches information about a post using its URL. 
     * <p>Note: The returned {@link PostData} object is for read-only information and cannot be used for actions like liking or commenting.</p>
     * 
     * @param url The URL of the post (e.g., "https://www.instagram.com/p/CODE/").
     * @return A {@link PostData} object containing media information.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public static PostData getPostInfo(@NotNull String url) throws InstagramException {
        var shortcode = url.split("/p/")[1].split("/")[0];
        var vars = new JSONObject();
        vars.put("shortcode", shortcode);
        vars.put("fetch_comment_count", 4);
        vars.put("fetch_tagged_user_count", 0);

        var body = new FormBody.Builder()
                .addEncoded("doc_id", "10015901848480474")
                .addEncoded("variables", vars.toString())
                .addEncoded("lsd", "JxInsta")
                .build();
        var req = new Request.Builder()
                .url("https://www.instagram.com/api/graphql")
                .headers(Headers.of(Constants.BASE_HEADERS))
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("x-fb-lsd", "JxInsta")
                .method("POST", body)
                .build();

        var json = Utils.callAPI(req);
        var shortcode_media = json.getJSONObject("xdt_shortcode_media");
        return new PostData(shortcode_media);
    }

    /**
     * Fetches profile information of a user by their username using a public endpoint.
     * <p>Note: This endpoint has strict rate limits and may not always work if called frequently.</p>
     *
     * @param username The username of the profile to fetch.
     * @return A {@link ProfileData} object containing basic user information.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public static ProfileData getProfileInfo(@NotNull String username) throws InstagramException {
        var response = Utils.publicCall("users/web_profile_info/?username=" + username);
        return new ProfileData(response.getJSONObject("user"));
    }

    /**
     * Fetches a list of posts for a profile using the user's numeric ID (pk).
     * 
     * @param pk         The numeric ID (pk) of the user.
     * @param count      The number of posts to fetch in this request.
     * @param nextCursor The pagination cursor for the next page of posts. Pass {@code null} for the first page.
     * @return A list of {@link PostData} objects representing the user's posts.
     * @throws InstagramException If there's an error in the Instagram API response.
     */
    public static List<PostData> getPosts(String pk, int count, @Nullable String nextCursor) throws InstagramException {
        var params = new HashMap<String, Object>();
        params.put("id", pk);
        params.put("first", count);
        if (nextCursor != null)
            params.put("after", nextCursor);


        var res = Utils.graphql(Constants.GraphQl.PUBLIC_USER_POSTS,params,null);
        var edges = res.getJSONObject("user").getJSONObject("edge_owner_to_timeline_media").getJSONArray("edges");
        var list = new ArrayList<PostData>();
        for (int i = 0; i < edges.length(); i++) {
            var node = edges.getJSONObject(i).getJSONObject("node");
            list.add(new PostData(node));
        }
        return list;
    }

    /**
     * Searches for posts associated with a specific hashtag.
     *
     * @param tag   The hashtag to search for (without the #).
     * @param after The pagination cursor for the next page of results. Pass {@code null} for the first page.
     * @return A {@link HashtagPaginator} to iterate through the results.
     */
    public static HashtagPaginator hashtagSearch(@NotNull String tag, @Nullable String after) {
        return new HashtagPaginator(after,tag);
    }
}
