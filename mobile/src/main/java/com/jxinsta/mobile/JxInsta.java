package com.jxinsta.mobile;


import com.jxinsta.mobile.endpoints.direct.Inbox;
import com.jxinsta.mobile.endpoints.post.Post;
import com.jxinsta.mobile.endpoints.profile.Profile;
import com.jxinsta.mobile.endpoints.profile.ProfileData;
import com.jxinsta.mobile.endpoints.profile.Story;
import com.jxinsta.mobile.paginators.FeedPaginator;
import com.jxinsta.mobile.utils.Constants;
import com.jxinsta.mobile.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

import android.org.json.JSONObject;

/**
 * Main interface for the JxInsta Mobile library.
 * This class provides methods for account-level actions such as logging in,
 * fetching profiles, stories, and managing the direct inbox.
 */
public class JxInsta {
    /** The username of the logged-in account. */
    public String username;
    /** The password of the account (only if initialized via constructor). */
    public String password;
    /** The authentication token used for mobile API requests. */
    public String token;

    /**
     * Authenticates with Instagram using a username and password.
     *
     * @param username The account username.
     * @param password The account password.
     * @throws IOException        If a network error occurs.
     * @throws InstagramException If authentication fails or the API returns an error.
     */
    public JxInsta(String username, String password) throws IOException, InstagramException {
        this.username = username;
        this.password = password;

        var encPassword = "#PWD_INSTAGRAM_BROWSER:0:" + new Date().getTime() + ":" + password;
        var body = new FormBody.Builder()
                .add("enc_password", encPassword)
                .add("username", username)
                .add("optIntoOneTap", "false")
                .add("queryParams", "{}")
                .add("trustedDeviceRecords", "{}")
                .build();
        Request request = new Request.Builder()
                .url(Constants.BASE_URL + Constants.LOGIN_ENDPOINT)
                .method("POST", body)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .build();

        var response = Utils.client.newCall(request).execute();
        if (response.isSuccessful()) {
            var bodyString = response.body().string();
            if (bodyString.equals("{\"user\":true,\"authenticated\":false,\"status\":\"ok\"}"))
                throw new InstagramException("Wrong password", InstagramException.Reasons.INCORRECT_PASSWORD);

            token = response.header("ig-set-authorization");
            response.close();
        } else {
            var json = new JSONObject(response.body().string());
            if (json.getString("status").equals("fail"))
                if (!json.getString("message").isEmpty())
                    throw new InstagramException(json.getString("message"), InstagramException.Reasons.UNKNOWN);
                else
                    throw new InstagramException(json.getString("error_type"), InstagramException.Reasons.TWO_FACTOR_REQUIRED);

            throw new InstagramException(json.toString(3), InstagramException.Reasons.UNKNOWN_LOGIN_ERROR);
        }
    }

    /**
     * Internal constructor for creating an instance with an existing auth token.
     *
     * @param auth The mobile authentication token.
     */
    protected JxInsta(String auth) {
        this.token = auth;
    }

    /**
     * Creates a JxInsta instance using an existing authentication token.
     *
     * @param auth The mobile auth token.
     * @return A new JxInsta instance.
     */
    public static JxInsta getInstance(@NotNull String auth) {
        return new JxInsta(auth);
    }


    /**
     * Fetches the profile of a user by their username.
     *
     * @param username The username of the user.
     * @return A {@link Profile} instance.
     * @throws InstagramException If the API returns an error.
     */
    public Profile getProfile(String username) throws InstagramException {
        var res = Utils.get(Constants.Endpoints.userInfo(username), token, null);
        var user = res.getJSONObject("user");
        return new Profile(user, token);
    }

    /**
     * Returns a paginator for the mobile feed.
     *
     * @return A {@link FeedPaginator} for iterating through feed items.
     */
    public FeedPaginator getFeed() {
        return new FeedPaginator(token);
    }

