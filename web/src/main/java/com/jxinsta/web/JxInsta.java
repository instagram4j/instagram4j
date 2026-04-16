package com.jxinsta.web;

import com.jxinsta.web.endpoints.post.Post;
import com.jxinsta.web.endpoints.profile.Profile;
import com.jxinsta.web.endpoints.profile.Story;
import com.jxinsta.web.paginators.FeedPaginator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
 * This is the main interface of the library. You can perform account level actions using this class.
 * For other actions like getting media, liking media, etc., there are other respective classes.
 */
public class JxInsta {
    /**
     * The username of the logged-in account.
     */
    public String username;
    /**
     * The password of the account (only available if initialized via constructor).
     */
    public String password;
    /**
     * The session ID cookie for authentication.
     */
    public String session;
    /**
     * The CSRF token used for secure requests.
     */
    public String crsf;

    /**
     * Logs into Instagram using the provided username and password.
     *
     * @param username The username of the account.
     * @param password The password of the account.
     * @throws IOException        If there's an error in the network communication.
     * @throws InstagramException If there's an error in the Instagram API response or authentication fails.
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
                .url(Constants.LOGIN_URL)
                .method("POST", body)
                .headers(Headers.of(Constants.BASE_HEADERS))
                .header("x-csrftoken", Utils.getCrsf())
                .build();

        try (var response = Utils.client.newCall(request).execute()) {
            var bodyString = response.body().string();
            var json = new JSONObject(bodyString);
            
            if (response.isSuccessful()) {
                if (json.optBoolean("authenticated")) {
                    var headers = response.headers("set-cookie");
                    for (String header : headers) {
                        if (header.startsWith("sessionid=")) {
                            session = header.split(";")[0].split("=")[1];
                        } else if (header.startsWith("csrftoken=")) {
                            crsf = header.split(";")[0].split("=")[1];
                        }
                    }
                } else {
                    throw new InstagramException("Authentication failed: " + bodyString, InstagramException.Reasons.INCORRECT_PASSWORD);
                }
            } else {
                throw InstagramException.wrap(json, response.code());
            }
        }
    }

    /**
     * Internal constructor for creating an instance with existing session credentials.
     *
     * @param session The sessionId cookie.
     * @param crsf    The CSRF token.
     * @param retries Number of retries (currently unused in this constructor).
     */
    protected JxInsta(@NotNull String session,@NotNull String crsf,int retries) {
        this.session = session;
        this.crsf = crsf;
    }

    /**
     * Initializes JxInsta with an existing session.
     *
     * @param session The sessionId cookie.
     * @param crsf    The CSRF token.
     * @return A new JxInsta instance.
     */
    public static JxInsta getInstance(@NotNull String session, @NotNull String crsf) {
        return new JxInsta(session,crsf,3);
    }

    /**
     * Fetches the profile information of a user by their username.
     *
     * @param username The username of the user to fetch.
     * @return A {@link Profile} instance containing user details and actions.
     * @throws InstagramException If there's an error in the Instagram API.
     */
    public Profile getProfile(String username) throws InstagramException {
        var res = Utils.getCall(Constants.Endpoints.userInfo(username), session);
        var user = res.getJSONObject("user");
        return new Profile(user, session,crsf);
    }

    /**
     * Fetches the home feed posts for the logged-in user.
     *
     * @param cursor The pagination cursor (optional). Pass {@code null} for the first page.
     * @return A {@link FeedPaginator} to iterate through feed posts.
     */
    public FeedPaginator getFeedPosts(@Nullable String cursor) {
        return new FeedPaginator(session,crsf,cursor);
    }

    /**
     * Fetches the stories from the user's feed tray.
     *
     * @return A list of arrays, where each array contains stories for a specific user.
     * @throws InstagramException If there's an error in the Instagram API.
     */
    public List<Story[]> getFeedStories() throws InstagramException {
        var list = new ArrayList<Story[]>();
        var json = Utils.getCall(Constants.Endpoints.STORIES, session);
        var users = json.getJSONArray("tray");
        String[] tray = new String[users.length()];
        for (int i = 0; i < users.length(); i++) {
            var trayId = users.getJSONObject(i).getLong("id");
            tray[i] = String.valueOf(trayId);
        }
        var stories = Story.getActualStory(tray, session);
        if (stories != null)
            list.add(stories.toArray(new Story[0]));

        return list;
    }

    /**
     * Uploads and posts a picture to Instagram.
     *
     * @param inputStream      The input stream of the image data.
     * @param caption          The caption for the post.
     * @param disableLikenComment Whether to disable likes and comments on the new post.
     * @throws IOException        If there's an error reading the input stream or network.
     * @throws InstagramException If there's an error in the Instagram API.
     */
    public void postPicture(@NotNull InputStream inputStream, @NotNull String caption, boolean disableLikenComment) throws IOException, InstagramException {
        var id = Utils.uploadPicture(inputStream, session);
        Map<String, Object> body = new HashMap<>();
        body.put("upload_id", id);
        body.put("caption", caption);
        if (disableLikenComment) {
            body.put("comments_disabled", "1");
            body.put("like_and_view_counts_disabled", "1");
        }

        Utils.postCall(session,crsf,Constants.Endpoints.MEDIA_CONFIGURE, body);
    }

    /**
     * Fetches a post object from a given Instagram post URL.
     *
     * @param url The URL of the Instagram post (e.g., "https://www.instagram.com/p/CODE/").
     * @return A {@link Post} instance representing the post.
     * @throws InstagramException If there's an error in the Instagram API or shortcode conversion.
     */
    public Post getPost(String url) throws InstagramException {
        var shortcode = url.split("/p/")[1].split("/")[0];
        var id = Utils.shortCode2id(shortcode);
        var res = Utils.getCall(Constants.Endpoints.mediaInfo(String.valueOf(id)), session);
        var items = res.getJSONArray("items");
        return new Post(session,crsf, items.getJSONObject(0));
    }

    /**
     * Searches for users on Instagram.
     *
     * @param username The search query (username prefix).
     * @return A map where keys are usernames and values are profile picture URLs.
     * @throws InstagramException If there's an error in the Instagram API.
     */
    public Map<String, String> search(@NotNull String username) throws InstagramException {
        var json = Utils.getCall(Constants.Endpoints.SEARCH + username, session);
        var users = json.getJSONArray("users");
        var map = new HashMap<String, String>();
        for (int i = 0; i < users.length(); i++) {
            var user = users.getJSONObject(i).getJSONObject("user");
            map.put(user.getString("username"), user.getString("profile_pic_url"));
        }
        return map;
    }
}
