package com.jxinsta.web.paginators;

import com.jxinsta.web.Constants;
import com.jxinsta.web.InstagramException;
import com.jxinsta.web.Utils;
import com.jxinsta.web.endpoints.HashtagPost;

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import android.org.json.JSONObject;

/**
 * Paginator for searching and fetching posts associated with a specific hashtag.
 * Implements {@link Iterator} to provide pages of {@link HashtagPost} objects.
 */
public class HashtagPaginator implements Iterator<List<HashtagPost>> {
    private String after;
    private final String keyword;
    private boolean hasMore = true;

    /**
     * Internal constructor for HashtagPaginator.
     *
     * @param cursor  The initial pagination cursor. Pass {@code null} for the first page.
     * @param keyword The hashtag keyword to search for.
     */
    public HashtagPaginator(@Nullable String cursor, String keyword){
        this.after = cursor;
        this.keyword = keyword;
    }

    /**
     * Checks if there are more pages of results available.
     *
     * @return {@code true} if another page can be fetched, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasMore;
    }

    /**
     * Fetches the next page of hashtag posts.
     *
     * @return A list of {@link HashtagPost} objects for the current page.
     * @throws NoSuchElementException If no more pages are available.
     * @throws RuntimeException If an {@link InstagramException} occurs during the API call.
     */
    @Override
    public List<HashtagPost> next() {
        if (!hasMore)
            throw new NoSuchElementException();

        var variables = new JSONObject();
        variables.put("after", after);
        variables.put("media_count", 29);
        variables.put("keyword", keyword);


        try {
            var res = Utils.publicGraphQL(Constants.GraphQl.HASHTAG,variables);
            System.out.println(res);
            var main = res.getJSONObject("xig_logged_out_popular_search_media_info");
            var edges = main.getJSONArray("edges");
            var list = new java.util.ArrayList<HashtagPost>();
            for (int i = 0; i < edges.length(); i++) {
                var node = edges.getJSONObject(i).getJSONObject("node");
                var hashtag = new HashtagPost(
                        node.getString("code"),
                        node.getString("id"),
                        node.getJSONObject("caption").getString("text"),
                        node.getJSONObject("user").getString("username"),
                        node.getString("display_uri"),
                        node.getLong("play_count"),
                        node.getJSONArray("video_versions").getJSONObject(0).getString("url")

                );
                list.add(hashtag);
            }

            var pageinfo = main.getJSONObject("page_info");
            hasMore = pageinfo.getBoolean("has_next_page");
            after = pageinfo.optString("end_cursor");
            return list;
        } catch (InstagramException e) {
            throw new RuntimeException(e);
        }
    }
}