    /**
     * Fetches story reels from the tray.
     *
     * @return A list of story arrays, where each array represents stories from one user.
     * @throws InstagramException If the API returns an error.
     */
    public List<Story[]> getStories() throws InstagramException {
        Map<String, String> body = new HashMap<>();
        body.put("reason", "pull_to_refresh");
        body.put("page_size", "2");
        var res = Utils.post(Constants.Endpoints.REELS_TRAY, token, body);
        var tray = res.getJSONArray("tray");
        
        List<Story[]> list = new ArrayList<>();
        for (int i = 0; i < tray.length(); i++) {
            var item = tray.getJSONObject(i);
            if (item.has("media_ids")) {
                var media_ids = item.getJSONArray("media_ids");
                if (media_ids.isEmpty())
                    continue;

                var itemsRes = Utils.get(Constants.Endpoints.CLIPS_ITEMS, token, Map.of("clips_media_ids", media_ids.toString()));
                var clipsItems = itemsRes.getJSONArray("clips_items");
                
                Story[] stories = new Story[clipsItems.length()];
                for (int j = 0; j < clipsItems.length(); j++) {
                    var media = clipsItems.getJSONObject(j).getJSONObject("media");
                    stories[j] = new Story(media, token);
                }
                list.add(stories);
            }
        }
        return list;
    }

    /**
     * Uploads and posts a picture to Instagram.
     *
     * @param inputStream      The input stream of the image data.
     * @param caption          The caption for the post.
     * @param disableLikenComment Whether to disable likes and comments.
     * @throws InstagramException If the API returns an error or an IO error occurs during upload.
     */
    public void postPicture(@NotNull InputStream inputStream, @NotNull String caption, boolean disableLikenComment) throws InstagramException {
        try {
            var uploadId = Utils.uploadPicture(inputStream, token);
            Map<String, Object> body = new HashMap<>();
            body.put("upload_id", uploadId);
            body.put("caption", caption);
            if (disableLikenComment) {
                body.put("comments_disabled", "1");
                body.put("like_and_view_counts_disabled", "1");
            }
            var signedBody = Utils.genSignedBody(body);
            Utils.post(Constants.Endpoints.MEDIA_CONFIGURE, token, signedBody);
        } catch (IOException e) {
            throw new InstagramException(e.getMessage(), InstagramException.Reasons.IO);
        }
    }

    /**
     * Fetches a post object from a given URL.
     *
     * @param url The URL of the Instagram post.
     * @return A {@link Post} instance.
     * @throws InstagramException If the API returns an error.
     * @throws IOException        If a network error occurs.
     */
    public Post getPost(String url) throws InstagramException, IOException {
        var shortcode = url.split("/p/")[1].split("/")[0];
        var id = Utils.shortCode2id(shortcode);
        var res = Utils.get(Constants.Endpoints.mediaInfo(String.valueOf(id)), token, null);
        var items = res.getJSONArray("items");
        return new Post(token, items.getJSONObject(0));
    }

    /**
     * Searches for users matching a query.
     *
     * @param query The search query.
     * @return A list of {@link ProfileData} matching the query.
     * @throws InstagramException If the API returns an error.
     */
    public List<ProfileData> search(@NotNull String query) throws InstagramException {
        Map<String, Object> params = new HashMap<>();
        params.put("q", query);
        params.put("count", 5);
        var res = Utils.get(Constants.Endpoints.USER_SEARCH, token, params);
        var users = res.getJSONArray("users");
        List<ProfileData> list = new ArrayList<>();
        for (int i = 0; i < users.length(); i++) {
            list.add(new ProfileData(users.getJSONObject(i)));
        }
        return list;
    }

    /**
     * Fetches the direct message inbox.
     *
     * @param maxThreads  The maximum number of threads to fetch.
     * @param maxMessages The maximum number of messages per thread.
     * @return An {@link Inbox} instance.
     * @throws InstagramException If the API returns an error.
     */
    public Inbox getDirectInbox(int maxThreads, int maxMessages) throws InstagramException {
        var params = new HashMap<String,Object>();
        params.put("visual_message_return_type", "unread");
        params.put("limit", maxThreads);
        params.put("direction", "older");
        params.put("is_prefetching", false);
        params.put("thread_message_limit", maxMessages);

        var res = Utils.get(Constants.Endpoints.DM_INBOX, token, params);
        return new Inbox(token,res);
    }
}
