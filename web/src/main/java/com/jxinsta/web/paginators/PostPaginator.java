package com.jxinsta.web.paginators;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.endpoints.post.Post;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import android.org.json.JSONObject;

/**
 * Paginator for fetching posts from a specific user's profile.
 * Implements {@link Iterator} to provide pages of {@link Post} objects.
 */
public class PostPaginator implements Iterator<List<Post>> {
    private final String session;
    private final String crsf;
    private final String username;
    private String nextCursor;
    private boolean hasMore = true;

    /**
     * Internal constructor for PostPaginator.
     *
     * @param session    The session ID cookie.
     * @param crsf       The CSRF token.
     * @param username   The username whose posts are being fetched.
     * @param nextCursor The initial pagination cursor. Pass {@code null} for the first page.
     */
    public PostPaginator(@NotNull String session,@NotNull String crsf, @NotNull String username, @Nullable String nextCursor) {
        this.session = session;
        this.crsf = crsf;
        this.username = username;
        this.nextCursor = nextCursor;
    }

    /**
     * Checks if there are more pages of posts available.
     *
     * @return {@code true} if another page can be fetched, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasMore;
    }

    /**
     * Fetches the next page of posts.
     *
     * @return A list of {@link Post} objects for the current page.
     * @throws NoSuchElementException If no more pages are available.
     * @throws RuntimeException If an {@link InstagramException} occurs during the API call.
     */
    @Override
    public List<Post> next() {
        if (!hasNext()) throw new NoSuchElementException();

        try {
            var params = new JSONObject();
            params.put("data", new JSONObject().put("count", 12));
            params.put("username", username);
            params.put("__relay_internal__pv__PolarisIsLoggedInrelayprovider", true);
            params.put("__relay_internal__pv__PolarisShareSheetV3relayprovider", true);

            if (nextCursor != null)
                params.put("after", nextCursor);

            var response = Utils.postGraphQL(session, Constants.GraphQl.PROFILE_POSTS, params);
            var mainJson = response.getJSONObject("xdt_api__v1__feed__user_timeline_graphql_connection");
            var edges = mainJson.getJSONArray("edges");
            var pageInfo = mainJson.getJSONObject("page_info");
            
            nextCursor = pageInfo.optString("end_cursor", null);
            hasMore = pageInfo.optBoolean("has_next_page", false);

            List<Post> list = new ArrayList<>();
            for (int i = 0; i < edges.length(); i++) {
                var node = edges.getJSONObject(i).getJSONObject("node");
                list.add(new Post(session,crsf, node));
            }
            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
