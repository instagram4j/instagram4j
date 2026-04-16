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
 * Paginator for fetching the home feed posts of the logged-in user.
 * Implements {@link Iterator} to provide pages of {@link Post} objects.
 */
public class FeedPaginator implements Iterator<List<Post>> {
    private final String session;
    private final String csrf;
    private String nextCursor;
    private boolean hasMore = true;

    /**
     * Internal constructor for FeedPaginator.
     *
     * @param session The session ID cookie.
     * @param csrf    The CSRF token.
     * @param cursor  The initial pagination cursor. Pass {@code null} for the first page.
     */
    public FeedPaginator(@NotNull String session,@NotNull String csrf, @Nullable String cursor) {
        this.session = session;
        this.nextCursor = cursor;
        this.csrf = csrf;
    }

    /**
     * Checks if there are more pages available in the feed.
     *
     * @return {@code true} if another page can be fetched, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasMore;
    }

    /**
     * Fetches the next page of feed posts.
     *
     * @return A list of {@link Post} objects for the current page.
     * @throws NoSuchElementException If no more pages are available.
     * @throws RuntimeException If an {@link InstagramException} occurs during the API call.
     */
    @Override
    public List<Post> next() {
        if (!hasMore)
            throw new NoSuchElementException();


        var variables = new JSONObject();
        variables.put("first", 12);
        variables.put("data", "{}");
        variables.put("__relay_internal__pv__PolarisIsLoggedInrelayprovider", false);
        variables.put("__relay_internal__pv__PolarisShareSheetV3relayprovider",false);
        if (nextCursor != null)
            variables.put("after", nextCursor);

        try {
            var res = Utils.postGraphQLWeb(session,csrf,Constants.GraphQl.FEED_POSTS,variables);
            var mainJson = res.getJSONObject("xdt_api__v1__feed__timeline__connection");
            var edges = mainJson.getJSONArray("edges");
            var pageInfo = mainJson.getJSONObject("page_info");

            hasMore = pageInfo.optBoolean("has_next_page", false);
            nextCursor = pageInfo.optString("end_cursor", null);

            var list = new ArrayList<Post>();
            for (int i = 0; i < edges.length(); i++) {
                var node = edges.getJSONObject(i).getJSONObject("node");
                var media = node.optJSONObject("media");
                if (media == null)
                    continue;

                list.add(new Post(session,csrf, media));
            }
            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
